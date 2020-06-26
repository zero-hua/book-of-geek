package ink.zerohua.bookofgeek.service.impl;

import ink.zerohua.bookofgeek.entity.Comment;
import ink.zerohua.bookofgeek.mapper.CommentMapper;
import ink.zerohua.bookofgeek.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: book-of-geek
 * @author: zerohua
 * @create: 2020-06-21 16:20
 **/
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public boolean addComment(Comment comment) {
        //设置当前日期
        comment.setCommentDate(new Date());
        if(commentMapper.addComment(comment) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteComment(Integer aId, Integer uId) {
        if(commentMapper.deleteComment(aId,uId) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateComment(Comment comment) {
        if(commentMapper.updateComment(comment) > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Comment> getSomeArticleAllComment(Integer aId) {
        return commentMapper.getSomeArticleAllComment(aId);
    }

    @Override
    public List<Comment> getSomeUserAllComment(Integer uId) {
        return commentMapper.getSomeUserAllComment(uId);
    }

    @Override
    public Integer getSomeArticleCommentCount(Integer aid) {
        return commentMapper.getSomeArticleCommentCount(aid);
    }
}
