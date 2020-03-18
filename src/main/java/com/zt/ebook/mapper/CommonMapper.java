package com.zt.ebook.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Auther: szp
 * @Date: 2020/2/2 21:32
 * @Description:
 */

public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
}