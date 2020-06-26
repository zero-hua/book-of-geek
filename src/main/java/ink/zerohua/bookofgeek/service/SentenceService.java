package ink.zerohua.bookofgeek.service;

import ink.zerohua.bookofgeek.entity.Sentence;
import ink.zerohua.bookofgeek.mapper.SentenceMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: book-of-geek
 * @author: zerohua
 * @create: 2020-06-20 18:39
 **/
@EnableScheduling   //开启定时任务
@Service
public class SentenceService {

    @Autowired
    private SentenceMapper sentenceMapper;

    private Integer index = 1;

    public Sentence getTodaySentence(){
        return sentenceMapper.getTodaySentence(index);
    }

    //每天0点换句子
    @Scheduled(cron = "00 00 00 * * ?")
    private void addIndex(){
        this.index++;
    }
}
