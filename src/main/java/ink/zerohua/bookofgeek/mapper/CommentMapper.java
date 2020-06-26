package ink.zerohua.bookofgeek.mapper;

import ink.zerohua.bookofgeek.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: book-of-geek
 * @description: 操作评论的接口
 * @author: zerohua
 * @create: 2020-06-21 15:49
 */
@Component
@Mapper
public interface CommentMapper {

  /**
   * 增加评论
   * @param comment
   * @return 影响的行数
   */
  @Insert("insert into comment(aId,uId,content,commentDate) values(#{aId},#{uId},#{content},#{commentDate})")
  int addComment(Comment comment);

  /**删除评论
   * @param aId
   * @param uId
   * @return 影响的行数
   */
  @Delete("delete from comment where aId=#{aId} and uId=#{uId}")
  int deleteComment(Integer aId, Integer uId);

  /**
   * 修改评论
   * @param comment
   * @return 影响的行数
   */
  @Update("update comment set content=#{content} ")
  int updateComment(Comment comment);

  /**获得某文章的所有评论
   * @param aId
   * @return 评论集合
   */
  @Select("select c.*,u.username as commenterName from comment c,user u where c.aId=#{aId} and c.uId=u.uId")
  List<Comment> getSomeArticleAllComment(Integer aId);

  /**获得某用户的所有评论
   * @param uId
   * @return 评论集合
   */
  @Select("select * from comment where uId=#{uId}")
  List<Comment> getSomeUserAllComment(Integer uId);

  /**获得某文章所有评论数
   * @param aid
   * @return int
   */
  @Select("select count(id) from comment where aId=#{aId}")
  Integer getSomeArticleCommentCount(Integer aid);
}
