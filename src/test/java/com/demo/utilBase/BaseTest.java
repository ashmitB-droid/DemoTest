package com.demo.utilBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.*;
import java.net.URL;
import java.util.Properties;

public class BaseTest {

    public static AppiumDriver driver;
    public static Properties properties;
    InputStream inputStream;
    protected static String platform;

    AppiumServiceBuilder builder;
    AppiumDriverLocalService service;
/*If value for emulator is not true in xml file then uncomment line number 43*/
    @Parameters({"emulator","platformName", "platformVersion", "deviceName"})
    @BeforeTest
    public void setUp(String emulator, String platformName, String platformVersion, String deviceName){
        DesiredCapabilities caps = new DesiredCapabilities();
        URL url;
        platform = platformName;
        try {
            properties = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
            caps.setCapability("deviceName", deviceName);
            caps.setCapability("platformName", platformName);
            caps.setCapability("platformVersion", platformVersion);
            switch(platformName){
                case "Android":
                    File appPath = new File(System.getProperty("user.dir")+properties.getProperty("androidAppLocation"));
//                    caps.setCapability("udid", properties.getProperty("androidUdid"));
                    if(emulator.equalsIgnoreCase("true")) {
                         caps.setCapability("avd",properties.getProperty("deviceName"));
                    }
                    caps.setCapability("appPackage", properties.getProperty("androidAppPackage"));
                    caps.setCapability("appActivity", properties.getProperty("androidAppActivity"));
                    caps.setCapability("app",appPath.getAbsolutePath());
                    url = new URL(properties.getProperty("appiumUrl"));

                    builder = new AppiumServiceBuilder();
                    builder.withIPAddress(properties.getProperty("ipAddress"));
                    builder.usingPort(Integer.parseInt(properties.getProperty("port")));
                    builder.withCapabilities(caps);
                    builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
                    builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

                    service = AppiumDriverLocalService.buildService(builder);
                    service.start();

                    driver = new AndroidDriver(url, caps);
                    break;
                case "iOS":
                // code for iOS app
                    break;
                default:
                    throw new Exception("Invalid platform!!! - "+platformName);
            }
        }catch (Exception exc){
            System.out.println(exc.getCause());
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    public static JSONObject testDataReader(String filePath) throws IOException, ParseException {
        JSONParser jsonparser = new JSONParser();
        FileReader reader = new FileReader(filePath);
        Object obj = jsonparser.parse(reader);
        JSONObject userCred = (JSONObject)obj;
        return userCred;
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
        service.stop();
    }
}
