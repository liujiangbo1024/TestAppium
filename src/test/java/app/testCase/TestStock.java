/**
 * @Project Name :  testAppium
 * @Package Name :  app.testCase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-25 10:30
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package app.testCase;

import app.page.App;
import app.page.StockPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.MalformedURLException;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @Description  :  测试股票页面 使用junit5
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-25 10:30
 */
public class TestStock {
    private static StockPage stockPage;

    @BeforeAll
    public static void beforeAll() throws MalformedURLException {
        App.start();
        stockPage=App.toStock();
    }
    @BeforeEach
    public void beforeEach(){

    }

    @Order(2)
    @Test
    public void addDefaultSelectedStocks() {
        //先清除默认的股票
        if (stockPage.getAllStocks().size()> 1) {
            stockPage.deleteAll();
        }
       assertThat(stockPage.addDefaultSelectedStocks().getAllStocks().size(),greaterThanOrEqualTo(6));
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("data")
    public void addSelected(String code,String name){
        stockPage.toSearch().search(code).addSelect().cancel();
        assertThat(stockPage.getAllStocks(),hasItem(name));
    }

    public static Stream<Arguments> data(){
        return Stream.of(arguments("pdd","拼多多"),arguments("jd","京东"));
    }
}