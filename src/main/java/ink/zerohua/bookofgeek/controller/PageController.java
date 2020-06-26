package ink.zerohua.bookofgeek.controller;

import ink.zerohua.bookofgeek.entity.Article;
import ink.zerohua.bookofgeek.entity.News;
import ink.zerohua.bookofgeek.entity.Sentence;
import ink.zerohua.bookofgeek.service.IArticleService;
import ink.zerohua.bookofgeek.service.IUserService;
import ink.zerohua.bookofgeek.service.ScienceNewsService;
import ink.zerohua.bookofgeek.service.SentenceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @program: book-of-geek
 * @author: zerohua
 * @create: 2020-05-20 18:43
 **/
@Controller
public class PageController {

    @Autowired
    IArticleService articleService;

    @Autowired
    IUserService userService;

    @Autowired
    SentenceService sentenceService;

    @Autowired
    ScienceNewsService scienceNewsService;

    @RequestMapping("/")
    public String getIndexArticle(Map<String,Object> map){
        initMap(map,1);
        return "index";
    }

    @GetMapping("/{curPage}")
    public String getIndexArticle(@PathVariable Integer curPage,Map<String,Object> map){
        initMap(map,curPage);
        return "index";
    }

    @RequestMapping("/edit")
    public String toEdit(HttpServletRequest request,Map<String,Object> map){
        return "user/edit_at";
    }

    //note：thymeleaf可以获得URI的值
    @RequestMapping("/personal_center/{name}")
    public String toPersonalCenter(@PathVariable("name") String username, HttpServletRequest request,Map<String,Object> map){
        List<Article> articles = articleService.findSomeoneAllArticle(username);
        map.put("ats",articles);
        return "user/recent";
    }

    @RequestMapping("/out")
    public String out( HttpServletResponse response,HttpServletRequest request,
                       Map<String, Object> map){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        request.getSession().removeAttribute("username");
        initMap(map,1);
        request.getSession().invalidate();
        return "redirect:/";
    }

    private void initMap(Map<String,Object> map,Integer page){
        List<Article> articles = articleService.findAllArticle(page);
        map.put("ats",articles);
        Sentence sentence = sentenceService.getTodaySentence();
        map.put("sentence",sentence);
        List<News> newsList = scienceNewsService.getTodayNewses();
        map.put("newsList",newsList);
    }

}
