package com.hko.driver;

import com.hko.base.TestContext;
import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;

public interface AppiumDriverCreator {

    AppiumDriver getDriver(TestContext context) throws MalformedURLException;
}
