package ios;

import com.mailslurp.clients.ApiException;
import io.appium.java_client.AppiumDriver;
import org.junit.Test;
import pages.BaseSetupIOS;
import pages.CommonPage;
import pages.SignUpPage;

public class SignUpMemberIOSTest {
    AppiumDriver driver = BaseSetupIOS.getIOSDriver();
    SignUpPage signUpPage = new SignUpPage(driver);
    CommonPage commonPage = new CommonPage(driver);

    @Test
    public void verifySignUp() throws ApiException {
        commonPage.setPrefLang("ios", "English");
        signUpPage.signUpMember("ios");
    }

    @Test
    public void verifyHomePage() {
        signUpPage.verifyHomePageAfterMemberSignUp();
    }
}
