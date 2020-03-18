package com.zt.ebook.service;

import com.zt.ebook.pojo.User;
/**
 * Author: zlp
 * Date: 2020-02-09 23:01
 * Description:UserService
 */
public interface UserService {

    Integer userRegister(User user);

    User userLogin(String userName, String password);

    Integer alertUserInfo(User user);
}
