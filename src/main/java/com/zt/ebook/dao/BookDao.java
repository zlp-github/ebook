package com.zt.ebook.dao;

import com.zt.ebook.mapper.BookMapper;
import com.zt.ebook.pojo.Book;
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

    //通过书名查找书籍
    public List<Book> findBookByName(String name) {
        Example example = new Example(Book.class);
        example.createCriteria().andLike("bName", "%" + name + "%");
        return bookMapper.selectByExample(example);
    }

    //通过作者查找书籍
    public List<Book> findBookByAuthor(String author){
        Example example = new Example(Book.class);
        example.createCriteria().andLike("author","%"+author+"%");
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


}
