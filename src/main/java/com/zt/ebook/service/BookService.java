package com.zt.ebook.service;

import com.zt.ebook.pojo.Book;
import com.zt.ebook.pojo.Category;
import com.zt.ebook.response.bookResponse.BookChapterResponse;
import com.zt.ebook.response.bookResponse.BookResponse;
import com.zt.ebook.response.bookResponse.ContentResponse;

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

    BookChapterResponse readBook(String str, Integer pageNumber, Integer pageSize);

    Integer insertBook(Book book);

    Book findBookById(Integer id);

    List<Category> getAllCategory();

    BookResponse getEveryCategoryBooks(Integer cateId,Integer pageNumber,Integer pageSize);
}
