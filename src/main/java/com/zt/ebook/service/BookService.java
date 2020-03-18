package com.zt.ebook.service;

import com.zt.ebook.response.BookResponse;
import com.zt.ebook.response.ContentResponse;

import java.util.List;

/**
 * Author: zlp
 * Date: 2020-02-09 23:01
 * Description:
 */

public interface BookService {

    BookResponse findBookByName(String name, Integer pageNumber, Integer pageSize);

    BookResponse findBookByAuthor(String author,Integer pageNumber,Integer pageSize);

    Integer deleteBookById(Integer id);

    BookResponse getAllBooks(Integer pageNumber ,Integer pageSize);

    List<ContentResponse> readBook(String str);
}
