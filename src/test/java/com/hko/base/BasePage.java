package com.hko.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {


    private AppiumDriver driver;
    private WebDriverWait wait;

    protected void setDriver(AppiumDriver driver){
        this.driver = driver;
        init();
    }

    private void init() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }

    protected WebElement waitUntilElementVisible(By locator){
        wait = new WebDriverWait(driver, 30);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void swipingHorizontal(int duration) {
        // duration should be in milliseconds
        Dimension size = driver.manage().window().getSize();
        // Find swipe start point and end point from screen's width and height
        int pointY = (int) (size.height * 0.50 );
        int startPointX = (int) (size.width * 0.01);
        int endPointX = (int) (size.width * 0.80);

        swipe(startPointX, pointY, endPointX, pointY, duration);
    }


    protected void swipingVertical(int duration){
        Dimension size = driver.manage().window().getSize();
        // Find swipe start point and end point from screen's width and height
        int pointX = (int) (size.width * 0.30 );
        int startPointY = (int) (size.height * 0.92);
        int endPointY = (int) (size.height * 0.30);

        swipe(pointX, startPointY, pointX, endPointY, duration);
    }

    private void swipe(int startx, int starty, int endx, int endy, int duration) {
        new TouchAction((MobileDriver) driver).press(startx, starty).waitAction(Duration.ofMillis(duration)).moveTo(endx, endy).release().perform();
    }



}
