package ink.zerohua.bookofgeek.controller;

import ink.zerohua.bookofgeek.entity.Article;
import ink.zerohua.bookofgeek.entity.Comment;
import ink.zerohua.bookofgeek.service.ICommentService;
import ink.zerohua.bookofgeek.service.impl.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: book-of-geek
 * @author: zerohua
 * @create: 2020-05-20 16:42
 **/
@RequestMapping("/article")
@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    ICommentService commentService;

    @GetMapping("/{aId}")
    public String showArticle(@PathVariable Integer aId, Map<String,Object> map){
        //放入文章
        Article article = articleService.findArticleById(aId);
        map.put("at",article);
        //放入文章评论
        List<Comment> comments = commentService.getSomeArticleAllComment(aId);
        map.put("comments",comments);
        //放入评论数
        Integer count = commentService.getSomeArticleCommentCount(aId);
        map.put("commentCount",count);
        //浏览数+1
        articleService.addReadCount(article);

        return "article";
    }

    @PostMapping("/save_at")
    public String saveArticle(Article article){
        articleService.saveArticle(article);
        return "user/recent";
    }


    @RequestMapping("/like")
    @ResponseBody
    public boolean doLike(@RequestParam("aId")Integer aId){
         System.out.println("体会"+aId);
         Article article = articleService.findArticleById(aId);
         if(articleService.addlikeCount(article)){
             return true;
         }
         return false;
    }

    @RequestMapping("/collect")
    public void doCollect(@RequestParam Integer aId){
        Article article = articleService.findArticleById(aId);
        articleService.addCollectCount(article);
    }

}
