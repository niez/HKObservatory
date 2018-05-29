package com.hko.driver;

import com.hko.base.TestContext;
import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;

public class DriverFactory {

    private AppiumDriverCreator driverInstance;

    public AppiumDriver create(TestContext context) throws MalformedURLException {

        switch (context.getMobileType()){
            case "Android":
                driverInstance = new AndroidDriverCreator();
                break;
        }
        return driverInstance.getDriver(context);
    }
}
