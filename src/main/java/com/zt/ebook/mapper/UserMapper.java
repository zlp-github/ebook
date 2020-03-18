package com.zt.ebook.mapper;

import com.zt.ebook.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends CommonMapper<User> {
}