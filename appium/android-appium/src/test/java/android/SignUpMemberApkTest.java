package android;

import com.mailslurp.clients.ApiException;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BaseSetupAndroid;
import pages.SignUpPage;

public class SignUpMemberApkTest {
    private  SignUpPage signUpPage;

    @BeforeMethod
    public void setup() {
        AppiumDriver driver = BaseSetupAndroid.getAndroidDriver("false");
        signUpPage = new SignUpPage(driver);
    }

    @Test(priority = 1)
    public void verifySignUp() throws ApiException {
        signUpPage.signUpMember("android");
    }

    @Test(priority = 2)
    public void verifyHomePage() {
        signUpPage.verifyHomePageAfterMemberSignUp();
    }
}
