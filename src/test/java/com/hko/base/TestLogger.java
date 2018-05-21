package com.hko.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {

    private Logger logger;

    private TestLogger(Class<?> clazz){
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public static TestLogger getInstance(Class<?> clazz) {
        return new TestLogger(clazz);
    }

    public void info(String message)
    {
        logger.info(message);
    }

    public void warn(String message)
    {
        logger.warn(message);
    }

    public void error(String message)
    {
        logger.error(message);
    }

}
