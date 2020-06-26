package ink.zerohua.bookofgeek.mapper;

import ink.zerohua.bookofgeek.entity.Sentence;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: book-of-geek
 * @description: 每天好句
 * @author: zerohua
 * @create: 2020-06-20 18:36
 **/
@Component
@Mapper
public interface SentenceMapper {

    @Select("select * from sentence where id=#{id}")
    Sentence getTodaySentence(Integer id);

    @Insert("insert into sentence(content,mean) values(#{content},#{mean})")
    void addSentence(Sentence sentence);

}
