package ink.zerohua.bookofgeek.service;

import ink.zerohua.bookofgeek.entity.News;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: book-of-geek
 * @describletion: 每天从36氪爬取新闻  https://36kr.com
 * @author: zerohua
 * @create: 2020-06-20 19:29
 **/
@EnableScheduling   // 开启定时任务
@Service
public class ScienceNewsService {

    private List<News> newsList;

    public ScienceNewsService() {
        this.newsList = new ArrayList<News>();
        updateNews();
    }

    public List<News> getTodayNewses(){
        return newsList;
    }

    /**
    * @Description: 更新和初始化新闻集合，设定定时任务，每天0点更新
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/6/20 20:40
    */
    @Scheduled(cron = "00 00 00 * * ?")
    private void updateNews(){
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(Boolean.TRUE);
        options.addArguments("--headless");
        options.addArguments("no-sandbox");

        System.setProperty(
                "webdriver.chrome.driver", "src/main/resources/driver/ChromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://36kr.com/newsflashes");

        List<WebElement> webElements = driver.findElements(By.cssSelector("div.flow-item a.item-title"));

        for(int index=0;index<10;index++){
            WebElement webElement = webElements.get(index);
            News news = new News();
            news.setTitle(webElement.getText());
            news.setUrl(webElement.getAttribute("href"));
            newsList.add(news);
        }
        driver.close();
    }
}
