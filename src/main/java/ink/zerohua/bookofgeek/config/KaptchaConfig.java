package ink.zerohua.bookofgeek.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import ink.zerohua.bookofgeek.utils.Kaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @program: book-of-geek
 * @author: zerohua
 * @create: 2020-06-05 11:31
 **/

@Configuration
public class KaptchaConfig {

    @Bean
    public Kaptcha getCaptchaBean(){
        Kaptcha kaptcha = new Kaptcha();
        return kaptcha;
    }

}