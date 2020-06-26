package ink.zerohua.bookofgeek.autoservice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @program: book-of-geek
 * @describletion: 实现万恶的打卡，与本项目没一毛钱关系
 * @author: zerohua
 * @create: 2020-06-20 15:14
 **/
@Configuration
@EnableScheduling   // 2.开启定时任务
public class YiBanAuto {
    //3.添加定时任务 每天 23:30:10执行
    @Scheduled(cron = "30 10 23 * * ?")
    private void configureTasks() {

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(Boolean.TRUE);
        options.addArguments("--headless");
        options.addArguments("no-sandbox");

        System.setProperty(
                "webdriver.chrome.driver", "src/main/resources/driver/ChromeDriver/chromedriver.exe");

        WebDriver driver = new ChromeDriver(options);


        driver.get("http://xggl.hnie.edu.cn/content/menu/student/temp/zzdk?_t_s_=1592148510073");

        String username = "201803120103";
        String password = "250536";
        //是否打卡成功，默认成功，任意环节出错变为失败

        try {
            Thread.sleep(3000);
            WebElement signInBox = driver.findElement(By.cssSelector("button > span"));
            signInBox.click();

            WebElement usernameElement = driver.findElement(By.id("username"));
            usernameElement.sendKeys(username);

            WebElement passwordElement = driver.findElement(By.id("password"));
            passwordElement.sendKeys(password);

            WebElement submit = driver.findElement(By.name("submit"));
            System.out.println(submit.getText());
            submit.click();

            Thread.sleep(3000);

            //窗口切换，获取窗口句柄
            String winHandleBefore = driver.getWindowHandle();
            for(String winHandle : driver.getWindowHandles()) {
                if (winHandle.equals(winHandleBefore)) {
                    continue;
                }
                driver.switchTo().window(winHandle);
                break;
            }

            WebElement link = driver.findElement(By.cssSelector("ul.sidebar-menu>li:nth-child(2)>a"));
            link.click();

            WebElement btn = driver.findElement(By.id("dk_btn"));
            btn.click();

            WebElement save = driver.findElement(By.id("save"));
            Thread.sleep(3000);
            save.click();
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
    }
}