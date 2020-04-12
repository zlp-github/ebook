package com.zt.ebook.service;

import com.zt.ebook.pojo.User;

import java.util.List;

/**
 * Author: zlp
 * Date: 2020-02-09 23:01
 * Description:UserService
 */
public interface UserService {

    Integer userRegister(User user);

    List<User> userLogin(String userName, String password);

    User alertUserInfo(User user);
}
