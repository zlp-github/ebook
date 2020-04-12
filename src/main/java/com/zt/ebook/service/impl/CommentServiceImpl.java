package com.zt.ebook.service.impl;


import com.zt.ebook.common.JsonUtils;
import com.zt.ebook.common.SzpJsonResult;
import com.zt.ebook.dao.BookDao;
import com.zt.ebook.dao.CommentDao;
import com.zt.ebook.dao.SecondCommentDao;
import com.zt.ebook.dao.UserDao;
import com.zt.ebook.pojo.Book;
import com.zt.ebook.pojo.Comment;
import com.zt.ebook.pojo.SecondComment;
import com.zt.ebook.pojo.User;
import com.zt.ebook.request.commentRequest.AddCommentRequest;
import com.zt.ebook.request.commentRequest.UpdateCommentRequest;
import com.zt.ebook.response.commentResponse.FindCommentResponse;
import com.zt.ebook.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    SecondCommentDao secondCommentDao;

    @Autowired
    UserDao userDao;
    @Override
    public SzpJsonResult addComment(AddCommentRequest Request) {
        Comment comment = new Comment();
        comment.setBookId(Request.getBookId());
        comment.setUserId(Request.getUserId());
        comment.setCommentContent(Request.getCommentContent());
        comment.setCreateTime(new Date());
        comment.setUpdateTime(new Date());
        Integer integer = commentDao.addComment(comment);
        if (integer==1){
            addBookComments(comment.getBookId());
            return SzpJsonResult.ok();
        }
        return SzpJsonResult.errorException("添加失败");
    }

    private void addBookComments(Integer id) {
        Book book = bookDao.findBookById(id);
        if(book.getComments()==null){
            book.setComments(0);
        }
        book.setComments(book.getComments()+1);
        bookDao.updateBook(book);
    }
    @Override
    public SzpJsonResult deleteCommentById(Integer id) {
        Comment oneComment = commentDao.findOneComment(id);
        Integer integer = commentDao.deleteCommentById(id);
        if (integer==1){
            List<SecondComment> secondComments = secondCommentDao.FindSecondComment(id);
            for (int i=0;i<secondComments.size();i++) {
                secondCommentDao.deleteSecondComment(secondComments.get(i).getId());
            }
            Book book = bookDao.findBookById(oneComment.getBookId());
            book.setComments(book.getComments()-1-secondComments.size());
            bookDao.updateBook(book);
            return SzpJsonResult.ok("删除成功");
        }
        return SzpJsonResult.errorException("删除失败");
     
    }

    @Override
    public SzpJsonResult updateComment(Integer commentId,Integer bookId,Integer userId,UpdateCommentRequest request) {
        List<Comment> comments = commentDao.findCommentByIds(commentId,bookId,userId);
        comments.get(0).setCommentContent(request.getCommentContent());
        comments.get(0).setUpdateTime(new Date());
        Integer integer = commentDao.updateComment(comments.get(0));
        if (integer==1){
            return SzpJsonResult.ok("更新成功");
        }
        return SzpJsonResult.errorException("更新失败");
    }

    @Override
    public List<FindCommentResponse> findComment(Integer bookId) {
        List<Comment> list = commentDao.findComment(bookId);
        return showCommentAll(list);
    }
    private List<FindCommentResponse> showCommentAll(List<Comment> commentList){
        List<FindCommentResponse> list=new ArrayList<>();
        for (Comment comment:commentList){
            FindCommentResponse findCommentResponse = new FindCommentResponse();
            BeanUtils.copyProperties(comment,findCommentResponse);
            User user = userDao.findByUserId(comment.getUserId());
            findCommentResponse.setNickName(user.getNickName());
            findCommentResponse.setUImg(user.getuImg());
            list.add(findCommentResponse);
        }
        return list;
    }

    @Override
    public List<Comment> findCommentByIds(Integer commentId, Integer bookId, Integer userId) {
        return commentDao.findCommentByIds(commentId,bookId,userId);
    }
}
