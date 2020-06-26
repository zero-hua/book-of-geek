package ink.zerohua.bookofgeek.entity;

import lombok.Data;

import java.util.Date;

/**
 * @program: book-of-geek
 * @describletion: 评论实体
 * @author: zerohua
 * @create: 2020-06-21 15:30
 **/
@Data
public class Comment {
    //文章编号
    private Integer aId;
    //评论者编号
    private Integer uId;
    //评论内容
    private String content;
    //评论时间
    private Date commentDate;
    //评论者用户名
    private String commenterName;
}
