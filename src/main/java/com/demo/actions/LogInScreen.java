package com.demo.actions;

import com.demo.baseAction.AndroidCommonActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LogInScreen extends AndroidCommonActions {
    AppiumDriver driver;
    public LogInScreen(AppiumDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }
    @AndroidFindBy(id = "android:id/ok") private MobileElement gotIt;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Later')]") private MobileElement laterBtn;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Login Using')]") private MobileElement logIn;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Start Playing Securely')]") private MobileElement launchScreen;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='RNE__Input__text-input'][contains(@text,'Enter username or email')]") private MobileElement username;
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='RNE__Input__text-input'][contains(@text,'Enter password')]") private MobileElement password;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Log In Securely')]") private MobileElement loginBtn;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Home')]") private MobileElement mainPage;


    public Boolean isGotItButtonDisplayed(){
    return isElementDisplayed(gotIt);
}

    public void clickGotItButton(){
        click(gotIt);
    }

    public Boolean isUserNameFieldDisplayed(){
        return isElementDisplayed(username);
    }

    public Boolean isPasswordFieldDisplayed(){
        return isElementDisplayed(password);
    }

    public Boolean isLoginButtonDisplayed(){
        return isElementDisplayed(loginBtn);
    }

    public void clickOnLaterButton(){
        isElementDisplayed(laterBtn);
        click(laterBtn);
    }

    public Boolean isLoginScreenDisplayed(){
        return isElementDisplayed(launchScreen);
    }

    public void clickOnLogIn(){
        click(logIn);
    }

    public void clickOnLogInButton(){
        click(loginBtn);
    }

    public void enterUsername(String user){
        sendKeys(username,user);
    }

    public void enterPassword(String passText){
        sendKeys(password,passText);
    }

    public boolean isMainPageDisplayed(){
     return isElementDisplayed(mainPage);
    }
}
