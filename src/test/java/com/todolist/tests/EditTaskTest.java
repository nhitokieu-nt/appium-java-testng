package com.todolist.tests;

import com.todolist.models.Task;
import com.todolist.pageobjects.DetailTaskPage;
import com.todolist.pageobjects.HomePage;
import com.todolist.pageobjects.shared.DatePicker;
import com.todolist.pageobjects.shared.TimerModal;
import com.todolist.utils.DateTimeUtils;
import com.todolist.utils.JsonUtils;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.todolist.constants.FilePathConstants.EDIT_TASK_PATH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EditTaskTest extends BaseTest {
    HomePage homePage;
    DetailTaskPage detailTaskPage;
    DatePicker datePicker;
    TimerModal timerModal;

    @BeforeMethod
    public void beforeEditTask() {
        homePage = new HomePage(driver);
        detailTaskPage = new DetailTaskPage(driver);
        datePicker = new DatePicker(driver);
        timerModal = new TimerModal(driver);
    }

    @Step("Verify that user can edit task successfully")
    @Test
    public void editTaskTest() throws Exception {
        Task editedTask = JsonUtils.parseJsonFile(EDIT_TASK_PATH, Task.class);
        homePage.tapTaskItem();
        detailTaskPage.enterTaskName(editedTask.taskName);
        detailTaskPage.tapDueDate();
        datePicker.selectDate(editedTask.dueDate);
        detailTaskPage.tapDueTime();
        timerModal.selectTime(editedTask.dueTime);
        detailTaskPage.selectRepeatOption(editedTask.repeat);
        detailTaskPage.selectList(editedTask.taskList);
        detailTaskPage.tapSaveTaskBtn();

        String[] dateArr = homePage.getTaskDateTime().split(", ");

        String actualDate = DateTimeUtils.convertDateTime("MMM dd yyyy", "dd MMMM yyyy", dateArr[1] + " " + dateArr[2]);
        String actualTime = dateArr[dateArr.length-1];
        assertThat("Verify task name", homePage.getTaskName(), equalTo(editedTask.taskName));
        assertThat("Verify task list name", homePage.getTaskListName(), equalTo(editedTask.taskList));
        assertThat("Verify task due date", actualDate, equalTo(editedTask.dueDate));
        assertThat("Verify task due time", actualTime, equalTo(editedTask.dueTime));
    }
}
