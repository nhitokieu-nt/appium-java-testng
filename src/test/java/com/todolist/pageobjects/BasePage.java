package com.todolist.pageobjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.todolist.utils.Pair;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public AppiumDriver driver;
    public WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public List<WebElement> elements(By locator) {
        return driver.findElements(locator);
    }

    public static By getByLocator(Pair<String, String> elementPattern, String... elementLocatorValues) {
        String locatorValue = String.format(elementPattern.getValue(), elementLocatorValues);
        return convertToBy(elementPattern.getKey(), locatorValue);
    }

    public static By convertToBy(String locatorType, String locatorValue) {
        switch (locatorType.toLowerCase()) {
            case "id":
                return AppiumBy.id(locatorValue);
            case "name":
                return AppiumBy.name(locatorValue);
            case "classname":
                return AppiumBy.className(locatorValue);
            case "accessibilityid":
                return AppiumBy.accessibilityId(locatorValue);
            case "uiautomator":
                return AppiumBy.ByAndroidUIAutomator.androidUIAutomator(String.format("new UiSelector().text(\"%s\")", locatorValue));
            default:
                return AppiumBy.xpath(locatorValue);
        }
    }

    public WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeTappable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void tapElement(By locator) {
//        waitForElementToBeTappable(element);
        WebElement element = waitForElementToBeTappable(locator);
        element.click();
    }

    public void scrollAndSelect(String option) {

        WebElement scrollableContainer = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"));

        // Find the element to tap on
        WebElement elementToTap = scrollableContainer.findElement(AppiumBy.androidUIAutomator(String.format("new UiSelector().text(\"%s\")", option)));

        // Scroll until the element is visible
        driver.executeScript("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Element text\"))");


// Tap on the element
        elementToTap.click();
    }

    public void tapElement(WebElement element) {
        element.click();
    }

    public void tapElementByText(String text) {
        WebElement element = waitForElementToBeVisible(AppiumBy.ByAndroidUIAutomator.androidUIAutomator(String.format("new UiSelector().text(\"%s\")", text)));
        element.click();
    }

    public void enterText(By locator, String text) {
        WebElement element = waitForElementToBeTappable(locator);
        element.sendKeys(text);
    }

    public void clearAndEnter(By locator, String text) {
        WebElement element = waitForElementToBeTappable(locator);
        element.clear();
        element.sendKeys(text);
    }


    public void selectOptionFromDropdown(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        tapElement(locator);
        tapElementByText(text);
    }

    public String getText(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        return element.getText();
    }

    public By getByLocator(String text) {
        return AppiumBy.ByAndroidUIAutomator.androidUIAutomator(String.format("new UiSelector().text(\"%s\")", text));
    }


    public Dimension getDimension() {
        return driver.manage().window().getSize();
    }

    public boolean isElementDisplayed(By locator) {
        return waitForElementToBeVisible(locator).isDisplayed();
    }
}
