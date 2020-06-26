package ink.zerohua.bookofgeek.controller;

import ink.zerohua.bookofgeek.entity.Comment;
import ink.zerohua.bookofgeek.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @program: book-of-geek
 * @author: zerohua
 * @create: 2020-06-21 16:41
 **/
@Controller
public class CommentController {

    @Autowired
    ICommentService commentService;

    //ajax方式实现保存评论并且回显
    @PostMapping("/save_comment")
    public String saveComment(Comment comment, Map<String,Object> map){
        System.out.println(comment);
        if(commentService.addComment(comment)){
            map.put("msg","评论成功！");
        }else {
            map.put("msg","评论失败！");
        }
        return "redirect:article/"+comment.getAId();
    }
}
