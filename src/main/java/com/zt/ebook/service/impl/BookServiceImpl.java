package com.zt.ebook.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zt.ebook.dao.BookDao;
import com.zt.ebook.pojo.Book;
import com.zt.ebook.pojo.Category;
import com.zt.ebook.response.bookResponse.BookChapterResponse;
import com.zt.ebook.response.bookResponse.BookResponse;
import com.zt.ebook.response.bookResponse.ContentResponse;
import com.zt.ebook.service.BookService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: zlp
 * Date: 2020-02-26 13:46
 * Description:BookServiceImpl
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    String lineTxt;
    @Override
    public BookResponse findBookByName(String name, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Book> bookList = bookDao.findBookByName(name);
        PageInfo<Book> bookPageInfo = new PageInfo<>(bookList);
        BookResponse bookResponse = new BookResponse();
        long total = bookPageInfo.getTotal();
        bookResponse.setBookList(bookList);
        bookResponse.setTotal(total);
        return bookResponse;
    }

    @Override
    public BookResponse findBookByAuthor(String author, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Book> bookList = bookDao.findBookByAuthor(author);
        PageInfo<Book> bookPageInfo = new PageInfo<>(bookList);
        BookResponse bookResponse = new BookResponse();
        long total = bookPageInfo.getTotal();
        bookResponse.setBookList(bookList);
        bookResponse.setTotal(total);
        return bookResponse;
    }

    @Override
    public Integer deleteBookById(Integer id) {
        return bookDao.deleteBookById(id);
    }

    @Override
    public BookResponse getAllBooks(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Book> bookList = bookDao.getAllBooks();
        for (Book book : bookList) {
            Integer cateId = book.getCateId();
            Category category = bookDao.findCategoryById(cateId);
            book.setCateName(category.getCateName());
            bookDao.updateBook(book);
        }
        PageInfo<Book> bookPageInfo = new PageInfo<>(bookList);
        BookResponse bookResponse = new BookResponse();
        long total = bookPageInfo.getTotal();
        bookResponse.setBookList(bookPageInfo.getList());
        bookResponse.setTotal(total);
        return bookResponse;
    }

    @SneakyThrows
    @Override
    public BookChapterResponse readBook(String fileNameDirs, Integer pageNumber, Integer pageSize) {
        List<ContentResponse> contentResponseArrayList = new ArrayList<>();
        PageHelper.startPage(pageNumber, pageSize);
        Long count = (long) 0;
        boolean bflag = false;
        int n = 0;
        String newStr = null;
        String titleName = null;
        String newChapterName = null;//新章节名称
        String substring = null;
        int indexOf = 0;
        int indexOf1 = 0;
        int line = 0;
        try {
            // 文件路径
            File file = new File(fileNameDirs);
            if (file.isFile() && file.exists()) { // 判断内容是否为空
                // 输入流
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));
                BufferedReader bufferedReader = new BufferedReader(read);
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    //小说内容类
                    ContentResponse content;
                    content = new ContentResponse();
                    //小说名称
                    Pattern comp = Pattern.compile("《([\\s\\S]*?)》");
                    Matcher matcher2 = comp.matcher(lineTxt);
                    if (matcher2.find()) {
                        content.setName(matcher2.group().trim());
                    }
                    count++;
                    // 正则表达式
                    Pattern p = Pattern.compile("(^\\s*第)(.{1,9})[章节卷集部篇回](\\s{1})(.*)($\\s*)");
                    Matcher matcher = p.matcher(lineTxt);
                    Matcher matcher1 = p.matcher(lineTxt);
                    newStr = newStr + lineTxt;
                    while (matcher.find()) {
                        titleName = matcher.group();
                        //章节去空
                        newChapterName = titleName.trim();
                        //获取章节
                        //System.out.println(newChapterName);
                        content.setChapter(newChapterName);
                        indexOf1 = indexOf;
                        //System.out.println(indexOf);
                        indexOf = newStr.indexOf(newChapterName);
                        // System.out.println(newChapterName + ":" + "第" + count + "行"); // 得到返回的章
                        if (bflag) {
                            bflag = false;
                            break;
                        }
                        if (n == 0) {
                            indexOf1 = newStr.indexOf(newChapterName);
                        }
                        n = 1;
                        bflag = true;
                        //System.out.println(chapter);
                    }
                    while (matcher1.find()) {
                        if (indexOf != indexOf1) {
                            //根据章节数量生成
                            content.setNumber(++line);
                            content.setId(line);
                            substring = newStr.substring(indexOf1, indexOf);
                            content.setContent(substring);
                            contentResponseArrayList.add(content);
                            System.out.println(content.toString());
                        }
                    }
                    if(contentResponseArrayList.size()==100){
                        break;
                    }
                }
                bufferedReader.close();
            } else {
                System.out.println("找不到指定的内容");
            }
        } catch (Exception e) {
            System.out.println("读取内容出错");
            e.printStackTrace();
        }
        BookChapterResponse bookChapterResponse = new BookChapterResponse();
        PageInfo<ContentResponse> carPageInfo = new PageInfo<>(contentResponseArrayList);
        bookChapterResponse.setBookChapterList(carPageInfo.getList());
        bookChapterResponse.setTotal(carPageInfo.getTotal());
        return bookChapterResponse;
    }


    @Override
    public Integer insertBook(Book book) {
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        book.setComments(0);
        return bookDao.insertBook(book);
    }
}
