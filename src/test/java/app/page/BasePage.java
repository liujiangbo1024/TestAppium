/**
 * @Project Name :  testAppium
 * @Package Name :  app.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-24 19:41
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package app.page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description  :  一个公共的类
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-24 19:41
 */
public class BasePage {
    public static AndroidDriver driver;


    public static WebElement findElement(By by) {
        //todo:递归的更好
        System.out.println("找到元素为："+by);
        try {
            return  driver.findElement(by);
        } catch (Exception e) {
            handleAlter();

            //再次回到
            return driver.findElement(by);
        }
    }


    public static void findElementAndClick(By by) {
        //todo:递归的更好，怎么递归？？多个弹框如何处理
        //todo :如果定位的元素是动态变化的
        System.out.println("点击元素为：" + by);
        try {
            driver.findElement(by).click();

        } catch (Exception e) {
            // 异常调试方法如下：
            // System.out.println(driver.getPageSource());//打印出整个页面,打印出当前布局的所有控件
           /* for (AndroidElement element : driver.findElementsByXPath("//*")) {
                System.out.println(element.getTagName());
                System.out.println(element.getText());

            }*/
            handleAlter();//处理所有的弹框
            //再次回到
            driver.findElement(by).click();
        }
    }


    public static List<WebElement> findElemets(By by){
        System.out.println("找到元素集"+by);

        List<WebElement> elementList= driver.findElements(by);
        if(elementList.size()==0){
            handleAlter();
            elementList= driver.findElements(by);
        }
        return elementList;
    }
    //各种弹框的处理
    static void handleAlter() {
        By tips=By.id("com.xueqiu.android:id/snb_tip_text");//弹框手势
        List<By> alterBoxs = new ArrayList<>();
        //todo:不需要所有的都判断是否存在
        alterBoxs.add(By.id("com.xueqiu.android:id/image_cancel"));//升级框出现,
        //alterBoxs.add(By.id("com.xueqiu.android:id/update_id_ok"));//后台更新
        alterBoxs.add(tips);//自选股票页面手势框,找到这个后不能直接点击，需要点击任意地方
        alterBoxs.add(By.id("com.xueqiu.android:id/md_buttonDefaultNegative"));//App的评价页面，需要点击下次再说(添加选中的股票)
        //adb shell pm clear com.xueqiu.android 清理android的缓存
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //判断一下存在定位符在点击
        alterBoxs.forEach(alter -> {
            List<WebElement> ads = driver.findElements(alter);
            if (alter.equals(tips)) {
                System.out.println("snb_tip found ");
                Dimension size = driver.manage().window().getSize();
                try {
                    // 2次判断在不在，存在在进行点击，不在不执行（解决那个点击错误的情况）
                    if (driver.findElements(tips).size() >= 1) {
                        new TouchAction(driver).tap(PointOption.point(size.width / 2, size.height / 2)).perform();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("snb_tip clicked");
                }
            } else if (ads.size() >= 1) {
                System.out.println("ads.get(0)===="+ads.get(0));
                ads.get(0).click();
            }
        });

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
}