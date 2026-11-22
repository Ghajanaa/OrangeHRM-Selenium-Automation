package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeavePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Sidebar Leave menu
    private By leaveMenu = By.cssSelector(
            "a[href='/web/index.php/leave/viewLeaveModule']"
    );

    // Assign Leave tab
    private By assignLeaveTab = By.xpath(
            "//a[contains(@class,'oxd-topbar-body-nav-tab-item') and normalize-space()='Assign Leave']"
    );

    // Assign Leave heading
    private By assignLeaveHeading = By.xpath(
            "//h6[text()='Assign Leave']"
    );

    private By employeeName = By.xpath(
            "//input[@placeholder='Type for hints...']"
    );

    // Leave Type dropdown
    private By leaveType = By.xpath(
            "//label[text()='Leave Type']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')]"
    );

    // From Date
    private By fromDate = By.xpath(
            "//label[text()='From Date']/../following-sibling::div//input"
    );

    // To Date
    private By toDate = By.xpath(
            "//label[text()='To Date']/../following-sibling::div//input"
    );

    // Assign button
    private By assignButton = By.xpath(
            "//button[@type='submit' and normalize-space()='Assign']"
    );


    public LeavePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickLeave() {
        wait.until(ExpectedConditions.elementToBeClickable(leaveMenu)).click();
    }

    public void clickAssignLeave() {
        wait.until(ExpectedConditions.elementToBeClickable(assignLeaveTab)).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(assignLeaveHeading)
        );
    }

    public boolean isAssignLeavePageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(assignLeaveHeading)
        ).isDisplayed();
    }

    public void enterEmployeeName(String name) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(employeeName)
        );

        field.clear();
        field.sendKeys(name);
    }

    public void selectEmployeeSuggestion(String name) {
        By suggestion = By.xpath(
                "//div[contains(@class,'oxd-autocomplete-option')]//span[normalize-space()='"
                        + name + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(suggestion)
        ).click();
    }

    //select leave type
    public void selectLeaveType(String type) {

        wait.until(
                ExpectedConditions.elementToBeClickable(leaveType)
        ).click();

        By option = By.xpath(
                "//div[@role='listbox']//span[normalize-space()='" + type + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(option)
        ).click();
    }


    //to select date
    public String getTomorrowDate() {

        return LocalDate.now()
                .plusDays(1)
                .format(DateTimeFormatter.ofPattern("yyyy-dd-MM"));
    }


    public void enterFromDate(String date) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromDate)
        );

        field.clear();
        field.sendKeys(date);
    }

    public void enterToDate(String date) {

        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(toDate)
        );

        field.clear();
        field.sendKeys(date);
    }


    //click assign
    public void clickAssign() {

        wait.until(
                ExpectedConditions.elementToBeClickable(assignButton)
        ).click();
    }

}
