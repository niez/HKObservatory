package com.hko.base;
import com.hko.utils.Page;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.ReflectionUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public class BaseTest {

    private static final String PROPERTIES_FILE = "project.properties";
    private Properties properties;
    protected static AppiumDriver<MobileElement> driver;
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
        processAnnotations();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }


    private void processAnnotations() {

        for(Field field: getClass().getDeclaredFields()){
            processAnnotation(field);
        }
    }

    private void processAnnotation(Field field) {
        if(field.isAnnotationPresent(Page.class)){
            setField(field);
        }
    }

    private void setField(Field field) {
        //
        try {
            BasePage basePage = (BasePage) field.getType().newInstance();
            basePage.setDriver(driver);
            ReflectionUtils.setField(field, this, basePage);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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
}

