package com.todolist.pageobjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage extends  BasePage {
    private By BTN_ADD_TASK = AppiumBy.id("com.splendapps.splendo:id/fabAddTask");
    private By LBL_TASK_NAME = AppiumBy.ByAndroidUIAutomator.androidUIAutomator("new UiSelector().resourceId(\"com.splendapps.splendo:id/task_name\")");
    private By LBL_TASK_LIST_NAME = AppiumBy.ByAndroidUIAutomator.androidUIAutomator("new UiSelector().resourceId(\"com.splendapps.splendo:id/task_list_name\")");
    private By LBL_TASK_DATETIME = AppiumBy.ByAndroidUIAutomator.androidUIAutomator("new UiSelector().resourceId(\"com.splendapps.splendo:id/task_due\")");
    private By ITEM_TASK = AppiumBy.ByAndroidUIAutomator.androidUIAutomator("new UiSelector().resourceId(\"com.splendapps.splendo:id/task_list_item\")");
    private By IMG_EMPTY = AppiumBy.ByAndroidUIAutomator.androidUIAutomator("new UiSelector().resourceId(\"com.splendapps.splendo:id/ivEmpty\")");
    private By LBL_EMPTY = AppiumBy.ByAndroidUIAutomator.androidUIAutomator("new UiSelector().resourceId(\"com.splendapps.splendo:id/tvEmpty\")");

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    @Step("Tap (+) button")
    public void tapAddTaskButton() {
        tapElement(BTN_ADD_TASK);
    }

    public String getTaskName() {
        return getText(LBL_TASK_NAME);
    }

    public String getTaskListName() {
        return getText(LBL_TASK_LIST_NAME);
    }

    public String getTaskDateTime() {
        return getText(LBL_TASK_DATETIME);
    }

    @Step("Tap a task item")
    public void tapTaskItem() {
        tapElement(ITEM_TASK);
    }

    public boolean isEmptyImageDisplayed() {
        return isElementDisplayed(IMG_EMPTY);
    }

    public boolean isEmptyTextDisplayed() {
        return isElementDisplayed(LBL_EMPTY);
    }
}

