package com.todolist.tests;

import com.todolist.driver.BrowserFactory;
//import com.todolist.utils.PropertiesFileUtils;
import com.todolist.utils.PropertiesFileUtils;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;


public class BaseTest {
    public static AppiumDriver driver;

    @Step("Start the application")
    @BeforeSuite
    public void launchApp() throws IOException {
        Properties properties = PropertiesFileUtils.loadPropertiesFromFile("/src/test/resources/appium.properties");
        PropertiesFileUtils.appendSystemProperties(properties);
        try {
            driver = BrowserFactory.getDriver();
            System.out.println(driver);
        }
        catch(MalformedURLException e) {
            System.out.println("Cause is: " + e.getCause());
            System.out.println("Message is: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Step("Close the application")
    @AfterSuite
    public void closeApp() {
        driver.quit();
    }
}
