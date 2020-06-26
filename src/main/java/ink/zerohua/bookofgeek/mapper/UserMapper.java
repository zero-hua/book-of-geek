package ink.zerohua.bookofgeek.mapper;

import ink.zerohua.bookofgeek.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    /**
    * @Description:  增加用户
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/5/18 11:16
    */
    @Options(useGeneratedKeys = true, keyProperty = "uId")
    @Insert("insert into user(username,phoneNumbers,email,password,salt) values(#{username},#{phoneNumbers},#{email},#{password},#{salt})")
    void saveUser(User user);

    
    /**
    * @Description:  删除用户
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/5/18 11:16
    */
    @Delete("delete from user where uId=#{uId}")
    void deleteUser(Integer uId);

    /**
    * @Description:  修改用户
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/5/18 11:17
    */
    @Update("update user set username=#{username},phoneNumbers=#{phoneNumbers},email=#{email},password=#{password},salt=#{salt} where uId=#{uId}")
    void updateUser(User user);

    /**
    * @Description:  通过username查找用户
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/5/18 11:17
    */
    @Select("select * from user where username=#{username}")
    User findUserByName(String username);

    /**
    * @Description:  通过email查找用户
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/5/18 11:17
    */
    @Select("select * from user where email=#{email}")
    User findUserByEmail(String email);

    /**
    * @Description:  通过手机号查找用户
    * @Param: null
    * @Return: null
    * @Author: zerohua
    * @Date: 2020/5/18 11:17
    */
    @Select("select * from user where phoneNumbers=#{phoneNumbers}")
    User findUserByPhone(String phoneNumbers);
}
