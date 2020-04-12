package com.zt.ebook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setMap(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    public String getMap(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void setMapWithExpiredTime(String key,String value,long time){
        stringRedisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }
}
