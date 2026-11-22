package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.LeavePage;

public class LeavePageTest {

    WebDriver driver;
    LoginPage loginPage;
    LeavePage leavePage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        leavePage = new LeavePage(driver);

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
    }

    @Test
    public void openAssignLeavePageTest() {

        leavePage.clickLeave();

        leavePage.clickAssignLeave();

        Assert.assertTrue(
                leavePage.isAssignLeavePageDisplayed(),
                "Assign Leave page was not displayed!"
        );
    }



    @Test
    public void assignLeaveTest() {

        leavePage.clickLeave();
        leavePage.clickAssignLeave();

        // Employee Name
        leavePage.enterEmployeeName("John David Smith");
        leavePage.selectEmployeeSuggestion("John David Smith");

        // Leave Type
        leavePage.selectLeaveType("CAN - Personal");

        // Dates
        String tomorrow = leavePage.getTomorrowDate();

        leavePage.enterFromDate(tomorrow);
        leavePage.enterToDate(tomorrow);

        // Assign Leave
        leavePage.clickAssign();

        System.out.println("Leave Assigned Successfully");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
