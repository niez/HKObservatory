package com.hko.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestContext {

    private static final String PROPERTIES_FILE = "project.properties";
    private TestLogger logger = TestLogger.getInstance(getClass());
    private Properties properties;
    private String mobileType;
    private String appiumUrl;
    private String deviceType;
    private String appDirctory;
    private String appName;
    private String platformName;

    public TestContext() throws IOException {
        initialize();
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
        setFields();
    }

    private void setFields(){
        mobileType = properties.getProperty("mobile.type");
        appiumUrl = properties.getProperty("appium.url");
        deviceType = properties.getProperty("device.type");
        appDirctory = properties.getProperty("app.dirctory");
        appName = properties.getProperty("app.name");
        platformName = properties.getProperty("platform.name");
    }

    public String getMobileType() {
        return mobileType;
    }

    public String getAppiumUrl() {
        return appiumUrl;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getAppDirctory() {
        return appDirctory;
    }

    public String getAppName() {
        return appName;
    }

    public String getPlatformName() {
        return platformName;
    }

}
