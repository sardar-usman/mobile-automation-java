package pages;

import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseSetupIOS {
    public static AppiumDriver getIOSDriver() {
        AppiumDriver driver = null;
        WebDriverWait wait;

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setApiKey("ee3db81cd43d25188151e0261e24fc172cb5b2ed6849af99380def5909ce2246");

        try {
            final XCUITestOptions caps = getXcuiTestOptions();
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723"),caps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static XCUITestOptions getXcuiTestOptions() {
        XCUITestOptions caps = new XCUITestOptions();
        caps.setPlatformVersion("17.0.1");
        caps.setPlatformName("iOS");
        caps.setDeviceName("iPhone-1");
        caps.setUdid("990F538A-E42C-4DC7-A05C-C209324DE3B4");
        caps.setAutomationName("XCUITest");
        caps.setBundleId("com.dpw.wlp");
        caps.setAutoAcceptAlerts(true);
        caps.setCapability("autoGrantPermissions", true);
        caps.setNoReset(true);
        caps.setFullReset(false);
        caps.setCapability("appium:forceAppLaunch", true);
        caps.setCapability("appium:shouldTerminateApp", false);
//        caps.setApp(System.getProperty("user.dir")+ "/src/test/resources/WLP.app");
        return caps;
    }
}
