package com.zt.ebook.service.impl;

import com.zt.ebook.dao.UserDao;
import com.zt.ebook.service.RedisService;
import com.zt.ebook.service.UserService;
import com.zt.ebook.pojo.User;
import com.zt.ebook.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Author: zlp
 * Date: 2020-02-09 23:07
 * Description:UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;

    @Override
    public Integer userRegister(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsAdmin(0);
        return userDao.userRegister(user);
    }

    @Override
    public List<User> userLogin(String userName, String password) {
               List<User> users = userDao.userLogin(userName,password);
               if(!users.isEmpty()){
                   String token = UUID.randomUUID().toString();
                   redisService.setMap(token, JsonUtils.objectToJson(users.get(0)));
                   String value = redisService.getMap(token);
                   redisService.setMapWithExpiredTime(token,value,100000);
                   System.out.println(value);
                   users.get(0).setToken(token);
                   return users;
               }else{
                    return null;
               }
    }

    @Override
    public User alertUserInfo(User user) {
        user.setUpdateTime(new Date());
        Integer result = userDao.alterUserInfo(user);
        if(result==1){
            return userDao.findByUserId(user.getId());
        } else {
            return null;
        }
    }
}
