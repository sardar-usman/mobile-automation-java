package pages;

import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class BaseSetupAndroid {

    private static AppiumDriver driver;

    public static AppiumDriver getAndroidDriver(String fullReset) {
        if (driver  == null) {
            ApiClient defaultClient = Configuration.getDefaultApiClient();
            defaultClient.setApiKey("ee3db81cd43d25188151e0261e24fc172cb5b2ed6849af99380def5909ce2246");
            try {
                final UiAutomator2Options caps = getUiAutomator2Options(fullReset);
                driver = new AppiumDriver(new URL("http://127.0.0.1:4723"),caps);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    private static UiAutomator2Options getUiAutomator2Options(String fullReset) {
        UiAutomator2Options caps = new UiAutomator2Options();
        caps.setPlatformVersion("13");
        caps.setPlatformName("Android");
        caps.setDeviceName("Android Emulator");
        caps.setAutomationName("UiAutomator2");
//        caps.setAppActivity("com.wlp.MainActivity");
//        caps.setAppPackage("com.dpworld.wlp");
        if (fullReset.equals("true")) {
            caps.setNoReset(false);
            caps.setFullReset(true);
            caps.setApp(System.getProperty("user.dir")+ "/src/test/resources/WLP_27_6.apk");

        } else  {
            caps.setNoReset(true);
            caps.setFullReset(false);
//            caps.setApp(System.getProperty("user.dir")+ "/src/test/resources/WLP_27_6.apk");
            caps.setCapability("appium:forceAppLaunch", true);
            caps.setCapability("appium:shouldTerminateApp", false);
        }


        return caps;
    }

    public static void resetDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
