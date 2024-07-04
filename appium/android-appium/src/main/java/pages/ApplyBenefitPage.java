package pages;

import com.mailslurp.apis.EmailControllerApi;
import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiException;
import com.mailslurp.models.Email;
import com.mailslurp.models.EmailTextLinesResult;
import com.mailslurp.models.InboxByEmailAddressResult;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ApplyBenefitPage extends BasePage {

    private static final Integer TIMEOUT_MILLIS = 3000;
    private static final Boolean UNREAD_ONLY = true;

    WaitForControllerApi waitForControllerApi = new WaitForControllerApi();
    InboxControllerApi inboxControllerApi = new InboxControllerApi();

    public ApplyBenefitPage(AppiumDriver driver)  {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    WebElement getBenefitIconSelector() {
        return driver.findElement(AppiumBy.accessibilityId("benefitIcon"));
    }

    WebElement getOtherBenefitSelector() {
        return driver.findElement(AppiumBy.accessibilityId("Other Benefits"));
    }

    WebElement getSearchBenefitSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Search Benefit Here\")"));
    }

    WebElement getBenefitApplyButtonSelector() {
        return driver.findElement(AppiumBy.accessibilityId("applyButton"));
    }

    WebElement getSubjectTextFieldSelector() {
        return driver.findElement(AppiumBy.accessibilityId("Subject_field"));
    }

    WebElement getDescriptionTextFieldSelector() {
        return driver.findElement(AppiumBy.accessibilityId("Enter Description_field"));
    }

    WebElement getSubmitButtonSelector() {
        return driver.findElement(AppiumBy.accessibilityId("submit"));
    }

    public void applyBenefit() throws ApiException {
        InboxByEmailAddressResult inbox = inboxControllerApi.getInboxByEmailAddress("079e924c-0f54-49d5-b599-dd6bf59773e2@mailslurp.net");

        this.getBenefitIconSelector().click();
        this.getOtherBenefitSelector().click();
        this.getSearchBenefitSelector().sendKeys("mobile benefit 2");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Mobile Benefit 2\")")).click();


        //verify benefits details.
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Mobile Testing Partner\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Hub\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Last Availed\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Dubai\")")).isDisplayed();

        this.getBenefitApplyButtonSelector().click();
        this.getSubjectTextFieldSelector().sendKeys("Test Subject for Apply Benefit");
        this.getDescriptionTextFieldSelector().sendKeys("Test Description for Apply Benefit");
        this.getSubmitButtonSelector().click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Success!\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"You have succesfully applied for the benefit\")")).isDisplayed();

        //Get Email Data and Verify it's Content.
        Email partnerEmail = waitForControllerApi.waitForLatestEmail(inbox.getInboxId(), TIMEOUT_MILLIS.longValue(), UNREAD_ONLY, null, null, null, null);
        EmailTextLinesResult partnerEmailText1 = new EmailControllerApi().getEmailHTMLQuery(partnerEmail.getId(), "table:nth-child(2) tbody:nth-child(1) td p:nth-child(1)");
        assertEquals(partnerEmailText1.getLines().get(0), "Dear Mobile Testing Partner,");
    }
}
