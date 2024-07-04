package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BaseSetupBS {
    public static AndroidDriver getAndroidDriver(){
        AndroidDriver driver = null;
        try {
            MutableCapabilities cap = new MutableCapabilities();
            HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
            cap.setCapability("app", "bs://a0991debdf8e5340d200c76be3921ab6bb7fbc60");
            cap.setCapability("browserName", "samsung");
            browserstackOptions.put("userName", "zohaibnaseer_XbvlwD");
            browserstackOptions.put("accessKey", "PhYrpW1iwungDRnqqg7N");
            browserstackOptions.put("osVersion", "13.0");
            browserstackOptions.put("deviceName", "Google Pixel 7");
            browserstackOptions.put("projectName", "WLP");
            browserstackOptions.put("buildName", "browserstack-build-1");
            browserstackOptions.put("appiumVersion", "2.0.0");
            browserstackOptions.put("debug", "true");
            cap.setCapability("bstack:options", browserstackOptions);

            driver = new AndroidDriver
                    (new URL("http://hub.browserstack.com/wd/hub"), cap);

        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
