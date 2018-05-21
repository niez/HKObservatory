package com.hko.scenario;

import com.hko.base.BaseTest;
import com.hko.base.TestLogger;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NineDayForecastTest extends BaseTest {

    private static final String ID_AGREE_BUTTON = "hko.MyObservatory_v1_0:id/btn_agree";
    private static final String ID_PERMISSION_ALLOW_BUTTON = "com.android.packageinstaller:id/permission_allow_button";
    private static final String ID_REMINDER_SKIP = "hko.MyObservatory_v1_0:id/btn_friendly_reminder_skip";
    private static final String XPATH_9_DAY_FORECAST_SELECTION = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[3]";
    private static final String XPATH_9_DAY_FORECAST_TAP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[2]/android.widget.TextView";
    private static final String XPATH_1ST_DAY_FORECAST = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]";
    private static final String XPATH_9TH_DAY_FORECAST = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]";
    private TestLogger logger = TestLogger.getInstance(getClass());

    @Test
    public void nineDayWeatherForecastTest() throws InterruptedException {

        /**
         * Launch the app
         */
        logger.info("Launch APP");
        waitUntilElementVisible(By.id(ID_AGREE_BUTTON)).click();
        waitUntilElementVisible(By.id(ID_AGREE_BUTTON)).click();
        waitUntilElementVisible(By.id(ID_PERMISSION_ALLOW_BUTTON)).click();
        waitUntilElementVisible(By.id(ID_REMINDER_SKIP)).click();

        Thread.sleep(1000);

        /**
         * Open side menu
         */
        logger.info("Open side menu");
        swipingHorizontal(1000); // swipe out the left navigation
        Thread.sleep(1000);
        swipingVertical(1400);   // scroll vertically to 9-Day Forecast linear

        /**
         * Select item: 9-Day Weather Forecast
         */
        logger.info("Select item: 9-Day Weather Forecast");
        waitUntilElementVisible(By.xpath(XPATH_9_DAY_FORECAST_SELECTION)).click();

        waitUntilElementVisible(By.xpath(XPATH_9_DAY_FORECAST_TAP));
        MobileElement nineDayForecastTap = driver.findElementByXPath(XPATH_9_DAY_FORECAST_TAP);
        /**
         * Assert the 9-Day Forecast Tap is selected
         * Assert the text is equal to expected
         */
        logger.info("Verfy Forecast of the next 9 days are displayed");
        Assert.assertTrue(nineDayForecastTap.isSelected());
        Assert.assertEquals(nineDayForecastTap.getText(), "9-Day Forecast");

        /**
         * Assert 9-Day Forecast are all displayed
         */
        MobileElement day1 = driver.findElementByXPath(XPATH_1ST_DAY_FORECAST);
        Assert.assertEquals(day1.getText(), expectedDates().get(0));
        // Scroll vertically from 1st Day to the 9th Day
        swipingVertical(1500);
        swipingVertical(1500);
        swipingVertical(1500);
        MobileElement day9 = driver.findElementByXPath(XPATH_9TH_DAY_FORECAST);
        Assert.assertEquals(day9.getText(), expectedDates().get(8));
    }
}
