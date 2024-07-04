package android;

import io.appium.java_client.AppiumDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BaseSetupAndroid;
import pages.LanguageChangePage;

import java.io.IOException;

public class LanguageChangeApkTest {

    private LanguageChangePage languageChangePage;

    @BeforeMethod
    public void setup() {
        BaseSetupAndroid.resetDriver();
        AppiumDriver driver = BaseSetupAndroid.getAndroidDriver("true");
        languageChangePage = new LanguageChangePage(driver);
    }

//    @AfterMethod
//    public void tearDown() {
//        BaseSetupAndroid.resetDriver();
//    }


    @Test(priority = 1)
    public void setRussianPrefLang() throws IOException, ParseException {
        languageChangePage.changePrefLang("android", "Russian");
    }

    @Test(priority = 2)
    public void setSpanishPrefLang() throws IOException, ParseException {
        languageChangePage.changePrefLang("android","Spanish");
    }

    @Test(priority = 3)
    public void setArabicPrefLang() throws IOException, ParseException {
        languageChangePage.changePrefLang("android","Arabic");
    }

    @Test(priority = 4)
    public void setFrenchPrefLang() throws IOException, ParseException {
        languageChangePage.changePrefLang("android","French");
    }

    @Test(priority = 5)
    public void setPortuguesePrefLang() throws IOException, ParseException {
        languageChangePage.changePrefLang("android","Portuguese");
    }

    @Test(priority = 6)
    public void setIndonesianPrefLang() throws IOException, ParseException {
        languageChangePage.changePrefLang("android","Indonesian");
    }

    @Test(priority = 7)
    public void setThaiPrefLang() throws IOException, ParseException {
        languageChangePage.changePrefLang("android","Thai");
    }

    @Test(priority = 8)
    public void setKoreanPrefLang() throws IOException, ParseException {
        languageChangePage.changePrefLang("android","Korean");
    }

    @Test(priority = 9)
    public void setVietnamesePrefLang() throws IOException, ParseException {
        languageChangePage.changePrefLang("android","Vietnamese");
    }

    @Test(priority = 10)
    public void setEnglishPrefLang() throws IOException, ParseException {
        languageChangePage.changePrefLang("android", "English");
    }
}
