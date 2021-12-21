package com.zkzy.portal.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

@Repository
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void add(String key, String str) {
        redisTemplate.opsForValue().set(key, str);
    }

    public void delete(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    public Boolean hasKey(String key) {
        String jsonStr = redisTemplate.opsForValue().get(key);
        return redisTemplate.hasKey(key);
    }

    public Boolean expire(String key) {
        return redisTemplate.expire(key, 60, TimeUnit.SECONDS);
    }

    public void  addWithTime(String key,String value,int seconds){

        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        String jsonStr = redisTemplate.opsForValue().get(key);
    }

}
