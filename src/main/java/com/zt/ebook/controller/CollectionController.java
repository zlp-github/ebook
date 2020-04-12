package com.zt.ebook.controller;

import com.alibaba.druid.util.StringUtils;
import com.zt.ebook.common.JsonUtils;
import com.zt.ebook.common.SzpJsonResult;
import com.zt.ebook.pojo.User;
import com.zt.ebook.request.collectionRequest.AddCollectionRequest;
import com.zt.ebook.response.collectionResponse.FindCollectionResponse;
import com.zt.ebook.service.CollectionService;
import com.zt.ebook.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping(value = "add/collection")
    public SzpJsonResult addCollection(@RequestHeader String token,@RequestBody AddCollectionRequest request){
        if(checkToken(token)){
            return SzpJsonResult.errorMsg("登录已过期，请重新登录");
        }
        return collectionService.addCollection(request);
    }

    @DeleteMapping(value = "delete/collection")
    public SzpJsonResult deleteCollection(@RequestHeader String token,@RequestParam Integer id){
        if(checkToken(token)){
            return SzpJsonResult.errorMsg("登录已过期，请重新登录");
        }
        return collectionService.deleteCollection(id);
    }

    @DeleteMapping(value = "delete/collection/List")
    public SzpJsonResult deleteCollectionList(@RequestHeader String token,@RequestParam List<Integer> ids){
        if(checkToken(token)){
            return SzpJsonResult.errorMsg("登录已过期，请重新登录");
        }
        return collectionService.deleteCollectionList(ids);
    }

    @GetMapping(value = "find/collection")
    public SzpJsonResult<FindCollectionResponse>findCollectionByUserId(@RequestParam String token){
        if(checkToken(token)){
            return SzpJsonResult.errorMsg("登录已过期，请重新登录");
        }
        User user = getTokenToObject(token);
        return SzpJsonResult.ok(collectionService.findCollectionByUserId(user.getId()));
    }

    private boolean checkToken(String token){
        String s = stringRedisTemplate.opsForValue().get(token);
        return StringUtils.isEmpty(s);
    }

    private User getTokenToObject(String token){
        String s = stringRedisTemplate.opsForValue().get(token);
        User user = JsonUtils.jsonToPojo(s, User.class);
        return user;
    }
}
