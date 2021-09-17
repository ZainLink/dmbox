package com.zkzy.portal.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

@Repository
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void  add(String key,String str){
        redisTemplate.opsForValue().set(key,str);
    }

    public void delete(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }

}
