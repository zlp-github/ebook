package com.zt.ebook.dao;

import com.zt.ebook.mapper.UserMapper;
import com.zt.ebook.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * Author: zlp
 * Date: 2020-02-09 22:21
 * Description:用户
 */
@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    //用户注册
    public Integer userRegister(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userMapper.insert(user);
    }

    //用户登录
    public User userLogin(String userName, String password) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", userName).andEqualTo("password", password);
        return (User) userMapper.selectByExample(example);
    }

    //修改用户信息
    public Integer alterUserInfo(User user) {
        user.setUpdateTime(new Date());
        return userMapper.updateByPrimaryKeySelective(user);
    }


}
