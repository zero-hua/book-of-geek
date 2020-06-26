package ink.zerohua.bookofgeek.service.impl;

import ink.zerohua.bookofgeek.entity.Article;
import ink.zerohua.bookofgeek.service.impl.ArticleServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @program: book-of-geek
 * @author: zerohua
 * @create: 2020-06-21 15:11
 **/
@Service
public class ArticleService extends ArticleServiceImpl {

    /**
    * @Description: 增加文章浏览数
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/6/21 15:20
    */
    public boolean addReadCount(Article article){
        article.setReadCount(article.getReadCount()+1);
        return this.add(article);
    }

    /**
    * @Description:  点赞
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/6/21 15:25
    */
    public boolean addlikeCount(Article article){
        article.setLikeCount(article.getLikeCount()+1);
        return this.add(article);
    }

    /**
    * @Description: 收藏
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/6/21 15:25
    */
    public boolean addCollectCount(Article article){
        article.setCollectCount(article.getCollectCount()+1);
        return this.add(article);
    }

    private boolean add(Article article){
        if(this.updateArticle(article)){
            return true;
        }
        return false;
    }
}
