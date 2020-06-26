package ink.zerohua.bookofgeek.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Article {
    private Integer aId;
    private String title;
    private String author;
    private String content;
    //int会有初始值
    private int readCount;
    private int likeCount;
    private int collectCount;
    private Date startDate;
    private Date endDate;
}
