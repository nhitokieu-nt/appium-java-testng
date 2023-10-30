//package com.todolist.tests;
//
//import com.google.gson.JsonObject;
//import com.todolist.pageobjects.DetailTaskPage;
//import com.todolist.pageobjects.shared.DatePicker;
//import com.todolist.pageobjects.shared.TimerModal;
//import utils.JsonUtils;
//import io.qameta.allure.Step;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import com.todolist.pageobjects.HomePage;
//import utils.DateTimeUtils;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;
//import static com.todolist.constants.FilePathConstants.CREATE_NEW_TASK_PATH;
//
//public class CreateTaskTest extends BaseTest {
//    HomePage homePage;
//    DetailTaskPage detailTaskPage;
//    DatePicker datePicker;
//    TimerModal timerModal;
//
//    @BeforeMethod
//    public void setUp() {
//        homePage = new HomePage(driver);
//        detailTaskPage = new DetailTaskPage(driver);
//        datePicker = new DatePicker(driver);
//        timerModal = new TimerModal(driver);
//    }
//
//    @Step("Verify that user can create task successfully")
//    @Test(priority = 1)
//    public void createTaskTest() throws Exception {
//        JsonObject newTask = JsonUtils.readJsonFile(CREATE_NEW_TASK_PATH);
//        homePage.tapAddTaskButton();
//        detailTaskPage.enterTaskName(newTask.get("taskName").getAsString());
//        detailTaskPage.tapDueDate();
//        datePicker.selectDate(newTask.get("dueDate").getAsString());
//        detailTaskPage.tapDueTime();
//        timerModal.selectTime(newTask.get("dueTime").getAsString());
//        detailTaskPage.selectRepeatOption(newTask.get("repeat").getAsString());
//        detailTaskPage.selectList(newTask.get("taskList").getAsString());
//        detailTaskPage.tapSaveTaskBtn();
//
//        String[] dateArr = homePage.getTaskDateTime().split(", ");
//
//        String actualDate = DateTimeUtils.convertDateTime("MMM dd yyyy", "dd MMMM yyyy", dateArr[1] + " " + dateArr[2]);
//        String actualTime = dateArr[dateArr.length-1];
//        assertThat("Verify task name", homePage.getTaskName(), equalTo(newTask.get("taskName").getAsString()));
//        assertThat("Verify task list name", homePage.getTaskListName(), equalTo(newTask.get("taskList").getAsString()));
//        assertThat("Verify task due date", actualDate, equalTo(newTask.get("dueDate").getAsString()));
//        assertThat("Verify task due time", actualTime, equalTo(newTask.get("dueTime").getAsString()));
//    }
//}




package com.todolist.tests;

import com.todolist.models.Task;
import com.todolist.pageobjects.DetailTaskPage;
import com.todolist.pageobjects.shared.DatePicker;
import com.todolist.pageobjects.shared.TimerModal;
import com.todolist.utils.JsonUtils;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.todolist.pageobjects.HomePage;
import com.todolist.utils.DateTimeUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static com.todolist.constants.FilePathConstants.CREATE_NEW_TASK_PATH;

public class CreateTaskTest extends BaseTest {
    HomePage homePage;
    DetailTaskPage detailTaskPage;
    DatePicker datePicker;
    TimerModal timerModal;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(driver);
        detailTaskPage = new DetailTaskPage(driver);
        datePicker = new DatePicker(driver);
        timerModal = new TimerModal(driver);
    }

    @Step("Verify that user can create task successfully")
    @Test
    public void createTaskTest() throws Exception {
        Task newTask = JsonUtils.parseJsonFile(CREATE_NEW_TASK_PATH, Task.class);
        homePage.tapAddTaskButton();
        detailTaskPage.enterTaskName(newTask.taskName);
        detailTaskPage.tapDueDate();
        datePicker.selectDate(newTask.dueDate);
        detailTaskPage.tapDueTime();
        timerModal.selectTime(newTask.dueTime);
        detailTaskPage.selectRepeatOption(newTask.repeat);
        detailTaskPage.selectList(newTask.taskList);
        detailTaskPage.tapSaveTaskBtn();

        String[] dateArr = homePage.getTaskDateTime().split(", ");

        String actualDate = DateTimeUtils.convertDateTime("MMM dd yyyy", "dd MMMM yyyy", dateArr[1] + " " + dateArr[2]);
        String actualTime = dateArr[dateArr.length-1];
        assertThat("Verify task name", homePage.getTaskName(), equalTo(newTask.taskName));
        assertThat("Verify task list name", homePage.getTaskListName(), equalTo(newTask.taskList));
        assertThat("Verify task due date", actualDate, equalTo(newTask.dueDate));
        assertThat("Verify task due time", actualTime, equalTo(newTask.dueTime));
    }
}
