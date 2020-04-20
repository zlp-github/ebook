package com.zt.ebook.controller;

import com.zt.ebook.common.SzpJsonResult;
import com.zt.ebook.pojo.Book;
import com.zt.ebook.pojo.Category;
import com.zt.ebook.response.bookResponse.BookChapterResponse;
import com.zt.ebook.service.BookService;
import com.zt.ebook.utils.GetDataByUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public SzpJsonResult<Book> findBookByName(@RequestParam(value = "Name") String name, @RequestParam(value = "pageNumber",defaultValue = "1")
            Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        return SzpJsonResult.ok(bookService.findBookByName(name,pageNumber,pageSize));
    }

    //通过作者名进行查找书籍
    @GetMapping("find/bookByAuthor")
    public SzpJsonResult<Book> findBookByAuthor(@RequestParam(value = "author") String author, @RequestParam(value = "pageNumber",defaultValue = "1")
            Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        return SzpJsonResult.ok(bookService.findBookByAuthor(author,pageNumber,pageSize));
    }

//    阅读书籍
    @GetMapping("read/book")
    public SzpJsonResult<BookChapterResponse> readBook(@RequestParam(value = "url") String url, @RequestParam(value = "pageNumber",defaultValue = "1")
    Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        String data = getDataByUrl.getGetDateByUrl(url);
//        String data = "src/main/java/com/zt/ebook/utils/bookFile/demo.txt";
        BookChapterResponse bookChapterResponse = bookService.readBook(data,pageNumber,pageSize);
        return SzpJsonResult.ok(bookChapterResponse);
    }

    //获取全部书籍
    @GetMapping("get/allBook")
    public SzpJsonResult<Book> getAllBook(@RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                          @RequestParam(value = "pageSize",defaultValue = "5")Integer pageSize){
        return SzpJsonResult.ok(bookService.getAllBooks(pageNumber,pageSize));
    }

    //添加书籍
    @PostMapping("add/book")
    public SzpJsonResult<Integer> insertBook(@RequestBody Book book){
        return SzpJsonResult.ok(bookService.insertBook(book));
    }

    //通过id查找书籍
    @GetMapping("find/bookById")
    public SzpJsonResult<Book> findBookById(@RequestParam Integer id){
        return SzpJsonResult.ok(bookService.findBookById(id));
    }

    //获取全部分类
    @GetMapping("get/allCategory")
    public  SzpJsonResult<Category> getAllCategory(){
        return SzpJsonResult.ok(bookService.getAllCategory());
    }

    //获取每个分类底下的所有书籍
    @GetMapping("get/everyCategoryByCateId")
    public SzpJsonResult<Book> getEveryCategoryByCateId(@RequestParam Integer cateId,@RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                        @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        return SzpJsonResult.ok(bookService.getEveryCategoryBooks(cateId,pageNumber,pageSize));
    }
}
