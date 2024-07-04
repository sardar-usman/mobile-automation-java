package pages;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

public class LanguageChangePage extends BasePage {

    public LanguageChangePage(AppiumDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    CommonPage commonPage = new CommonPage(driver);

    public WebElement getLangIconSelector() {
        return driver.findElement(AppiumBy.accessibilityId("welcome_language_icon"));
    }

    public WebElement getSearchLangSelector() {
        return driver.findElement(AppiumBy.accessibilityId("Search Language Here_field"));
    }

    public WebElement getApplyButtonSelector() {
        return driver.findElement(AppiumBy.accessibilityId("apply_button"));
    }

    public void changePrefLang(String deviceName , String prefLang) throws IOException, ParseException {

        if (Objects.equals(prefLang, "Russian")) {
            if (deviceName.equals("android")) {
                this.getSearchLangSelector().sendKeys(prefLang);
            } else {
                driver.findElement(By.name("welcome_language_icon")).click();
                driver.findElement(By.name("Search Language Here_field")).click();
            }

            driver.findElement(AppiumBy.accessibilityId("lang_text_" + prefLang)).click();
            this.getApplyButtonSelector().click();
            commonPage.getAllowWLPAccessLocationSelector().click();

            this.verifyLangText("russianLang");
        }
        // Set Spanish Lang
        else if (Objects.equals(prefLang, "Spanish")) {
            this.getSearchLangSelector().sendKeys(prefLang);
            driver.findElement(AppiumBy.accessibilityId("lang_text_" + prefLang)).click();
            this.getApplyButtonSelector().click();
            commonPage.getAllowWLPAccessLocationSelector().click();

            this.verifyLangText("spanishLang");
        }

        // Set Arabic Lang
        else if (Objects.equals(prefLang, "Arabic")) {
            this.getSearchLangSelector().sendKeys(prefLang);
            driver.findElement(AppiumBy.accessibilityId("lang_text_" + prefLang)).click();
            this.getApplyButtonSelector().click();
            commonPage.getAllowWLPAccessLocationSelector().click();

            this.verifyLangText("arabicLang");
        }

        // Set French Lang
        else if (Objects.equals(prefLang, "French")) {
            this.getSearchLangSelector().sendKeys(prefLang);
            driver.findElement(AppiumBy.accessibilityId("lang_text_" + prefLang)).click();
            this.getApplyButtonSelector().click();
            commonPage.getAllowWLPAccessLocationSelector().click();

            this.verifyLangText("frenchLang");
        }

        // Set Portuguese Lang
        else if (Objects.equals(prefLang, "Portuguese")) {
            this.getSearchLangSelector().sendKeys(prefLang);
            driver.findElement(AppiumBy.accessibilityId("lang_text_" + prefLang)).click();
            this.getApplyButtonSelector().click();
            commonPage.getAllowWLPAccessLocationSelector().click();

            this.verifyLangText("portugueseLang");
        }

        // Set Indonesian Lang
        else if (Objects.equals(prefLang, "Indonesian")) {
            this.getSearchLangSelector().sendKeys(prefLang);
            driver.findElement(AppiumBy.accessibilityId("lang_text_" + prefLang)).click();
            this.getApplyButtonSelector().click();
            commonPage.getAllowWLPAccessLocationSelector().click();

            this.verifyLangText("indonesianLang");
        }

        // Set Thai Lang
        else if (Objects.equals(prefLang, "Thai")) {
            this.getSearchLangSelector().sendKeys(prefLang);
            driver.findElement(AppiumBy.accessibilityId("lang_text_" + prefLang)).click();
            this.getApplyButtonSelector().click();
            commonPage.getAllowWLPAccessLocationSelector().click();

            this.verifyLangText("thaiLang");
        }

         // Set Korean Lang
        else if (Objects.equals(prefLang, "Korean")) {
            this.getSearchLangSelector().sendKeys(prefLang);
            driver.findElement(AppiumBy.accessibilityId("lang_text_" + prefLang)).click();
            this.getApplyButtonSelector().click();
            commonPage.getAllowWLPAccessLocationSelector().click();

            this.verifyLangText("koreanLang");
        }

        // Set Vietnamese Lang
        else if (Objects.equals(prefLang, "Vietnamese")) {
            this.getSearchLangSelector().sendKeys(prefLang);
            driver.findElement(AppiumBy.accessibilityId("lang_text_" + prefLang)).click();
            this.getApplyButtonSelector().click();
            commonPage.getAllowWLPAccessLocationSelector().click();

            this.verifyLangText("vietnameseLang");
        }
        else if (Objects.equals(prefLang, "English")) {
            this.getSearchLangSelector().sendKeys(prefLang);
            driver.findElement(AppiumBy.accessibilityId("lang_text_" + prefLang)).click();
            this.getApplyButtonSelector().click();
            commonPage.getAllowWLPAccessLocationSelector().click();

            this.verifyLangText("englishLang");
        }
    }

    public void verifyLangText(String lang) throws IOException, ParseException {

        JSONObject jsonObject = (JSONObject) DataContent.getData();

        // Access JSON array
        JSONArray langArray = (JSONArray) jsonObject.get(lang);
        JSONObject langObject = (JSONObject) langArray.get(0); // Assuming only one object in the array

        // Access "text1" value
        String text1 = (String) langObject.get("text1");
        String text2 = (String) langObject.get("text2");
        String text3 = (String) langObject.get("text3");
        String text4 = (String) langObject.get("text4");
        String text5 = (String) langObject.get("text5");
        String text6 = (String) langObject.get("text6");
        String text7 = (String) langObject.get("text7");
        String text8 = (String) langObject.get("text8");
        String text9 = (String) langObject.get("text9");
        String text10 = (String) langObject.get("text10");
        String text11 = (String) langObject.get("text11");
        String text12 = (String) langObject.get("text12");

        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text1 + "\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text2 + "\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text3 + "\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text4 + "\")")).isDisplayed();

        if (lang.equals("arabicLang")) {

            //Swipe Page to Right
            WebElement pageSwipe1 = driver.findElements(By.className("android.view.ViewGroup")).get(1);
            ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId", ((RemoteWebElement) pageSwipe1).getId(),
                    "direction", "right",
                    "percent", 0.9,
                    "speed", "1000"
            ));

            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text5 + "\")")).isDisplayed();
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text6 + "\")")).isDisplayed();

            //Swipe Page to Right
            WebElement pageSwipe2 = driver.findElements(By.className("android.view.ViewGroup")).get(6);
            ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId", ((RemoteWebElement) pageSwipe2).getId(),
                    "direction", "right",
                    "percent", 0.75,
                    "speed", "1000"
            ));
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text7 + "\")")).isDisplayed();
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text8 + "\")")).isDisplayed();
        }
         else {
            //Swipe Page to Left
            WebElement pageSwipe1 = driver.findElements(By.className("android.view.ViewGroup")).get(1);
            ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId", ((RemoteWebElement) pageSwipe1).getId(),
                    "direction", "left",
                    "percent", 0.9,
                    "speed", "1000"
            ));
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text5 + "\")")).isDisplayed();
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text6 + "\")")).isDisplayed();

            //Swipe Page to Left
            WebElement pageSwipe2 = driver.findElements(By.className("android.view.ViewGroup")).get(5);
            ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId", ((RemoteWebElement) pageSwipe2).getId(),
                    "direction", "left",
                    "percent", 0.10,
                    "speed", "1000"
            ));
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text7 + "\")")).isDisplayed();
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text8 + "\")")).isDisplayed();
        }
        this.getLangIconSelector().click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text9 + "\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text10 + "\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text11 + "\")")).isDisplayed();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + text12 + "\")")).isDisplayed();
    }
}



