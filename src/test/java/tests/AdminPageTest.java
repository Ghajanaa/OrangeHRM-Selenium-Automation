package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.AdminPage;

public class AdminPageTest {

    WebDriver driver;
    LoginPage loginPage;
    AdminPage adminPage;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);

        loginPage.openLoginPage();
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
    }

    @Test
    public void viewSystemUsersTest() {

        adminPage.clickAdmin();

        Assert.assertTrue(
                adminPage.isSystemUsersHeadingDisplayed(),
                "System Users page was not opened!"
        );
    }

    @Test
    public void openAddUserFormTest() {

        adminPage.clickAdmin();
        adminPage.clickAddUser();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/admin/saveSystemUser"),
                "Add User form was not opened!"
        );
    }


    @Test
    public void addAdminUserTest() {

        // Navigate to Admin module
        adminPage.clickAdmin();

        // Open Add User form
        adminPage.clickAddUser();

        // Select User Role
        adminPage.selectUserRole("ESS");

        // Enter and select existing employee
        adminPage.enterEmployeeName("John David Smith");
        //adminPage.selectEmployeeSuggestion("John Doe");

        // Select Status
        adminPage.selectStatus("Enabled");

        // Enter Username
        adminPage.enterUsername("Johndavid");

        // Enter Password
        adminPage.enterPassword("John123");

        // Confirm Password
        adminPage.enterConfirmPassword("John123");

        // Save the new user
        adminPage.clickSave();

        // Verify successful navigation back to System Users
        Assert.assertTrue(
                adminPage.isSystemUsersHeadingDisplayed(),
                "Admin user was not created successfully!"
        );
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
