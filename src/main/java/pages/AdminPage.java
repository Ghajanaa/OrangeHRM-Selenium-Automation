package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AdminPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By adminMenu = By.cssSelector(
            "a[href='/web/index.php/admin/viewAdminModule']"
    );

    private By systemUsersHeading = By.xpath(
            "//h5[text()='System Users']"
    );

    private By addButton = By.xpath(
            "//button[.//i[contains(@class,'bi-plus')]]"
    );

    private By addUserHeading = By.xpath(
            "//h6[text()='Add User']"
    );


    private By userRole = By.xpath(
            "//label[text()='User Role']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')]"
    );

    private By status = By.xpath(
            "//label[text()='Status']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')]"
    );

    private By employeeName = By.xpath(
            "//input[@placeholder='Type for hints...']"
    );

    private By username = By.xpath(
            "//label[text()='Username']/../following-sibling::div//input"
    );

    private By password = By.xpath(
            "//label[text()='Password']/../following-sibling::div//input"
    );

    private By confirmPassword = By.xpath(
            "//label[text()='Confirm Password']/../following-sibling::div//input"
    );

    private By saveButton = By.cssSelector(
            "button[type='submit']"
    );




    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickAdmin() {
        wait.until(
                ExpectedConditions.elementToBeClickable(adminMenu)
        ).click();
    }

    public boolean isSystemUsersHeadingDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(systemUsersHeading)
        ).isDisplayed();
    }

    public void clickAddUser() {
        wait.until(
                ExpectedConditions.elementToBeClickable(addButton)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(addUserHeading)
        );
    }


    public void selectUserRole(String role) {

        wait.until(
                ExpectedConditions.elementToBeClickable(userRole)
        ).click();

        By option = By.xpath(
                "//div[@role='listbox']//span[text()='" + role + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(option)
        ).click();
    }



    public void selectStatus(String userStatus) {

        wait.until(
                ExpectedConditions.elementToBeClickable(status)
        ).click();

        By option = By.xpath(
                "//div[@role='listbox']//span[text()='" + userStatus + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(option)
        ).click();
    }


    public void enterUsername(String user) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(username)
        ).sendKeys(user);
    }

    public void enterPassword(String pass) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(password)
        ).sendKeys(pass);
    }

    public void enterConfirmPassword(String pass) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(confirmPassword)
        ).sendKeys(pass);
    }


    public void clickSave() {
        wait.until(
                ExpectedConditions.elementToBeClickable(saveButton)
        ).click();
    }

    public void enterEmployeeName(String name) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(employeeName)
        );

        field.clear();
        field.sendKeys(name);

        By dropdown = By.className("oxd-autocomplete-dropdown");

        // Wait until the search finishes
        wait.until(driver -> {
            WebElement result = driver.findElement(dropdown);
            return !result.getText().trim().equalsIgnoreCase("Searching...");
        });

        // Print the actual search result
        WebElement result = driver.findElement(dropdown);

        System.out.println("Autocomplete result: " + result.getText());
    }


    public void selectEmployeeSuggestion(String name) {

        By suggestion = By.xpath(
                "//div[@role='listbox']//span[contains(text(),'" + name + "')]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(suggestion)
        ).click();
    }
}
