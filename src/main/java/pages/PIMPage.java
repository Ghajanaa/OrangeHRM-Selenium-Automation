
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIMPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By pimMenu = By.xpath("//span[text()='PIM']");
    private By addEmployeeButton = By.xpath("//a[text()='Add Employee']");

    private By firstName = By.name("firstName");
    private By middleName = By.name("middleName");
    private By lastName = By.name("lastName");

    private By employeeId = By.xpath(
            "//label[text()='Employee Id']/../following-sibling::div//input"
    );

    private By saveButton = By.cssSelector("button[type='submit']");

    private By personalDetailsHeading =
            By.xpath("//h6[text()='Personal Details']");

    // Employee List locators
    private By employeeListButton =
            By.xpath("//a[text()='Employee List']");

    private By searchEmployeeId =
            By.xpath("//label[text()='Employee Id']/../following-sibling::div//input");

    private By searchButton =
            By.cssSelector("button[type='submit']");

    private By employeeRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");

    private By licenseNumber = By.xpath(
            "//label[text()=\"Driver's License Number\"]/../following-sibling::div//input"
    );

    private By licenseExpiryDate = By.xpath(
            "//label[text()='License Expiry Date']/../following-sibling::div//input"
    );

    private By nationality = By.xpath(
            "//label[text()='Nationality']/../following-sibling::div//div[contains(@class,'oxd-select-text')]"
    );

    private By maritalStatus = By.xpath(
            "//label[text()='Marital Status']/../following-sibling::div//div[contains(@class,'oxd-select-text')]"
    );

    private By dateOfBirth = By.xpath(
            "//label[text()='Date of Birth']/../following-sibling::div//input"
    );

    private By personalDetailsSaveButton = By.xpath(
            "//h6[text()='Personal Details']/following::button[@type='submit'][1]"
    );

    private By successMessage = By.cssSelector(
            ".oxd-toast-content-text"
    );



    public PIMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickPIM() {
        wait.until(ExpectedConditions.elementToBeClickable(pimMenu)).click();
    }

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton)).click();
    }

    public void enterFirstName(String first) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName))
                .sendKeys(first);
    }

    public void enterMiddleName(String middle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(middleName))
                .sendKeys(middle);
    }

    public void enterLastName(String last) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName))
                .sendKeys(last);
    }

    public void enterEmployeeId(String id) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(employeeId)
        );

        field.clear();
        field.sendKeys(id);
    }


    public void clickSave() {

        // Wait until the form loading overlay disappears
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.className("oxd-form-loader")
                )
        );

        // Wait until Save button is clickable
        wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        ).click();

        // Wait for Personal Details page
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        personalDetailsHeading
                )
        );
    }


    public String getPersonalDetailsHeading() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(personalDetailsHeading)
        ).getText();
    }

    public String getCurrentPageUrl() {
        wait.until(
                ExpectedConditions.urlContains("/pim/viewPersonalDetails/empNumber/")
        );

        return driver.getCurrentUrl();
    }

    public String getEmployeeNumberFromUrl() {
        String url = getCurrentPageUrl();
        return url.substring(url.lastIndexOf("/") + 1);
    }


    public void clickEmployeeList() {
        wait.until(ExpectedConditions.elementToBeClickable(employeeListButton)).click();
    }

    public void enterSearchEmployeeId(String id) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchEmployeeId)
        );

        field.clear();
        field.sendKeys(id);
    }

    public void clickSearch() {

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.className("oxd-form-loader")
                )
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(searchButton)
        ).click();

        // Wait for search results table to load
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("oxd-table-body")
                )
        );

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.className("oxd-form-loader")
                )
        );
    }

    public int getEmployeeRowCount() {

        return driver.findElements(employeeRows).size();
    }


    public void clickEmployeeNameById(String id) {

        By employeeName = By.xpath(
                "//div[contains(@class,'oxd-table-card')]" +
                        "[.//div[normalize-space()='" + id + "']]" +
                        "//div[@role='cell'][3]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(employeeName)
        ).click();

        // Wait until the Personal Details page loads
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(personalDetailsHeading)
        );
    }

    //LicenseNumber
    public void enterLicenseNumber(String license) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(licenseNumber)
        );
        field.clear();
        field.sendKeys(license);
    }

    //date of license
    public void enterLicenseExpiryDate(String date) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(licenseExpiryDate)
        );
        field.clear();
        field.sendKeys(date);
    }


    //nationality dropdown
    public void selectNationality(String country) {

        // Wait until loading overlay disappears
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.className("oxd-form-loader")
                )
        );

        // Click nationality dropdown
        wait.until(
                ExpectedConditions.elementToBeClickable(nationality)
        ).click();

        // Find and select nationality
        By option = By.xpath(
                "//div[@role='listbox']//span[text()='" + country + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(option)
        ).click();

        // Wait for loading to finish
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.className("oxd-form-loader")
                )
        );
    }


    //date of birth
    public void enterDateOfBirth(String date) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(dateOfBirth)
        );
        field.clear();
        field.sendKeys(date);
    }


    //marital status dropdown
    public void selectMaritalStatus(String status) {

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.className("oxd-form-loader")
                )
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(maritalStatus)
        ).click();

        By option = By.xpath(
                "//div[@role='listbox']//span[text()='" + status + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(option)
        ).click();

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.className("oxd-form-loader")
                )
        );
    }

    public void savePersonalDetails() {

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.className("oxd-form-loader")
                )
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(personalDetailsSaveButton)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMessage)
        );
    }

    public String getSuccessMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(successMessage)
        ).getText();
    }

    public void clickDeleteEmployeeById(String id) {

        By deleteButton = By.xpath(
                "//div[contains(@class,'oxd-table-card')]" +
                        "[.//div[normalize-space()='" + id + "']]" +
                        "//button[.//i[contains(@class,'bi-trash')]]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(deleteButton)
        ).click();
    }


}
