package com.todolist.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.options.BaseOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {
    public static AppiumDriver getDriver() throws MalformedURLException {
        System.out.println("Create Appium Driver Instance");
        // Appium 2 with Selenium 4
        BaseOptions options=getOptions();
        return new AppiumDriver(new URL(System.getProperty("APPIUM_HUB_URL")), options);
    }
    public static BaseOptions getOptions(){
        return new BaseOptions()
                .setPlatformName(System.getProperty("PLATFORM_NAME"))
                .setPlatformVersion(System.getProperty("PLATFORM_VERSION"))
                .setAutomationName(System.getProperty("AUTOMATION_NAME")) //appium 2.0
                .noReset()
                .amend("deviceName", System.getProperty("DEVICE_NAME"))
                .amend("app", System.getProperty("user.dir") + System.getProperty("APP"));
    }
}
