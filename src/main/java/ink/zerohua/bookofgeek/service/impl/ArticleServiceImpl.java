package ink.zerohua.bookofgeek.service.impl;

import ink.zerohua.bookofgeek.entity.Article;
import ink.zerohua.bookofgeek.mapper.ArticleMapper;
import ink.zerohua.bookofgeek.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: book-of-geek
 * @description: 操作article的实现类
 * @author: zerohua
 * @create: 2020-05-18 18:26
 **/
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean saveArticle(Article article) {
        //内容，标题，作者，标签
        Date date = new Date();
        article.setStartDate(date);
        article.setEndDate(date);
        //通过影响的行数来判定是否操作成功
        if(articleMapper.saveArticle(article) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteArticle(Integer aId) {
        if(articleMapper.deleteArticle(aId) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateArticle(Article article) {
        if(articleMapper.updateArticle(article) > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Article> findSomeoneAllArticle(String author) {
        List<Article> articles =  articleMapper.findSomeoneAllArticle(author);
        changeCotent(articles);
        return articles;
    }

    @Override
    public List<Article> findAllArticle(Integer curPage) {
        curPage = (curPage-1)*10;
        List<Article> articles =  articleMapper.findAllArticle(curPage);
        changeCotent(articles);
        return articles;
    }

    @Override
    public Article findArticleById(Integer aId) {
        return articleMapper.findArticleById(aId);
    }



    //文章在数据库中以md形式存储，不利于文章简介，消除其中#and*
    private void changeCotent(List<Article> articles){
        for(Article article:articles){
            String newStr  = article.getContent().replaceAll("#|\\*","");
            article.setContent(newStr);
        }
    }

}
