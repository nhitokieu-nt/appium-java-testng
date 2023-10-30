package com.todolist.pageobjects.shared;

import com.todolist.pageobjects.BasePage;
import io.appium.java_client.AppiumDriver;
import com.todolist.utils.DateTimeUtils;
import com.todolist.utils.Pair;
import io.qameta.allure.Step;

import java.text.ParseException;

public class TimerModal extends BasePage {
    private static final Pair<String, String> LBL_HOUR = Pair.of("accessibilityId", "%s");
    private static final Pair<String, String> LBL_MINUTE = Pair.of("accessibilityId", "%s");

    public TimerModal(AppiumDriver driver) {
        super(driver);
    }

    @Step("Choose hour")
    public void chooseHour(String hour) {
        if (Integer.parseInt(hour) < 10) {
            hour = String.valueOf(Integer.parseInt(hour));
        }
        tapElement(getByLocator(LBL_HOUR, hour));
    }

    @Step("Choose minutes")
    public void chooseMinute(String minute) {
        if (Integer.parseInt(minute) < 10) {
            minute = String.valueOf(Integer.parseInt(minute));
        }
        tapElement(getByLocator(LBL_MINUTE, minute));
    }

    @Step("Choose AM/PM")
    public void chooseAMorPM(String timeType) {
        tapElementByText(timeType);
    }

    @Step("Tap OK button")
    public void tapOkBtn() {
        tapElementByText("OK");
    }

    @Step("Select time")
    public void selectTime(String timeStr) throws ParseException {
        chooseHour(DateTimeUtils.convertDateTime("hh:mm a", "hh", timeStr));
        chooseMinute(DateTimeUtils.convertDateTime("hh:mm a", "mm", timeStr));
        chooseAMorPM(DateTimeUtils.convertDateTime("hh:mm a", "a", timeStr));
        tapOkBtn();
    }
}
