package com.zt.ebook.controller;

import com.alibaba.druid.util.StringUtils;
import com.zt.ebook.common.SzpJsonResult;
import com.zt.ebook.pojo.User;
import com.zt.ebook.request.secondCommentRequest.AddSecondCommentRequest;
import com.zt.ebook.request.secondCommentRequest.UpdateSecondCommentRequest;
import com.zt.ebook.response.secondCommentResponse.FindSecondCommentResponse;
import com.zt.ebook.service.SecondCommentService;
import com.zt.ebook.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SecondCommentController {
    @Autowired
    SecondCommentService secondCommentService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostMapping(value = "add/secondComment")
    public SzpJsonResult addSecondComment(@RequestHeader String token,@RequestBody AddSecondCommentRequest request){
        if(checkToken(token)){
            return SzpJsonResult.errorMsg("登录已过期，请重新登录");
        }
        return secondCommentService.addSecondComment(request);
    }
    @DeleteMapping(value = "delete/secondComment")
    public  SzpJsonResult deleteSecondCommentById(@RequestHeader String token,@RequestParam Integer id){
        if(checkToken(token)){
            return SzpJsonResult.errorMsg("登录已过期，请重新登录");
        }
        return  secondCommentService.deleteSecondCommentById(id);
    }
    @PutMapping(value = "update/secondComment")
    public SzpJsonResult  updateSecondComment(@RequestHeader String token,@RequestBody UpdateSecondCommentRequest request){
        if(checkToken(token)){
            return SzpJsonResult.errorMsg("登录已过期，请重新登录");
        }
        return secondCommentService.updateSecondComment(request);
    }
    @GetMapping(value = "find/secondComment")
    public List<FindSecondCommentResponse> findSecondComment(@RequestParam Integer commentId){

        return secondCommentService.findSecondComment(commentId);
    }

    private boolean checkToken(String token){
        String s = stringRedisTemplate.opsForValue().get(token);
        return StringUtils.isEmpty(s);
    }
}
