package com.zt.ebook.service.impl;

import com.zt.ebook.dao.UserDao;
import com.zt.ebook.service.UserService;
import com.zt.ebook.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: zlp
 * Date: 2020-02-09 23:07
 * Description:UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer userRegister(User user) {
        return userDao.userRegister(user);
    }

    @Override
    public User userLogin(String userName, String password) {
        return userDao.userLogin(userName,password);
    }

    @Override
    public Integer alertUserInfo(User user) {
        return userDao.alterUserInfo(user);
    }
}
