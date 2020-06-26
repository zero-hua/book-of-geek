package ink.zerohua.bookofgeek.service;

import ink.zerohua.bookofgeek.entity.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: book-of-geek
 * @description: 操作article的接口
 * @author: zerohua
 * @create: 2020-05-18 18:23
 **/
public interface IArticleService {

    /**
    * @Description: 增
    * @Param: 实体
    * @Return: 是否执行成功
    * @Author: zerohua
    * @Date: 2020/6/21 14:55
    */
    boolean saveArticle(Article article);

    /**
     * @Description: 删除某文章
     * @Param: 文章id
     * @Return: 是否成功
     * @Author: zerohua
     * @Date: 2020/6/21 14:42
     */
    boolean deleteArticle(Integer aId);

    /**
     * @Description: 更新文章，其中aId,作者,文章建立时间无法更改
     * @Param: 实体
     * @Return: 是否成功
     * @Author: zerohua
     * @Date: 2020/6/21 14:47
     */
     boolean updateArticle(Article article);

     /**
     * @Description: 查找某个作者的所有文章
     * @Param: 作者名
     * @Return: 文章集合
     * @Author: zerohua
     * @Date: 2020/6/21 14:57
     */
    List<Article> findSomeoneAllArticle(String author);

    /**
    * @Description: 分页查看最新文章，一次查看十篇
    * @Param: 页数
    * @Return: 文章集合
    * @Author: zerohua
    * @Date: 2020/6/21 14:58
    */
    List<Article> findAllArticle(Integer curPage);

    /**
    * @Description: 查看某篇详细文章 ,当用户点击文章标题时出现文章
    * @Param: 文章id
    * @Return: 实体
    * @Author: zerohua
    * @Date: 2020/6/21 14:59
    */
    Article findArticleById(Integer aId);

}
