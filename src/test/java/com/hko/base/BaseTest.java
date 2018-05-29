package com.hko.base;
import com.hko.driver.DriverFactory;
import com.hko.utils.Page;
import io.appium.java_client.AppiumDriver;
import org.springframework.util.ReflectionUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import java.lang.reflect.Field;

public class BaseTest {

    private TestContext context;
    private AppiumDriver driver;
    private DriverFactory driverFactory = new DriverFactory();

    @BeforeTest
    public void setUp() throws IOException {
        context = new TestContext();
        driver = driverFactory.create(context);
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

}

