package ink.zerohua.bookofgeek.service;

import ink.zerohua.bookofgeek.entity.Comment;
import java.util.List;

/**
 * @program: book-of-geek
 * @description: 评论业务逻辑接口
 * @author: zerohua
 * @create: 2020-06-21 16:18
 **/
public interface ICommentService {

    /**
     * 增加评论
     * @param comment
     * @return 是否成功
     */
    boolean addComment(Comment comment);

    /**删除评论
     * @param aId
     * @param uId
     * @return 是否成功
     */
    boolean deleteComment(Integer aId, Integer uId);

    /**
     * 修改评论
     * @param comment
     * @return 是否成功
     */
    boolean updateComment(Comment comment);

    /**获得某文章的所有评论
     * @param aId
     * @return 评论集合
     */
    List<Comment> getSomeArticleAllComment(Integer aId);

    /**获得某用户的所有评论
     * @param uId
     * @return 评论集合
     */
    List<Comment> getSomeUserAllComment(Integer uId);

    /**获得某文章所有评论数
     * @param aid
     * @return integer
     */
    Integer getSomeArticleCommentCount(Integer aid);
}
