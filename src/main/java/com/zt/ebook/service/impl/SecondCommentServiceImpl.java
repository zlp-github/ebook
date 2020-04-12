package com.zt.ebook.service.impl;

import com.zt.ebook.common.SzpJsonResult;
import com.zt.ebook.dao.BookDao;
import com.zt.ebook.dao.CommentDao;
import com.zt.ebook.dao.SecondCommentDao;
import com.zt.ebook.pojo.Book;
import com.zt.ebook.pojo.Comment;
import com.zt.ebook.pojo.SecondComment;
import com.zt.ebook.pojo.User;
import com.zt.ebook.request.secondCommentRequest.AddSecondCommentRequest;
import com.zt.ebook.request.secondCommentRequest.UpdateSecondCommentRequest;
import com.zt.ebook.response.secondCommentResponse.FindSecondCommentResponse;
import com.zt.ebook.service.SecondCommentService;
import com.zt.ebook.utils.SessionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SecondCommentServiceImpl implements SecondCommentService {
    @Autowired
    SecondCommentDao secondCommentDao;

    @Autowired
    BookDao bookDao;

    @Autowired
    CommentDao commentDao;

    @Override
    public SzpJsonResult addSecondComment(AddSecondCommentRequest request) {
        SecondComment secondComment = new SecondComment();
        secondComment.setCommentContent(request.getCommentContent());
        secondComment.setCommentId(request.getCommentId());
        secondComment.setUserId(request.getUserId());
        secondComment.setCreateTime(new Date());
        Integer integer = secondCommentDao.addSecondComment(secondComment);
        if (integer==1){
            addCommentComments(request.getCommentId());
            return SzpJsonResult.ok("添加成功");
        }
        return SzpJsonResult.errorException("添加失败");
    }

    private void addCommentComments(Integer id) {
        Comment oneComment = commentDao.findOneComment(id);
        System.out.println(oneComment.getComments());
        if(oneComment.getComments()==null){
            oneComment.setComments(0);
        }
        oneComment.setComments(oneComment.getComments()+1);
        commentDao.updateComment(oneComment);
        Book book = bookDao.findBookById(oneComment.getBookId());
        if(book.getComments()==null){
            book.setComments(0);
        }
        book.setComments(book.getComments()+1);
        bookDao.updateBook(book);
    }


    @Override
    public SzpJsonResult deleteSecondCommentById(Integer id) {
        Integer integer = secondCommentDao.deleteSecondComment(id);
        if (integer==1){
            return SzpJsonResult.ok();
        }
        return SzpJsonResult.errorException("删除失败");

    }


    @Override
    public SzpJsonResult updateSecondComment(UpdateSecondCommentRequest request) {
        SecondComment secondComment = new SecondComment();
        secondComment.setCommentContent(request.getCommentContent());
        secondComment.setUpdateTime(new Date());
        Integer integer = secondCommentDao.updateSecondComment(secondComment);
        if (integer==1){
            return SzpJsonResult.ok();
        }
        return SzpJsonResult.errorException("更新失败");
    }

    @Override
    public List<FindSecondCommentResponse> findSecondComment(Integer commentId) {
        List<SecondComment> secondComments = secondCommentDao.FindSecondComment(commentId);
       return showSecondCommentAll(secondComments);

    }
    private List<FindSecondCommentResponse> showSecondCommentAll(List<SecondComment> secondComments){
        List<FindSecondCommentResponse> list=new ArrayList<>();
        for (SecondComment comment:secondComments){
            FindSecondCommentResponse findSecondCommentResponse = new FindSecondCommentResponse();
            BeanUtils.copyProperties(comment,findSecondCommentResponse);
            list.add(findSecondCommentResponse);
        }
        return list;
    }
}
