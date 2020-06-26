package ink.zerohua.bookofgeek.mapper;

import ink.zerohua.bookofgeek.entity.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.util.List;

/**
 * @program: book-of-geek
 * @description: 操作article的mapper
 * @author: zerohua
 * @create: 2020-05-18 17:28
 **/

@Mapper
@Component
public interface ArticleMapper {

    /**
     * @Description: 保存article
     * @Param: null
     * @Return: null
     * @Author: zerohua
     * @Date: 2020/5/18 17:37
     */
    @Options(useGeneratedKeys = true, keyProperty = "aId")
    @Insert("insert into article(title,author,content,readCount,likeCount,collectCount,startDate,endDate) " +
            "values(#{title},#{author},#{content},#{readCount},#{likeCount},#{collectCount},#{startDate},#{endDate})")
    int saveArticle(Article article);
    
    /** 
    * @Description: 删除某文章 
    * @Param: null 
    * @Return: null 
    * @Author: zerohua
    * @Date: 2020/6/21 14:42
    */
    @Delete("delete from article where aId=#{aId}")
    int deleteArticle(Integer aId);
    
    /** 
    * @Description: 更新文章，其中aId,作者,文章建立时间无法更改 
    * @Param: null 
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/6/21 14:47
    */
    @Update("update article set title=#{title},content=#{content},readCount=#{readCount},likeCount=#{likeCount},collectCount=#{collectCount},endDate=#{endDate}" +
            "where aId=#{aId}")
    int updateArticle(Article article);
    
    /**
    * @Description: 查询某用户所有文章
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/5/18 18:12
    */
    @Select("select " +
            "aId,title,author,left(content,100) as content,readCount,likeCount,collectCount,startDate,endDate " +
            "from article where author=#{author} order by aId desc")
    List<Article> findSomeoneAllArticle(String author);

    /**
    * @Description: 查找所有文章用于主页显示,分页十条
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/5/20 16:46
    */
    @Select("select " +
            "aId,title,author,left(content,100) as content,readCount,likeCount,collectCount,startDate,endDate " +
            "from article order by aId desc limit #{curPage},10")
    List<Article> findAllArticle(Integer curPage);
    

    /**
    * @Description:
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/5/21 17:57
    */
    @Select("select * from article where aId=#{aId}")
    Article findArticleById(Integer aId);
}
