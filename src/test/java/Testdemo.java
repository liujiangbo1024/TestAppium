package app;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Testdemo {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        /* noreset：以下2种方式都可以 避免权限弹框
       1. noReset：true数据缓存，弹框也会缓存，不重置APP的状态
       2. noReset：false：权限，弹框的也会清空+autoGrantPermissions：true也可以  */
        //权限弹框的处理
        desiredCapabilities.setCapability("noReset",false);
        desiredCapabilities.setCapability("autoGrantPermissions",true);
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        //先指定隐士等待，否则无法运行成功
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() {
        //权限弹框
 /*       MobileElement el1 = (MobileElement) driver.findElementById("com.xueqiu.android:id/tv_open");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        el3.click();*/
        //版本升级框
        MobileElement el4 = (MobileElement) driver.findElementById("com.xueqiu.android:id/image_cancel");
        el4.click();
        MobileElement el5 = (MobileElement) driver.findElementById("com.xueqiu.android:id/home_search");
        el5.click();
        MobileElement el6 = (MobileElement) driver.findElementById("com.xueqiu.android:id/search_input_text");
        el6.sendKeys("alibaba");
        MobileElement el7 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]");
        el7.click();
        MobileElement el8 = (MobileElement) driver.findElementById("com.xueqiu.android:id/current_price");
        el8.click();

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
