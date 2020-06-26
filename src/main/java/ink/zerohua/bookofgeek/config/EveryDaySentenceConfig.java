package ink.zerohua.bookofgeek.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

/*
        “30 * * * * ?” 每半分钟触发任务
        “30 10 * * * ?” 每小时的10分30秒触发任务
        “30 10 1 * * ?” 每天1点10分30秒触发任务
        “30 10 1 20 * ?” 每月20号1点10分30秒触发任务
        “30 10 1 20 10 ? *” 每年10月20号1点10分30秒触发任务
        “30 10 1 20 10 ? 2011” 2011年10月20号1点10分30秒触发任务
        “30 10 1 ? 10 * 2011” 2011年10月每天1点10分30秒触发任务
        “30 10 1 ? 10 SUN 2011” 2011年10月每周日1点10分30秒触发任务
        “15,30,45 * * * * ?” 每15秒，30秒，45秒时触发任务
        “15-45 * * * * ?” 15到45秒内，每秒都触发任务
        “15/5 * * * * ?” 每分钟的每15秒开始触发，每隔5秒触发一次
        “15-30/5 * * * * ?” 每分钟的15秒到30秒之间开始触发，每隔5秒触发一次
        “0 0/3 * * * ?” 每小时的第0分0秒开始，每三分钟触发一次
        “0 15 10 ? * MON-FRI” 星期一到星期五的10点15分0秒触发任务
        “0 15 10 L * ?” 每个月最后一天的10点15分0秒触发任务
        “0 15 10 LW * ?” 每个月最后一个工作日的10点15分0秒触发任务
        “0 15 10 ? * 5L” 每个月最后一个星期四的10点15分0秒触发任务
        “0 15 10 ? * 5#3”

*/
/**
 * @program: book-of-geek
 * @author: zerohua
 * @create: 2020-06-14 23:02
 **/
//@Configuration
//@EnableScheduling   // 2.开启定时任务
public class  EveryDaySentenceConfig {

    //3.添加定时任务
    @Scheduled(cron = "30 10 23 * * ?")
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        System.setProperty(
                "webdriver.chrome.driver", "src/main/resources/driver/ChromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.1juzi.com/new/54812.html");

        List<WebElement> elements = driver.findElements(By.cssSelector("p:nth-of-type(2)"));

        for (WebElement element : elements) {
            System.out.println(element.getText());
        }
        driver.close();
    }
}

