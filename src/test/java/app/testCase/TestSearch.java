/**
 * @Project Name :  testAppium
 * @Package Name :  app.testCase
 * @Description :  TODO
 * @author :  liujiangbo
 * @Creation Date:  2019-11-24 18:59
 * @ModificationHistory Who    When    What
 * --------  ---------  --------------------------
 */
package app.testCase;

import app.page.App;
import app.page.SearchPage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * @Description  :  junit4的单元测试框架
 * @author       :  liujiangbo
 * @Creation Date:  2019-11-24 18:59
 */
@RunWith(Parameterized.class)
public class TestSearch {
    public static SearchPage searchPage;
    @BeforeClass
    public static void beforeAll()  throws MalformedURLException {
        App.start();

    }

    //参数化 junit4的参数化文档
    @Parameterized.Parameters
    public static Collection<Object[]> data() throws IOException {
        return Arrays.asList(new Object[][]{
                {"alibaba",100f},
                {"xiaomi",8f},
                {"jd",33f},
        });

    }
    @Parameterized.Parameter(0)
    public String stock;

    @Parameterized.Parameter(1)
    public float price;

    @Before
    //每次搜索之前，要先进入搜索页面
    public void brfore(){
        searchPage=App.toSearch();
    }

    @Test
    public void search(){
        //assertThat(searchPage.search("alibaba").getCurrentPrice(),greaterThanOrEqualTo(10.233F));
        assertThat(searchPage.search(stock).getCurrentPrice(),greaterThanOrEqualTo(price));

    }
    @After
    //每次执行之后，点击一起取消，回到app home页面
    public void after(){
        searchPage.cancel();
    }

}