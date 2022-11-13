package com.demo.baseAction;

import com.demo.util.BaseUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AndroidCommonActions {
    AppiumDriver driver;
    public AndroidCommonActions(AppiumDriver driver){
        this.driver = driver;
    }

    public void waitForElementToBeVisible(MobileElement element){
        WebDriverWait wait = new WebDriverWait(driver, BaseUtil.WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(MobileElement element){
        waitForElementToBeVisible(element);
        element.click();
    }

    public void sendKeys(MobileElement element, String text){
        waitForElementToBeVisible(element);
        element.sendKeys(text);
    }

    public Boolean isElementDisplayed(MobileElement element){
        WebDriverWait displayWait = new WebDriverWait(driver, BaseUtil.DISPLAY_WAIT);
        try {
            if (displayWait.until(ExpectedConditions.visibilityOf(element)).isDisplayed() == true) {
                return true;
            }
        }catch (Exception e){
        }
        return false;
    }
}
