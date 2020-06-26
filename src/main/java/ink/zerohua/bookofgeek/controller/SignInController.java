package ink.zerohua.bookofgeek.controller;


import ink.zerohua.bookofgeek.entity.Article;
import ink.zerohua.bookofgeek.entity.User;
import ink.zerohua.bookofgeek.service.IArticleService;
import ink.zerohua.bookofgeek.service.impl.UserServiceImpl;
import ink.zerohua.bookofgeek.utils.Kaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @program: book-of-geek
 * @description: 用户登录控制类
 * @author: zerohua
 * @create: 2020-05-20 13:23
 **/
@Controller
@RequestMapping("/sign_in")
@Slf4j
public class SignInController {

    @Autowired
    Kaptcha kaptcha;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    IArticleService articleService;

    @RequestMapping
    public String toSignIn(){
        return "sign_in";
    }

    @RequestMapping("/captcha")
    public void getCaptcha(){
    }

    @RequestMapping("/img_code")
    public void creatImgCode(HttpServletRequest request, HttpServletResponse response){
        String capText = kaptcha.getCode();
        log.info("本次验证码：" +capText);
        request.getSession().setAttribute("randomCapt",capText);
        try {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            BufferedImage image = kaptcha.createKaptcha(capText);
            OutputStream  out = response.getOutputStream();
            ImageIO.write(image,"jpg",out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/check_capt")
    @ResponseBody
    public boolean checkCaptcha(@RequestParam("captcha")String captcha,HttpSession session){
        String cap = (String) session.getAttribute("randomCapt");
        if (captcha.equals(cap)){
            return true;
        }else {
            return false;
        }
    }

    @PostMapping("/check")
    public String check(@RequestParam("username")String username, @RequestParam("password")String password,
                        Map<String,Object> map, HttpSession session){

        log.info(username+"尝试登录...");
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            List<Article> articles = articleService.findAllArticle(1);
            map.put("ats",articles);
            User user = userService.findUserByName(username);
            session.setAttribute("user",user);
            return "redirect:/";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            map.put("msg","用户名密码错误！");
            return "sign_in";
        }
    }
}
