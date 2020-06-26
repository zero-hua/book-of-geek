package ink.zerohua.bookofgeek.service;

import ink.zerohua.bookofgeek.entity.User;

/**
 * @program: book-of-geek
 * @description: 操作用户的service的接口
 * @author: 作者名字
 * @create: 2020-05-18 11:19
 **/
public interface IUserService {

    void saveUser(User user);

    void deleteUser(Integer uId);

    void updateUser(User user);

    User findUserByPhone(String phoneNumbers);

    User findUserByEmail(String email);

    User findUserByName(String username);

    int signUp(User user);
}
