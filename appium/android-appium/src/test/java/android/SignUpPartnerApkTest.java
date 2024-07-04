package android;

import com.mailslurp.clients.ApiException;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BaseSetupAndroid;
import pages.CommonPage;
import pages.SignUpPage;

public class SignUpPartnerApkTest {
    private  SignUpPage signUpPage;
    private CommonPage commonPage;

    @BeforeMethod
    public void setup() {
        AppiumDriver driver = BaseSetupAndroid.getAndroidDriver("true");
        signUpPage = new SignUpPage(driver);
        commonPage = new CommonPage(driver);
    }

    @Test
    public void verifyPartnerSignUp() throws ApiException {
        commonPage.setPrefLang("android", "English");
        signUpPage.signUpPartner();
    }
}
