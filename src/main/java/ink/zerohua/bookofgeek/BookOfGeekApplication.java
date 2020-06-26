package ink.zerohua.bookofgeek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BookOfGeekApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookOfGeekApplication.class, args);
    }

}
