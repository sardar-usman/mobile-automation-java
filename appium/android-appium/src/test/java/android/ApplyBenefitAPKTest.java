package android;

import com.mailslurp.clients.ApiException;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;
import pages.ApplyBenefitPage;
import pages.BaseSetupAndroid;
import pages.CommonPage;

public class ApplyBenefitAPKTest {

    AppiumDriver driver = BaseSetupAndroid.getAndroidDriver("true");
    CommonPage commonPage = new CommonPage(driver);
    ApplyBenefitPage applyBenefitPage = new ApplyBenefitPage(driver);

    @Test
    public void ApplyBenefit() throws ApiException {
        commonPage.setPrefLang("android", "English");
        commonPage.signIn("mailslurptrain1", "*%NkOnJsH4*%Nk");
        applyBenefitPage.applyBenefit();
    }
}
