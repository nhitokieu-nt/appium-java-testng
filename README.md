# Appium with Java-TestNG Framework
# To-do List application

<br>

## Introduction

This is an automation framework supporting UI mobile testing. It's built on top of the following component
- Selenium: Automation tool for web applications.
- TestNG: Test runner for executing tests.
- Appium: Framework for mobile application automation.


## Prerequisites
1. Java Development Kit (JDK): [Java 8 or higher](https://www.oracle.com/in/java/technologies/downloads/) installed.
2. Appium: [Appium](http://appium.io/docs/en/2.1/quickstart/install/) 2.x or higher installed
3. Android Emulator or Real Device: Android Studio or [SDK Flatform Tool](https://developer.android.com/tools/releases/platform-tools)
4. Install [Allure report](https://docs.qameta.io/allure/#_installing_a_commandline) commandline

## Project Structure

```
ToDoList/
|- src
   |- main/java/com.todolist
      |- driver
      |- utils                            # Functions for file, random
   |- test
      |- java/com/todolist
         |- constants                        # Constants
         |- models                           # Data models
         |- pageobjects                      # Page object classes
            |- shared                        # Common page classes
         |- tests                            # UI test cases of the application
      |- resources                           # Data (app, config data, test data ..)
         |- app                              # Application file (.apk)
         |- testdata                         # Data file (E.g. Excel, csv, ...)
         |- testsuite                        # Test suite     
         |- appium.properties                # Appium capabilities
|- .gitignore                             # Excluded the unnecessary files in the repo
|- README.md                              # This file          
```
<br>


## Getting Started
Clone the repository:

      git clone <repository-url>
      cd <repository-folder>
Install dependencies:

      mvn clean install -DskipTests
Run tests:

      mvn clean test
## Allure report:

      allure serve .\allure-results
<br>
