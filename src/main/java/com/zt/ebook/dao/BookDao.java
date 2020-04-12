package com.zt.ebook.dao;

import com.zt.ebook.mapper.BookMapper;
import com.zt.ebook.mapper.CategoryMapper;
import com.zt.ebook.pojo.Book;
import com.zt.ebook.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * Author: zlp
 * Date: 2020-02-09 22:34
 * Description:书籍
 */

@Repository
public class BookDao {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    //通过书名查找书籍
    public List<Book> findBookByName(String name) {
        Example example = new Example(Book.class);
        example.createCriteria().andLike("bName", "%" + name + "%");
        return bookMapper.selectByExample(example);
    }

    //通过作者查找书籍
    public List<Book> findBookByAuthor(String author){
        Example example = new Example(Book.class);
        example.createCriteria().andLike("author","%"+ author +"%");
        return bookMapper.selectByExample(example);
    }

    //删除书籍
    public Integer deleteBookById(Integer id) {
        return bookMapper.deleteByPrimaryKey(id);
    }

    //获取全部书籍
    public List<Book> getAllBooks(){
        return bookMapper.selectAll();
    }

    //阅读书籍
    public Integer readBook(Integer flag)
    {
        return 1;
    }

   //更新书籍
    public Integer updateBook(Book book){
        return bookMapper.updateByPrimaryKey(book);
    }

    //通过id查找书籍
    public Book findBookById(Integer id){
        return bookMapper.selectByPrimaryKey(id);
    }

    //增加书籍
    public Integer insertBook(Book book){
        return bookMapper.insert(book);
    }

    //获取分类信息
    public Category findCategoryById(Integer id){
        return categoryMapper.selectByPrimaryKey(id);
    }
}
