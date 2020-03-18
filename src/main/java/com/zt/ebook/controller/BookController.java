package com.zt.ebook.controller;

import com.zt.ebook.common.GetDataByUrl;
import com.zt.ebook.common.SzpJsonResult;
import com.zt.ebook.pojo.Book;
import com.zt.ebook.response.ContentResponse;
import com.zt.ebook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: zlp
 * Date: 2020-02-09 23:10
 * Description:书籍API
 */
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private GetDataByUrl getDataByUrl;

    //通过书名进行查找书籍
    @GetMapping("find/bookByName")
    public SzpJsonResult<Book> findBookByName(@RequestParam(value = "bName") String name, @RequestParam(value = "pageMNumber",defaultValue = "1")
            Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        return SzpJsonResult.ok(bookService.findBookByName(name,pageNumber,pageSize));
    }

    //通过作者名进行查找书籍
    @GetMapping("find/bookByAuthor")
    public SzpJsonResult<Book> findBookByAuthor(@RequestParam(value = "author") String author, @RequestParam(value = "pageMNumber",defaultValue = "1")
            Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        return SzpJsonResult.ok(bookService.findBookByAuthor(author,pageNumber,pageSize));
    }

    //阅读书籍
    @GetMapping("read/book")
    public SzpJsonResult<ContentResponse> readBook(@RequestParam(value = "url") String url){
        String data = getDataByUrl.getGetDateByUrl(url);
        List<ContentResponse> contentResponseList = bookService.readBook(data);
        System.out.println(contentResponseList.size());
        return SzpJsonResult.ok(contentResponseList);
    }

}
