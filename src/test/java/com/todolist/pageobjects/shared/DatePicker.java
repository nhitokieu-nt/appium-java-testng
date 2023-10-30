package com.todolist.pageobjects.shared;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import com.todolist.pageobjects.BasePage;
import com.todolist.utils.DateTimeUtils;
import com.todolist.utils.Pair;

import java.text.ParseException;

public class DatePicker extends BasePage {
    private By LBL_YEAR = AppiumBy.id("android:id/date_picker_header_year");
    private Pair<String, String> SPN_TARGET_YEAR = Pair.of("uiAutomator", "%s");

    private By LBL_MONTH_DAY = AppiumBy.id("android:id/date_picker_header_date");
    private Pair<String, String> LBL_DAY = Pair.of("accessibilityId", "%s") ;

    private By BTN_NEXT = AppiumBy.id("android:id/next");

    private By BTN_PREVIOUS = AppiumBy.id("android:id/prev");


    public DatePicker(AppiumDriver driver) {
        super(driver);
    }

    @Step("Select year")
    public void selectYear(String year) {
        if (Integer.parseInt(year) != Integer.parseInt(getText(LBL_YEAR))) {
            tapElement(LBL_YEAR);
            tapElement(getByLocator(SPN_TARGET_YEAR, year));
        }
    }

    @Step("Select month")
    public void selectMonth(String targetMonth) throws ParseException {
        int targetMonthInt = Integer.parseInt(DateTimeUtils.convertDateTime("MMMM", "MM", targetMonth));

        // Get the current month and year from the calendar
        String monthDayText = getText(LBL_MONTH_DAY);
        String[] monthDayArray = monthDayText.split(" ");

        int month = Integer.parseInt(DateTimeUtils.convertDateTime("MMM", "MM", monthDayArray[1]));
        int comparison = month - targetMonthInt;
        while (comparison > 0) {
            tapElement(BTN_PREVIOUS);
            monthDayText = getText(LBL_MONTH_DAY);
            monthDayArray = monthDayText.split(" ");
            month = Integer.parseInt(DateTimeUtils.convertDateTime("MMM", "MM", monthDayArray[1]));
            comparison --;
        }
        while (comparison < 0) {
            tapElement(BTN_NEXT);
            monthDayText = getText(LBL_MONTH_DAY);
            monthDayArray = monthDayText.split(" ");
            month = Integer.parseInt(DateTimeUtils.convertDateTime("MMM", "MM", monthDayArray[1]));
            comparison ++;
        }
    }

    @Step("Select day")
    public void selectDay(String day) {
        tapElement(getByLocator(LBL_DAY, day));
    }

    @Step("Select date")
    public void selectDate(String dateString) throws ParseException {
        String targetYear = DateTimeUtils.convertDateTime("dd MMMM yyyy", "yyyy", dateString);
        String targetMonth = DateTimeUtils.convertDateTime("dd MMMM yyyy", "MMM", dateString);

        selectYear(targetYear);
        selectMonth(targetMonth);
        selectDay(dateString);

        tapOkBtn();
    }

    @Step("Tap OK button")
    public void tapOkBtn() {
        tapElementByText("OK");
    }
}
