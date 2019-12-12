/**
 * @Project Name :  testAppium
 * @Package Name :  app.page
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-25 10:47
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package app.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  :  股票页面
 * 1. 删除默认有的股票-
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-25 10:47
 */
public class StockPage extends BasePage{

    //删除存在的所有股票
    public StockPage deleteAll() {

        findElementAndClick(By.id("com.xueqiu.android:id/edit_group"));//点击
        findElementAndClick(By.id("com.xueqiu.android:id/check_all"));//全选
        findElementAndClick(By.id("com.xueqiu.android:id/cancel_follow"));//取消关注
        findElementAndClick(By.id("com.xueqiu.android:id/md_buttonDefaultPositive"));//确定
        findElementAndClick(By.id("com.xueqiu.android:id/action_close"));//完成
        return this;
    }

    //获取所有股票
    public List<String> getAllStocks(){
        handleAlter();
        List<String> listStocks=new ArrayList<>();
        findElemets(By.id("com.xueqiu.android:id/portfolio_stockName")).forEach(element->{
            listStocks.add(((WebElement)element).getText());
        });

        System.out.println("listStock======"+listStocks);
        return listStocks;
    }

    //添加自选股票
    public StockPage addDefaultSelectedStocks(){
        findElementAndClick(By.id("com.xueqiu.android:id/add_to_portfolio_stock"));
        return  this;
    }

   //点击搜索框，查找股票
    public SearchPage toSearch(){
        findElementAndClick(By.id("com.xueqiu.android:id/action_search"));
        return new SearchPage();
    }

}