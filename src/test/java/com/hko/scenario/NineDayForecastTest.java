package com.hko.scenario;

import com.hko.base.BaseTest;
import com.hko.base.TestLogger;
import com.hko.pageobject.MainPage;
import com.hko.utils.Page;
import org.testng.annotations.Test;

public class NineDayForecastTest extends BaseTest {

    @Page
    MainPage mainpage;

    private TestLogger logger = TestLogger.getInstance(getClass());

    @Test
    public void nineDayWeatherForecastTest() throws InterruptedException {

        /**
         * Launch the app
         */
        logger.info("Launch APP");
        mainpage.launchApp();

        /**
         * Open side menu
         */
        logger.info("Open side menu");
        mainpage.swipeSideMenu();

        /**
         * Select item: 9-Day Weather Forecast
         */
        logger.info("Select item: 9-Day Weather Forecast");
        mainpage.selectNineDayWeatherForecast();
        /**
         * Assert the 9-Day Forecast Tap is selected
         * Assert the text is equal to expected
         */
        logger.info("Verfy Forecast of the next 9 days are displayed");
        mainpage.assertNineDayDisplayed();

        /**
         * Assert 9-Day Forecast are all displayed
         */
        mainpage.assertAllNineDayDisplayed();
    }
}
