package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton =
            By.cssSelector("button[type='submit']");

    private By errorMessage =
            By.cssSelector(".oxd-alert-content-text");

    private By validationMessages =
            By.cssSelector(".oxd-input-field-error-message");

    private By dashboardHeading =
            By.xpath("//h6[text()='Dashboard']");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );
    }

    // Open login page
    public void openLoginPage() {
        driver.get(
                "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
        );
    }

    // Enter username
    public void enterUsername(String username) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameField)
        ).sendKeys(username);
    }

    // Enter password
    public void enterPassword(String password) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField)
        ).sendKeys(password);
    }

    // Click login
    public void clickLogin() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }

    // Get login error
    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
    }

    // Get dashboard heading
    public String getDashboardHeading() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(dashboardHeading)
        ).getText();
    }

    // Get required field validation count
    public int getValidationMessageCount() {
        wait.until(
                ExpectedConditions.numberOfElementsToBe(
                        validationMessages, 2
                )
        );

        List<WebElement> messages =
                driver.findElements(validationMessages);

        return messages.size();
    }
}
