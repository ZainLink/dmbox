package com.zkzy.portal.dumu.server.system.provider.config;

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

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void add(String key, String str) {
        redisTemplate.opsForValue().set(key, str);
    }

    public void delete(String key) {
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    public void refreshKey(String key) {
        //刷新token
        redisTemplate.expire(key, 30, TimeUnit.MINUTES);
    }

}
