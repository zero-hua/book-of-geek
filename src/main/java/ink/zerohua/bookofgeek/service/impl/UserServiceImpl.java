package ink.zerohua.bookofgeek.service.impl;

import ink.zerohua.bookofgeek.entity.User;
import ink.zerohua.bookofgeek.mapper.UserMapper;
import ink.zerohua.bookofgeek.service.IUserService;
import ink.zerohua.bookofgeek.utils.EnCode;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @program: book-of-geek
 * @description: usergongnen
 * @author: zerohua
 * @create: 2020-05-18 10:50
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public void deleteUser(Integer uId) {
        userMapper.deleteUser(uId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User findUserByPhone(String phoneNumbers) {
        User user = null;
        try {
            user = userMapper.findUserByPhone(phoneNumbers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = null;
        try {
            user = userMapper.findUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByName(String username) {
        User user = null;
        try {
            user = userMapper.findUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User signIn(String username,String password){
        User user =  userMapper.findUserByName(username);
        if(user != null){
            String salt = user.getSalt();
            String encodePassword = EnCode.getEnCodeStr(password,salt);
            if(encodePassword.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public int signUp(User user){
        User u2 = null;
        u2 = userMapper.findUserByName(user.getUsername());
        User u3 = null;
        u3 = userMapper.findUserByPhone(user.getPhoneNumbers());
        if(u2 != null){
            return 1;
        } else if(u3 != null){
            return 2;
        }
        //盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String encodedPassword = EnCode.getEnCodeStr(user.getPassword(),salt);

        user.setPassword(encodedPassword);
        user.setSalt(salt);
        userMapper.saveUser(user);
        return 3;
    }
}
