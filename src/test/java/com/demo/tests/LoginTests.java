package com.demo.tests;

import com.demo.actions.LogInScreen;
import com.demo.utilBase.BaseTest;
import io.qameta.allure.Description;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

public  class LoginTests extends BaseTest {

    LogInScreen logInScreen;

    @BeforeMethod
    public void beforeMethod(){
        logInScreen = new LogInScreen(driver);
    }

    @Test
    @Description("Click got it button if displayed and click on Later button")
    public void Test1_clickOnGotItAndUpdateLaterButton() throws InterruptedException {
        System.out.println("In test One");
        if (logInScreen.isGotItButtonDisplayed() == true) {
            logInScreen.clickGotItButton();
        }
        logInScreen.clickOnLaterButton();
    }

    @Test
    @Description("Verify screen to navigate to login is displayed, click login button")
    public void Test2_isLoginBtnDisplayed() throws InterruptedException {
        System.out.println("In test Two");
        Assert.assertEquals(logInScreen.isLoginScreenDisplayed(),true,
                "[Failed]: Login screen is not displayed");
        logInScreen.clickOnLogIn();
    }

    @Test
    @Description("Verify Username, Password and Login Button is displayed")
    public void Test3_areLoginAndPasswordDisplayed() throws InterruptedException {
        System.out.println("In test Three");
        Assert.assertEquals(logInScreen.isUserNameFieldDisplayed(),true,
                "[Failed]: Username field missing");
        Assert.assertEquals(logInScreen.isPasswordFieldDisplayed(),true,
                "[Failed]: Password field missing");
        Assert.assertEquals(logInScreen.isLoginButtonDisplayed(),true,
                "[Failed]: Login button not displayed");
    }

    @Test
    @Description("Enter Username & Password, and click login button")
    public void Test4_loginTheUser() throws InterruptedException, IOException, ParseException {
        System.out.println("In test Four");
        JSONObject userCred  = BaseTest.testDataReader("./src/test/java/com/demo/testData/testData.json");
        logInScreen.enterUsername((String)userCred.get("username"));
        logInScreen.enterPassword((String)userCred.get("password"));
        logInScreen.clickOnLogInButton();
    }

    @Test
    @Description("Verify user is on home screen after log in")
    public void Test5_isUserLoggedIn() throws InterruptedException {
        System.out.println("In test Five");
        Assert.assertEquals(logInScreen.isMainPageDisplayed(),true,
                "[Failed]: HomePage is not displayed after login in; user was not signed in");
    }

}