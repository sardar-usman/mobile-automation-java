package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonPage extends BasePage {

    private final WebDriverWait wait;

    public CommonPage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public WebElement getAndroidSearchPrefLangSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Search Language Here\")"));
    }

    public WebElement getIOSSearchPrefLangSelector() {
        return driver.findElement(By.xpath("(//XCUIElementTypeOther[@name=\"search_field_view\"])[4]"));
    }

    public WebElement getEnglishPrefLangSelector() {
        return driver.findElement(AppiumBy.accessibilityId("lang_text_English"));
    }

    public WebElement getApplyButtonSelector() {
        return driver.findElement(AppiumBy.accessibilityId("apply_button"));
    }

    public WebElement getAllowWLPAccessLocationSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"While using the app\")"));
    }

    public WebElement getIOSAllowWLPAccessLocationSelector() {
        return driver.findElement(By.xpath("XCUIElementTypeButton[@label='Allow While Using App'])"));
    }

    public WebElement getSignInButtonSelector() {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Sign In\")"));
    }

    public WebElement getUserNameSelector() {
        return driver.findElement(AppiumBy.accessibilityId("User Name_field"));
    }

    public WebElement getPasswordSelector() {
        return driver.findElement(AppiumBy.accessibilityId("Password_field"));
    }

    public void setPrefLang(String device, String prefLang) {
        if (device.equals("android")) {
            wait.until(ExpectedConditions.elementToBeClickable(this.getAndroidSearchPrefLangSelector())).sendKeys(prefLang);
        } else  {
            wait.until(ExpectedConditions.elementToBeClickable(this.getIOSSearchPrefLangSelector())).sendKeys(prefLang);
        }

        wait.until(ExpectedConditions.elementToBeClickable(this.getEnglishPrefLangSelector())).click();
        this.getApplyButtonSelector().click();

        if (device.equals("android")) {
            this.getAllowWLPAccessLocationSelector().click();
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            final String iosLocator = "**/XCUIElementTypeButton[`name == \"Allow Once\"`]";
            driver.setSetting("acceptAlertButtonSelector", iosLocator);
            try {
                driver.switchTo().alert().accept();
            } catch (Exception ignored) {
            }
        }
    }

    public void signIn(String username, String password) {
        this.getSignInButtonSelector().click();
        this.getUserNameSelector().sendKeys(username);
        this.getPasswordSelector().sendKeys(password);
        this.getSignInButtonSelector().click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Activity\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Dashboard\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Benefits\")")).isDisplayed();
    }
 }
