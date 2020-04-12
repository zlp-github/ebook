package com.zt.ebook.service;

import com.zt.ebook.common.SzpJsonResult;
import com.zt.ebook.pojo.Comment;
import com.zt.ebook.request.commentRequest.AddCommentRequest;
import com.zt.ebook.request.commentRequest.UpdateCommentRequest;
import com.zt.ebook.response.commentResponse.FindCommentResponse;

import java.util.List;

public interface CommentService {
    SzpJsonResult addComment(AddCommentRequest Request);
    SzpJsonResult deleteCommentById(Integer id);
    SzpJsonResult  updateComment(Integer commentId,Integer bookId,Integer userId,UpdateCommentRequest request);
    List<FindCommentResponse> findComment(Integer bookId);
    List<Comment> findCommentByIds(Integer commentId,Integer bookId,Integer userId);
}
