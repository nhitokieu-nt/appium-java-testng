package com.todolist.tests;

import com.todolist.pageobjects.DetailTaskPage;
import com.todolist.pageobjects.shared.DatePicker;
import com.todolist.pageobjects.shared.ModalDialog;
import com.todolist.pageobjects.shared.TimerModal;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.todolist.pageobjects.HomePage;

import java.text.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteTaskTest extends BaseTest {
    HomePage homePage;
    DetailTaskPage detailTaskPage;
    ModalDialog modalDialog;

    @BeforeMethod
    public void beforeDeleteTask() {
        homePage = new HomePage(driver);
        detailTaskPage = new DetailTaskPage(driver);
        modalDialog = new ModalDialog(driver);
    }

    @Step("Verify that user can delete task successfully")
    @Test
    public void deleteTaskTest() {
        homePage.tapTaskItem();
        detailTaskPage.tapDeleteIcon();
        modalDialog.tapDeleteBtn();
        assertThat("Verify empty image", homePage.isEmptyImageDisplayed());
        assertThat("Verify empty text", homePage.isEmptyTextDisplayed());
    }
}
