
package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.PIMPage;

public class PIMTest {

    WebDriver driver;
    LoginPage loginPage;
    PIMPage pimPage;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        pimPage = new PIMPage(driver);


    }

    @Test
    public void addEmployeeTest() {

        pimPage.clickPIM();
        pimPage.clickAddEmployee();

        pimPage.enterFirstName("John");
        pimPage.enterMiddleName("David");
        pimPage.enterLastName("Smith");

        pimPage.enterEmployeeId("10025");

        pimPage.clickSave();

        // Verify successful employee creation
        Assert.assertEquals(
                pimPage.getPersonalDetailsHeading(),
                "Personal Details",
                "Employee creation failed!"
        );

        // Verify employee profile URL
        Assert.assertTrue(
                pimPage.getCurrentPageUrl().contains(
                        "/pim/viewPersonalDetails/empNumber/"
                ),
                "Employee profile page was not opened!"
        );

        // Verify employee number
        Assert.assertTrue(
                pimPage.getEmployeeNumberFromUrl().matches("\\d+"),
                "Invalid employee number!"
        );

        System.out.println("SUCCESS: Employee created successfully!");
    }


    @Test
    public void searchEmployeeTest() {

        // Open PIM module
        pimPage.clickPIM();

        // Open Employee List directly
        pimPage.clickEmployeeList();

        // Enter Employee ID
        pimPage.enterSearchEmployeeId("10025");

        // Click Search
        pimPage.clickSearch();

        // Verify employee appears in search results
        Assert.assertTrue(
                pimPage.getEmployeeRowCount() > 0,
                "Employee was not found!"
        );

        System.out.println("SUCCESS: Employee search completed!");
    }


    @Test
    public void viewEmployeeDetailsTest() {

        // Open PIM module
        pimPage.clickPIM();

        // Open Employee List
        pimPage.clickEmployeeList();

        // Search for employee
        pimPage.enterSearchEmployeeId("10025");

        // Click Search
        pimPage.clickSearch();

        // Open employee details
        pimPage.clickEmployeeNameById("10025");

        // Verify Personal Details page
        Assert.assertEquals(
                pimPage.getPersonalDetailsHeading(),
                "Personal Details",
                "Employee details page was not opened!"
        );

        // Verify URL
        Assert.assertTrue(
                pimPage.getCurrentPageUrl().contains("/pim/viewPersonalDetails/"),
                "Incorrect employee details page URL!"
        );

        System.out.println("SUCCESS: Employee details viewed successfully!");
    }


    @Test
    public void updatePersonalDetailsTest() {

        // Navigate to employee details
        pimPage.clickPIM();
        pimPage.clickEmployeeList();
        pimPage.enterSearchEmployeeId("10025");
        pimPage.clickSearch();
        pimPage.clickEmployeeNameById("10025");

        // Driver's License Number
        pimPage.enterLicenseNumber("DL12345");

        // License Expiry Date
        pimPage.enterLicenseExpiryDate("2028-15-06");

        // Nationality
        pimPage.selectNationality("Sri Lankan");

        // Date of Birth
        pimPage.enterDateOfBirth("1998-15-06");

        // Marital Status
        pimPage.selectMaritalStatus("Married");

        System.out.println("All personal details fields filled!");


        // Save Personal Details
        pimPage.savePersonalDetails();

        // Verify successful update
        Assert.assertEquals(
                pimPage.getSuccessMessage(),
                "Success",
                "Personal Details were not updated!"
        );

        System.out.println("SUCCESS: Personal Details saved successfully!");
    }

    @Test
    public void deleteEmployeeTest() throws InterruptedException {

        pimPage.clickPIM();
        pimPage.clickEmployeeList();

        pimPage.enterSearchEmployeeId("0483");
        pimPage.clickSearch();

        Thread.sleep(2000);
        pimPage.clickDeleteEmployeeById("0483");

        System.out.println("Employee deleted successfully!");
    }

    //search employee negative scenario
    @Test
    public void searchEmployeeWithInvalidIdTest() {

        pimPage.clickPIM();
        pimPage.clickEmployeeList();

        pimPage.enterSearchEmployeeId("999999");
        pimPage.clickSearch();

        Assert.assertEquals(
                pimPage.getEmployeeRowCount(),
                0,
                "Unexpected employee found!"
        );
    }


    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
