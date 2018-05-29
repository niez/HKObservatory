package com.hko.driver;

import com.hko.base.TestContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidDriverCreator implements AppiumDriverCreator {

    private AppiumDriver driver;

    @Override
    public AppiumDriver getDriver(TestContext context) throws MalformedURLException {
        configure(context);
        return this.driver;
    }

    private void configure(TestContext context) throws MalformedURLException {
        File appDir = new File(context.getAppDirctory());
        File app = new File(appDir, context.getAppName());
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //mandatory capabilities
        capabilities.setCapability("deviceName",context.getDeviceType());
        capabilities.setCapability("platformName",context.getPlatformName());

        //other caps
        capabilities.setCapability("app", app.getAbsolutePath());
        driver =  new AndroidDriver<MobileElement>(new URL(context.getAppiumUrl()), capabilities);
    }
}
