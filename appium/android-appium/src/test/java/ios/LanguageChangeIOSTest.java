package ios;

import io.appium.java_client.AppiumDriver;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.BaseSetupIOS;
import pages.CommonPage;
import pages.LanguageChangePage;

import java.io.IOException;

public class LanguageChangeIOSTest {
    AppiumDriver driver = BaseSetupIOS.getIOSDriver();
    LanguageChangePage languageChangePage = new LanguageChangePage(driver);
    CommonPage commonPage = new CommonPage(driver);

    @Test(priority = 1)
    public void setRussianPrefLang() throws IOException, ParseException {
        languageChangePage.changePrefLang("ios", "Russian");
    }

//    @Test(priority = 2)
//    public void setSpanishPrefLang() throws IOException, ParseException {
//        languageChangePage.changePrefLang("ios", "Spanish");
//    }
//
//    @Test(priority = 3)
//    public void setArabicPrefLang() throws IOException, ParseException {
//        languageChangePage.changePrefLang("ios", "Arabic");
//    }
//
//    @Test(priority = 4)
//    public void setFrenchPrefLang() throws IOException, ParseException {
//        languageChangePage.changePrefLang("ios","French");
//    }
//
//    @Test(priority = 5)
//    public void setPortuguesePrefLang() throws IOException, ParseException {
//        languageChangePage.changePrefLang("ios","Portuguese");
//    }
//
//    @Test(priority = 6)
//    public void setIndonesianPrefLang() throws IOException, ParseException {
//        languageChangePage.changePrefLang("ios","Indonesian");
//    }
//
//    @Test(priority = 7)
//    public void setThaiPrefLang() throws IOException, ParseException {
//        languageChangePage.changePrefLang("ios", "Thai");
//    }
//
//    @Test(priority = 8)
//    public void setKoreanPrefLang() throws IOException, ParseException {
//        languageChangePage.changePrefLang("ios", "Korean");
//    }
//
//    @Test(priority = 9)
//    public void setVietnamesePrefLang() throws IOException, ParseException {
//        languageChangePage.changePrefLang("ios", "Vietnamese");
//
//    }
//
//    @Test(priority = 10)
//    public void setEnglishPrefLang() throws IOException, ParseException {
//        languageChangePage.changePrefLang("ios","English");
//    }
}
