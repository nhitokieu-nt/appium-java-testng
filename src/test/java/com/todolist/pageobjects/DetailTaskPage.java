package com.todolist.pageobjects;

import com.todolist.pageobjects.shared.DatePicker;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import com.todolist.pageobjects.shared.TimerModal;

import java.text.ParseException;
import java.util.ArrayList;


public class DetailTaskPage extends BasePage {

    private final By TXT_ENTER_TASK = AppiumBy.id("com.splendapps.splendo:id/edtTaskName");
    private final By DTP_DUE_DATE = AppiumBy.id("com.splendapps.splendo:id/btnSetD");
    private final By TMR_DUE_TIME = AppiumBy.id("com.splendapps.splendo:id/btnSetT");
    private final By DDL_REPEAT = AppiumBy.id("com.splendapps.splendo:id/spinnerRepeat");
    private final By DDL_ADD_TO_LIST = AppiumBy.id("com.splendapps.splendo:id/spinnerLists");
    private final By BTN_SAVE_TASK = AppiumBy.id("com.splendapps.splendo:id/fabSaveTask");
    private final By ICON_DELETE = AppiumBy.ByAndroidUIAutomator.androidUIAutomator("new UiSelector().resourceId(\"com.splendapps.splendo:id/action_delete_task\")");

    public DetailTaskPage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Enter task name")
    public void enterTaskName(String taskName) {
        waitForElementToBeTappable(TXT_ENTER_TASK);
        clearAndEnter(TXT_ENTER_TASK, taskName);
    }

    @Step("Select due date")
    public void tapDueDate() {
        tapElement(DTP_DUE_DATE);
    }

    @Step("Select due time")
    public void tapDueTime() {
        tapElement(TMR_DUE_TIME);
    }

    @Step("Select repeat option")
    public void selectRepeatOption(String option) {
        String[] repeatOpt= new ArrayList<String>().toArray(new String[0]);
        repeatOpt = new String[]{"No repeat", "Once a Day", "Once a Day (Mon-Fri)", "Once a Week", "Once a Month", "Once a Year"};
        selectOptionFromDropdown(DDL_REPEAT, option);
    }

    @Step("Select list category")
    public void selectList(String option) {
        selectOptionFromDropdown(DDL_ADD_TO_LIST, option);
    }

    @Step("Tap Save button")
    public void tapSaveTaskBtn() {
        tapElement(BTN_SAVE_TASK);
    }

    @Step("Tap Delete icon")
    public void tapDeleteIcon() {
        tapElement(ICON_DELETE);
    }

    @Step("Select due date")
    public void selectDueDate(String date) throws ParseException {
        DatePicker datePicker = new DatePicker(driver);
        tapDueDate();
        datePicker.selectDate(date);
    }

    @Step("Select due time")
    public void selectDueTime(String time) throws ParseException {
        TimerModal timerModal = new TimerModal(driver);
        tapDueTime();
        timerModal.selectTime(time);
    }

    public void createNewTask() throws ParseException {
        enterTaskName("taskname");
        tapDueDate();
        selectDueDate("28 September 2024");
        tapDueTime();
        selectDueTime("03:30 PM");
        selectRepeatOption("Once a Week");
        selectList("Shopping");
        tapSaveTaskBtn();
    }
}
