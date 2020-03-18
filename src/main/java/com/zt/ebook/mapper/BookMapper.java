package com.zt.ebook.mapper;

import com.zt.ebook.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends CommonMapper<Book> {
}