package com.hko.base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class BaseTest {

    private static final String PROPERTIES_FILE = "project.properties";
    private Properties properties;
    protected static AppiumDriver<MobileElement> driver;
    private WebDriverWait wait;
    private TestLogger logger = TestLogger.getInstance(getClass());

    @BeforeTest
    public void setUp() throws IOException {

        initialize();
        File appDir = new File(properties.getProperty("app.directory"));
        File app = new File(appDir, properties.getProperty("app.name"));
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //mandatory capabilities
        capabilities.setCapability("deviceName",properties.getProperty("deviceName"));
        capabilities.setCapability("platformName",properties.getProperty("platformName"));

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver =  new AndroidDriver<MobileElement>(new URL(properties.getProperty("appiumUrl")), capabilities);
        wait = new WebDriverWait(driver, 20);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    private void initialize() throws IOException {
        logger.info("Initializing...");
        final InputStream inputStream = ClassLoader.getSystemResourceAsStream(PROPERTIES_FILE);
        if (inputStream == null)
        {
            throw new FileNotFoundException("Propery file '" + PROPERTIES_FILE + "' not found in classpath");
        } else
        {
            properties = new Properties();
            properties.load(inputStream);
        }
    }

    protected WebElement waitUntilElementVisible(By locator){
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

    protected List expectedDates(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(properties.getProperty("timeZone")));
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMMM");
        List<String> expectedDates = new ArrayList<String>();
        for(int i = 1; i <= 9; i ++){
            cal.add(Calendar.DATE, 1);
            expectedDates.add(sdf.format(cal.getTime()));
        }
        return expectedDates;
    }

    private void swipe(int startx, int starty, int endx, int endy, int duration) {
        new TouchAction((MobileDriver) driver).press(startx, starty).waitAction(Duration.ofMillis(duration)).moveTo(endx, endy).release().perform();
    }


}

