/**
 * @Project Name :  testAppium
 * @Package Name :  app.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-24 19:15
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package app.page;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

/**
 * @Description  :  查询页面
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-24 19:15
 */
public class SearchPage extends BasePage {

    private By inputBox=By.id("com.xueqiu.android:id/search_input_text");

    //查询
    public SearchPage search(String keyWord) {

        try {
            App.driver.findElement(inputBox).sendKeys(keyWord);
        } catch (Exception e) {
            handleAlter();//版本升级
            App.driver.findElement(inputBox).sendKeys(keyWord);
        }
        //findElementAndClick(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]/android.widget.LinearLayout/android.widget.TextView[1]"));
        findElementAndClick(By.id("com.xueqiu.android:id/name"));
        return this;
    }

    //获取所有股票
    public Float getCurrentPrice() {
        MobileElement el8 = (MobileElement) findElement(By.id("com.xueqiu.android:id/current_price"));
      //  el8.click();
        return Float.valueOf(el8.getText());
    }

    //取消
    public void cancel() {
        findElementAndClick(By.id("com.xueqiu.android:id/action_close"));
    }
    //添加自选
    public SearchPage addSelect(){
        findElementAndClick(By.id("com.xueqiu.android:id/follow_btn"));
        return new SearchPage();
    }
}