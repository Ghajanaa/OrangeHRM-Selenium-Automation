package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.RecruitmentPage;

public class RecruitmentPageTest {

    WebDriver driver;
    LoginPage loginPage;
    RecruitmentPage recruitmentPage;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        recruitmentPage = new RecruitmentPage(driver);

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
    }

    @Test
    public void openAddCandidateFormTest() {

        recruitmentPage.clickRecruitment();

        recruitmentPage.clickCandidates();

        recruitmentPage.clickAddCandidate();

        Assert.assertTrue(
                recruitmentPage.isAddCandidateFormDisplayed(),
                "Add Candidate form was not displayed!"
        );
    }


    @Test
    public void fillCandidateDetailsTest() {

        recruitmentPage.clickRecruitment();
        recruitmentPage.clickCandidates();
        recruitmentPage.clickAddCandidate();

        recruitmentPage.enterFirstName("David");
        recruitmentPage.enterMiddleName("John");
        recruitmentPage.enterLastName("Perera");

        recruitmentPage.selectVacancy("Software Engineer");

        recruitmentPage.enterEmail("david@example.com");

        System.out.println("Candidate created successfully");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
