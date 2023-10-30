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
//
        String locatorValue = String.format(elementPattern.getValue(), elementLocatorValues);
        return convertToBy(elementPattern.getKey(),locatorValue);
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

//    public WebElement waitForElementToBeVisible(By locator) {
//        WebElement element = findElement(locator);
//        return wait.until(ExpectedConditions.visibilityOf(locator));
//    }

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

// Find the element you want to tap on
        WebElement elementToTap = scrollableContainer.findElement(AppiumBy.androidUIAutomator(String.format("new UiSelector().text(\"%s\")", option)));

// Scroll until the element becomes visible
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







//    public void tapElementByText(String text) {
//        WebElement element = findElementByText(text);
//        element.click();
//    }

//    public void scrollAndTapElement(String text) {
//        //Find element
//        WebElement element = findElementByText(text);
//        //Scroll until the element is visible
//        while (!element.isDisplayed()) {
//            Dimension size = driver.manage().window().getSize();
//            int startX = size.width / 2;
//            int startY = (int) (size.height * 0.8);
//            int endY = (int) (size.height * 0.2);
//
//            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
//            Sequence swipe = new Sequence(finger, 0);
//            swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));
//            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
//            swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
//            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
//            driver.perform(Arrays.asList(swipe));
//        }
//        //Tap on the element
//        element.click();
//    }
}






//    public WebElement waitForElementToBeClickable(By locator) {
//        return wait.until(ExpectedConditions.elementToBeClickable(locator));
//    }
//
//    public WebElement waitForElementToBeVisible(By locator) {
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }
//
//    public Alert waitForAlertToBePresence() {
//        return wait.until(ExpectedConditions.alertIsPresent());
//    }
//
//    public Alert switchDriverToAlert() {
//        waitForAlertToBePresence();
//        return driver.switchTo().alert();
//    }
//
//    public boolean isElementDisplayed(By locator) {
//        try {
//            waitForElementToBeVisible(locator);
//            return true;
//        } catch (TimeoutException e) {
//            return false;
//        }
//    }
//
//    public void inputText(By locator, String text) {
//        WebElement element = waitForElementToBeClickable(locator);
//        element.sendKeys(text);
//    }
//
//    public void tapElement(By locator) {
//        WebElement element = waitForElementToBeClickable(locator);
//        element.click();
//    }
//
//    public void selectOptionFromDropdown(By locator, String text) {
//        tapElement(locator);
//        tapElement(AppiumBy.ByAndroidUIAutomator.androidUIAutomator(String.format("newUiSeletor().text(\"%s\")", text)));
//    }
//
//    public void uploadFile(By locator, String filePath) {
//        File directory = new File(filePath);
//        filePath = directory.getAbsolutePath();
//        WebElement element = waitForElementToBeClickable(locator);
//        element.sendKeys(filePath);
//    }
//
//    public String getText(By locator) {
//        WebElement element = waitForElementToBeVisible(locator);
//        return element.getText();
//    }
//
//    public List<String> getTextList(By locator) {
//        List<String> textList = new ArrayList<>();
//        try {
//            waitForElementToBeVisible(locator);
//        } catch (Exception e) {
//            return textList;
//        }
//
//        List<WebElement> elementList = driver.findElements(locator);
//        for (WebElement element : elementList) {
//            textList.add(element.getText());
//        }
//        return textList;
//    }
//
//    public String getAlertText() {
//        return switchDriverToAlert().getText();
//    }
//
//    public void acceptAlert() {
//        switchDriverToAlert().accept();
//    }
//}
//
