/**
 * @Project Name :  testAppium
 * @Package Name :  app
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-24 18:56
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package app.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @Description  :  启动
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-24 18:56
 */
public class App  extends BasePage{



    public static void start() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
       // desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555"); //mumu模拟器
        //emulator-5554
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
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

    public static SearchPage toSearch() {
       findElementAndClick(By.id("com.xueqiu.android:id/home_search"));
        return new SearchPage();

    }

    public static StockPage toStock() {

        //进入前先判断是否有 弹框，升级矿，评价框，在接下来的动作,重复多次去除弹框和手势框的影响：多次点击自选，排除影响
        for(int i=1;i<=2;i++) {
            //handleAlter();
            findElementAndClick(By.xpath("//*[contains(@resource-id,'tab_name') and @text='自选']"));
            System.out.println("走进toStock===");
    }
        return new StockPage();
    }
}