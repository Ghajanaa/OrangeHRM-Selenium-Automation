package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecruitmentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Recruitment sidebar menu
    private By recruitmentMenu = By.cssSelector(
            "a[href='/web/index.php/recruitment/viewRecruitmentModule']"
    );

    // Candidates tab
    private By candidatesTab = By.xpath(
            "//a[contains(@class,'oxd-topbar-body-nav-tab-item') and normalize-space()='Candidates']"
    );

    // Add button
    private By addButton = By.xpath(
            "//button[.//i[contains(@class,'bi-plus')]]"
    );

    // Add Candidate heading
    private By addCandidateHeading = By.xpath(
            "//h6[text()='Add Candidate']"
    );

    // First Name
    private By firstName = By.name("firstName");

    // Middle Name
    private By middleName = By.name("middleName");

    // Last Name
    private By lastName = By.name("lastName");

    // Vacancy dropdown
    private By vacancyDropdown = By.xpath(
            "//label[text()='Vacancy']/../following-sibling::div//div[contains(@class,'oxd-select-text-input')]"
    );

    // Email
    private By email = By.xpath(
            "//input[@placeholder='Type here']"
    );



    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickRecruitment() {
        wait.until(
                ExpectedConditions.elementToBeClickable(recruitmentMenu)
        ).click();
    }

    public void clickCandidates() {
        wait.until(
                ExpectedConditions.elementToBeClickable(candidatesTab)
        ).click();
    }

    public void clickAddCandidate() {
        wait.until(
                ExpectedConditions.elementToBeClickable(addButton)
        ).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(addCandidateHeading)
        );
    }

    public boolean isAddCandidateFormDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(addCandidateHeading)
        ).isDisplayed();
    }

    //enter first name
    public void enterFirstName(String first) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstName)
        ).sendKeys(first);
    }

    //middle name
    public void enterMiddleName(String middle) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(middleName)
        ).sendKeys(middle);
    }

    //last name
    public void enterLastName(String last) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastName)
        ).sendKeys(last);
    }


    //select vacancy
    public void selectVacancy(String vacancyName) {

        wait.until(
                ExpectedConditions.elementToBeClickable(vacancyDropdown)
        ).click();

        By vacancyOption = By.xpath(
                "//div[@role='listbox']//span[normalize-space()='"
                        + vacancyName + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(vacancyOption)
        ).click();
    }


    //enter email
    public void enterEmail(String emailAddress) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(email)
        ).sendKeys(emailAddress);
    }
}
