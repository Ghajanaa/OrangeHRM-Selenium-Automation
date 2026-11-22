package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginPageTest {

    WebDriver driver;
    LoginPage loginPage;

    // Open browser before each test
    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
    }

    // TC_LOGIN_001: Valid Login
    @Test
    public void validLoginTest() {

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getDashboardHeading(),
                "Dashboard",
                "Valid login failed!"
        );
    }

    // TC_LOGIN_002: Invalid Login
    @Test
    public void invalidLoginTest() {

        loginPage.enterUsername("InvalidUser");
        loginPage.enterPassword("WrongPassword123");
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid credentials",
                "Expected error message was not displayed!"
        );
    }

    // TC_LOGIN_003: Empty Credentials
    @Test
    public void emptyCredentialsTest() {

        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getValidationMessageCount(),
                2,
                "Required validation messages were not displayed!"
        );
    }



    @Test
    public void invalidUsernameTest() {

        loginPage.enterUsername("InvalidUser");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid credentials",
                "Invalid username validation failed!"
        );
    }



    @Test
    public void invalidPasswordTest() {

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("WrongPassword123");
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Invalid credentials",
                "Invalid password validation failed!"
        );
    }


    // Close browser after each test
    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
