package ink.zerohua.bookofgeek.controller;

import ink.zerohua.bookofgeek.entity.Article;
import ink.zerohua.bookofgeek.entity.User;
import ink.zerohua.bookofgeek.service.IArticleService;
import ink.zerohua.bookofgeek.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: book-of-geek
 * @description: 用户操作的controller
 * @author: zerohua
 * @create: 2020-05-18 11:30
 **/
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    IArticleService articleService;

//    @GetMapping("/{username}")
//    public User findUserByName(@PathVariable String username){
//        return userService.findUserByName(username);
//    }

    @RequestMapping("/new_at")
    public String newArticle(HttpServletRequest request, Map<String,Object> map){

        String username = (String) request.getSession().getAttribute("username");
        map.put("username",username);

        return "user/new_at";
    }

    @RequestMapping("/recent")
    public String toRecent(HttpServletRequest request, Map<String,Object> map){

        String username = (String) request.getSession().getAttribute("username");
        map.put("username",username);

        List<Article> articles = articleService.findSomeoneAllArticle(username);
        map.put("ats",articles);

        return "user/recent";
    }

    @RequestMapping("/manage")
    public String toManage(HttpServletRequest request,Map<String,Object> map){

        String username = (String) request.getSession().getAttribute("username");
       map.put("username",username);

        return "user/manage";
    }

    @RequestMapping("/info")
    public String toInfo(HttpServletRequest request,Map<String,Object> map){

        String username = (String) request.getSession().getAttribute("username");
        map.put("username",username);

        User user = userService.findUserByName(username);
        map.put("user",user);

        return "user/info";
    }

}
