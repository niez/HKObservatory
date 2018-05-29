package com.hko.pageobject;

import com.hko.base.BasePage;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MainPage extends BasePage {

    // elements
    private static final String ID_AGREE_BUTTON = "hko.MyObservatory_v1_0:id/btn_agree";
    private static final String ID_PERMISSION_ALLOW_BUTTON = "com.android.packageinstaller:id/permission_allow_button";
    private static final String ID_REMINDER_SKIP = "hko.MyObservatory_v1_0:id/btn_friendly_reminder_skip";
    private static final String XPATH_9_DAY_FORECAST_SELECTION = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[3]";
    private static final String XPATH_9_DAY_FORECAST_TAP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[2]/android.widget.TextView";
    private static final String XPATH_1ST_DAY_FORECAST = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]";
    private static final String XPATH_9TH_DAY_FORECAST = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.support.v4.view.ViewPager/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]";

    @FindBy(xpath = XPATH_9_DAY_FORECAST_TAP)
    private MobileElement nineDayForecastTap;

    @FindBy(xpath = XPATH_1ST_DAY_FORECAST)
    private MobileElement day1;

    @FindBy(xpath = XPATH_9TH_DAY_FORECAST)
    private MobileElement day9;

    public void launchApp() throws InterruptedException {
        waitUntilElementVisible(By.id(ID_AGREE_BUTTON)).click();
        waitUntilElementVisible(By.id(ID_AGREE_BUTTON)).click();
        waitUntilElementVisible(By.id(ID_PERMISSION_ALLOW_BUTTON)).click();
        waitUntilElementVisible(By.id(ID_REMINDER_SKIP)).click();
        Thread.sleep(1000);
    }

    public void swipeSideMenu() throws InterruptedException {
        swipingHorizontal(1000); // swipe out the left navigation
        Thread.sleep(1000);
        swipingVertical(1400);   // scroll vertically to 9-Day Forecast linear
    }

    public void selectNineDayWeatherForecast() {
        waitUntilElementVisible(By.xpath(XPATH_9_DAY_FORECAST_SELECTION)).click();
        waitUntilElementVisible(By.xpath(XPATH_9_DAY_FORECAST_TAP));
    }

    public void assertNineDayDisplayed() {
        /**
         * Assert the 9-Day Forecast Tap is selected
         * Assert the text is equal to expected
         */
        Assert.assertTrue(nineDayForecastTap.isSelected());
        Assert.assertEquals(nineDayForecastTap.getText(), "9-Day Forecast");
    }

    public void assertAllNineDayDisplayed() {
        Assert.assertEquals(day1.getText(), expectedDates().get(0));
        // Scroll vertically from 1st Day to the 9th Day
        swipingVertical(1500);
        swipingVertical(1500);
        swipingVertical(1500);
        Assert.assertEquals(day9.getText(), expectedDates().get(8));
    }

    private List expectedDates(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("d MMMMM");
        List<String> expectedDates = new ArrayList<String>();
        for(int i = 1; i <= 9; i ++){
            cal.add(Calendar.DATE, 1);
            expectedDates.add(sdf.format(cal.getTime()));
        }
        return expectedDates;
    }
}
