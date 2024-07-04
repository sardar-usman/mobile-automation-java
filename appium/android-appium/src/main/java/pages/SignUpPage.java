package pages;

import com.github.javafaker.Faker;
import com.mailslurp.apis.EmailControllerApi;
import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiException;
import com.mailslurp.models.Email;
import com.mailslurp.models.EmailTextLinesResult;
import com.mailslurp.models.InboxDto;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class SignUpPage extends BasePage {

    private static final Integer TIMEOUT_MILLIS = 3000;
    private static final Boolean UNREAD_ONLY = true;
    CommonPage commonPage = new CommonPage(driver);
    Faker faker = new Faker();
    String fullName = faker.name().fullName();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userName = firstName + lastName;
    String orgName = faker.company().name();
    String password = "*%NkOnJsH4*%Nk";
    String businessCode = faker.number().digits(5);

    InboxControllerApi inboxControllerApi = new InboxControllerApi();
    WaitForControllerApi waitForControllerApi = new WaitForControllerApi();

    WebDriverWait wait;

    public SignUpPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement getWelcomeSignUpButtonSelector() {
        return driver.findElement(AppiumBy.accessibilityId("welcome_sign_up"));
    }

    public WebElement getFullNameSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter Full Name\")"));
    }

    public WebElement getMemberEmailSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter Email\")"));
    }

    public WebElement getPartnerEmailSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter Email Address\")"));
    }

    public WebElement getUserNameSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter User Name\")"));
    }

    public WebElement getPasswordSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter password\")"));
    }

    public WebElement getOrganizationNameSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter Organization Name\")"));
    }

    public WebElement getAuthorisedNameSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\" Enter Authorised Person Name\")"));
    }

    public WebElement getHubDropDownSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Select hub\")"));
    }

    public WebElement getSearchHereSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Search Here\")"));
    }

    public WebElement selectHub() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Dubai\")"));
    }

    public WebElement getContactNoSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter Contact No.\")"));
    }

    public WebElement getBusinessCodeSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView( new UiSelector().text(\"Enter Mirsal 2 Code\"))"));
    }

    public WebElement getJobTitleSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Enter Person's Job Title\")"));
    }

    public WebElement getPhoneCodeSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Code\")"));
    }

    public WebElement getCountrySelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Select the Country\")"));
    }

    public WebElement selectPhoneCode() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"United Arab Emirates - 971\")"));

    }

    public WebElement getSignUpButtonSelector() {
        return driver.findElement(AppiumBy.accessibilityId("sign_up"));
    }

    public WebElement agreeConditionsCheckbox() {
        return driver.findElement(AppiumBy.accessibilityId("i_have_agreed_unchecked"));
    }

    public WebElement getIOSJoinMemberSelector() {
        return driver.findElement(AppiumBy.accessibilityId("member_join_box"));
    }

    public WebElement getPartnerSignUpButtonSelector() {
        return driver.findElement(AppiumBy.accessibilityId("partner_sign_up"));
    }

    public void signUpMember(String device) throws ApiException {
        //create mailslurp email address
        InboxDto inbox = inboxControllerApi.createInboxWithDefaults();
        String emailAddress = inbox.getEmailAddress();

        wait.until(ExpectedConditions.elementToBeClickable(this.getWelcomeSignUpButtonSelector())).click();
        if(device.equals("ios")) {
            wait.until(ExpectedConditions.elementToBeClickable(this.getIOSJoinMemberSelector())).click();
        } else {
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"MEMBER\")")).click();
        }
        this.getFullNameSelector().sendKeys(fullName);
        this.getMemberEmailSelector().sendKeys(emailAddress);
        this.getUserNameSelector().sendKeys(userName);
        this.getPasswordSelector().sendKeys(password);
        this.getOrganizationNameSelector().sendKeys(orgName);
        this.getHubDropDownSelector().click();
        this.getSearchHereSelector().sendKeys("dubai");
        this.selectHub().click();
        this.getContactNoSelector().sendKeys("987654321");
        this.getBusinessCodeSelector().sendKeys("AE-" + businessCode);
        this.agreeConditionsCheckbox().click();
        this.getSignUpButtonSelector().click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Congratulations!\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Member Registered successfully. Press Continue to access the available features.\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Continue\")")).click();

        //Get Email Data and Verify it's Content.
        Email partnerEmail = waitForControllerApi.waitForLatestEmail(inbox.getId(), TIMEOUT_MILLIS.longValue(), UNREAD_ONLY, null, null, null, null);

        EmailTextLinesResult memberEmailText1 = new EmailControllerApi().getEmailHTMLQuery(partnerEmail.getId(), "table:nth-child(2) tbody:nth-child(1) td p:nth-child(1)");
        assertEquals(memberEmailText1.getLines().get(0), "Dear " + fullName + ",");

        EmailTextLinesResult memberEmailText2 = new EmailControllerApi().getEmailHTMLQuery(partnerEmail.getId(), "table:nth-child(2) tbody:nth-child(1) td p:nth-child(2)");
        assertEquals(memberEmailText2.getLines().get(0), "Greetings from the World Logistics Passport Team!");

        EmailTextLinesResult memberEmailText3 = new EmailControllerApi().getEmailHTMLQuery(partnerEmail.getId(), "table:nth-child(2) tbody:nth-child(1) td p:nth-child(3)");
        String requestNo = (memberEmailText3.getLines().get(0));
        String regexPattern = "[^0-9]";
        String replacementString = " ";
        requestNo = requestNo.replaceAll(regexPattern, replacementString);
        requestNo = requestNo.replaceAll(" ", "");

        assertEquals(memberEmailText3.getLines().get(0), "Thank you for signing up with WLP Program. Please note your request number is " + requestNo);

        EmailTextLinesResult memberEmailText4 = new EmailControllerApi().getEmailHTMLQuery(partnerEmail.getId(), "table:nth-child(2) tbody:nth-child(1) td p:nth-child(4)");
        assertEquals(memberEmailText4.getLines().get(0), "You can use your credentials to login into the platform and explore the WLP benefits.");
    }

    public void signUpPartner() throws ApiException {
        //create mailslurp email address
        InboxDto inbox = inboxControllerApi.createInboxWithDefaults();
        String emailAddress = inbox.getEmailAddress();

        this.getWelcomeSignUpButtonSelector().click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"PARTNER\")")).click();

        this.getOrganizationNameSelector().sendKeys(orgName);
        this.getAuthorisedNameSelector().sendKeys(fullName);
        this.getJobTitleSelector().sendKeys("CEO");
        this.getPartnerEmailSelector().sendKeys(emailAddress);
        this.getPhoneCodeSelector().click();
        this.getSearchHereSelector().sendKeys("united arab emirates");
        this.wait.until(ExpectedConditions.elementToBeClickable(this.selectPhoneCode())).click();
        this.wait.until(ExpectedConditions.elementToBeClickable(this.selectPhoneCode())).click();
        this.getContactNoSelector().sendKeys("987654321");
        this.getCountrySelector().click();
        this.getSearchHereSelector().sendKeys("united arab emirates");
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"United Arab Emirates\")")).click();
        driver.findElement(AppiumBy.accessibilityId("i_have_not_agreed")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView( new UiSelector().text(\"Sign Up\"))"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wait.until(ExpectedConditions.elementToBeClickable(this.getPartnerSignUpButtonSelector())).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Congratulations!\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Partner has registered successfully. Please proceed to the registered email to continue.\")")).isDisplayed();

        driver.findElement(AppiumBy.accessibilityId("pop_up_close")).click();

        //Get Email Data and Verify it's Content.
        Email memberEmail = waitForControllerApi.waitForLatestEmail(inbox.getId(), TIMEOUT_MILLIS.longValue(), UNREAD_ONLY, null, null, null, null);

        EmailTextLinesResult partnerEmailText1 = new EmailControllerApi().getEmailHTMLQuery(memberEmail.getId(), "table:nth-child(2) tbody:nth-child(1) td p:nth-child(1)");
        assertEquals(partnerEmailText1.getLines().get(0), "Dear Partner,");

        EmailTextLinesResult partnerEmailText2 = new EmailControllerApi().getEmailHTMLQuery(memberEmail.getId(), "table:nth-child(2) tbody:nth-child(1) td p:nth-child(2)");
        assertEquals(partnerEmailText2.getLines().get(0), "We are delighted to welcome you as a Partner to the World Logistics Passport (WLP) program and are looking forward to collaborating with you.");

        EmailTextLinesResult partnerEmailText3 = new EmailControllerApi().getEmailHTMLQuery(memberEmail.getId(), "table:nth-child(2) tbody:nth-child(1) td p:nth-child(3)");
        assertEquals(partnerEmailText3.getLines().get(0), "You will shortly receive the Partners Registration Pack by email.");

        EmailTextLinesResult partnerEmailText4 = new EmailControllerApi().getEmailHTMLQuery(memberEmail.getId(), "table:nth-child(2) tbody:nth-child(1) td p:nth-child(4)");
        assertEquals(partnerEmailText4.getLines().get(0), "The WLP Team is at your disposal to facilitate the on-boarding process.");
    }

    public void  verifyHomePageAfterMemberSignUp() {
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Welcome!\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Membership Under Review\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"You will be able to view your trade growth and assigned tier shortly\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Complete your Profile\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Fill out the questionnaire to complete it\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Activity\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Dashboard\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Benefits\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Partners\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Hubs\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"My Requests\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Reports\")")).isDisplayed();
    }
}