package ink.zerohua.bookofgeek.controller;

import ink.zerohua.bookofgeek.entity.User;
import ink.zerohua.bookofgeek.service.IArticleService;
import ink.zerohua.bookofgeek.service.IUserService;
import ink.zerohua.bookofgeek.utils.SendSmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

/**
 * @program: book-of-geek
 * @description: 用户注册控制类
 * @author: zerohua
 * @create: 2020-05-20 13:31
 **/
@RequestMapping("/sign_up")
@Controller
@Slf4j
public class SignUpController {

    @Autowired
    IUserService userService;

    @RequestMapping
    public String toSignUp(){
        return "sign_up";
    }

    @ResponseBody
    @RequestMapping("/send_capt")
    public boolean sendCapt(@RequestParam("phoneNumbers") String phoneNumbers,
                            Model model, HttpServletRequest request){
        String code = null;
        try {
            code = SendSmsService.sendSms(phoneNumbers);
            log.info("发送验证码到："+phoneNumbers);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        HttpSession session = request.getSession();
        session.removeAttribute("MACode");
        session.setAttribute("MACode",code);

        model.addAttribute("msg","已发送");
        return true;
    }

    @ResponseBody
    @RequestMapping("/check_capt")
    public boolean checkInputCapt(@RequestParam("inputCode")String inputCode,HttpSession session){
        String code = (String) session.getAttribute("MACode");
        if (code.equals(inputCode)){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping("/save_user")
    public String saveUser(User user, Model model){
        log.info(user.toString());
        int status = userService.signUp(user);
        if( status == 3){
            model.addAttribute("msg","注册成功！");
            return "redirect:/";
        }else if( status == 1) {
            log.info("用户名已被使用！");
            model.addAttribute("msg","用户名已被使用！注册失败！");
            return "sign_up";
        }else {
            log.info("号码已被使用！");
            model.addAttribute("msg","号码已被使用！注册失败！");
            return "sign_up";
        }
    }
}
