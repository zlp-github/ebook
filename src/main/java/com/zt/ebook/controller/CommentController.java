package com.zt.ebook.controller;

import com.alibaba.druid.util.StringUtils;
import com.zt.ebook.common.JsonUtils;
import com.zt.ebook.common.SzpJsonResult;
import com.zt.ebook.pojo.Comment;
import com.zt.ebook.pojo.User;
import com.zt.ebook.request.commentRequest.AddCommentRequest;
import com.zt.ebook.request.commentRequest.UpdateCommentRequest;
import com.zt.ebook.response.commentResponse.FindCommentResponse;
import com.zt.ebook.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    CommentService commentService;

    @PostMapping( value = "add/comment")
    public SzpJsonResult addComment(@RequestHeader String token,@RequestBody AddCommentRequest Request){
       if(checkToken(token)){
           return SzpJsonResult.errorMsg("登录已过期，请重新登录");
       }
        return commentService.addComment(Request);

    }

    @DeleteMapping(value = "delete/comment")
    public SzpJsonResult deleteCommentById(@RequestHeader String token,@RequestParam Integer id){
        if(checkToken(token)){
            return SzpJsonResult.errorMsg("登录已过期，请重新登录");
        }
        return commentService.deleteCommentById(id);

    }

    @PutMapping(value = "update/comment")
    public  SzpJsonResult  updateComment(@RequestHeader String token,@RequestParam Integer commentId,@RequestParam Integer bookId,
                                         @RequestBody UpdateCommentRequest request){
        if(checkToken(token)){
            return SzpJsonResult.errorMsg("登录已过期，请重新登录");
        }
        User user = getTokenToObject(token);
        System.out.println(user.getId());
        return SzpJsonResult.ok(commentService.updateComment(commentId,bookId,user.getId(),request));
    }

    @GetMapping(value = "find/comment")
    public List<FindCommentResponse> findComment(@RequestParam Integer bookId){
        return commentService.findComment(bookId);
    }

    @GetMapping(value = "find/commentByIds")
    public SzpJsonResult<Comment> findCommentByIdS(@RequestParam Integer commentId,@RequestParam Integer bookId,@RequestParam Integer userId){
        return SzpJsonResult.ok(commentService.findCommentByIds(commentId,bookId,userId));
    }

    private User getTokenToObject(String token){
        String s = stringRedisTemplate.opsForValue().get(token);
        User user = JsonUtils.jsonToPojo(s, User.class);
        return user;
    }
    private boolean checkToken(String token){
        String s = stringRedisTemplate.opsForValue().get(token);
        System.out.println(JsonUtils.jsonToPojo(s, User.class));
        return StringUtils.isEmpty(s);
    }
}
