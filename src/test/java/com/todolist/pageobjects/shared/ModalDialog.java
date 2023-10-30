package com.todolist.pageobjects.shared;

import com.todolist.pageobjects.BasePage;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;

public class ModalDialog extends BasePage {

    public ModalDialog(AppiumDriver driver) {
        super(driver);
    }

    @Step("Tap Delete button")
    public void tapDeleteBtn() {
        tapElementByText("DELETE");
    }
}
