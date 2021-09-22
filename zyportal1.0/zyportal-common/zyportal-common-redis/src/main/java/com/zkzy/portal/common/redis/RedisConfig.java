package com.zkzy.portal.common.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis配置
 *
 * @author admin
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.pool.max-wait}")
    private int maxWait;

    @Value("${spring.redis.database}")
    private int database;

    /**
     * Redis repository redis repository.
     *
     * @param redisTemplate the redis template
     * @return the redis repository
     */
    @Bean
    public RedisRepository redisRepository(RedisTemplate<String, String> redisTemplate) {
        return new RedisRepository(redisTemplate);
    }

    @Bean("redisZero")
    public RedisTemplate<String, Object> getRedisTemplate() {
        RedisConnectionFactory factory = this.getRedisConnectionFactory(
                hostName, password, port, maxActive, maxIdle, minIdle, maxWait,
                5); // 建立Redis的连接
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // key的序列化类型
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer()); // value的序列化类型
        return redisTemplate;
    }

    public RedisConnectionFactory getRedisConnectionFactory(String hostName,
                                                            String password, int port, int maxActive, int maxIdle, int minIdle,
                                                            long maxWait, int database) { // 是负责建立Factory的连接工厂类
        JedisConnectionFactory jedisFactory = new JedisConnectionFactory();
        jedisFactory.setHostName(hostName);
        jedisFactory.setPort(port);
        jedisFactory.setPassword(password);
        jedisFactory.setDatabase(database);
        JedisPoolConfig poolConfig = new JedisPoolConfig(); // 进行连接池配置
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        jedisFactory.setPoolConfig(poolConfig);
        jedisFactory.afterPropertiesSet(); // 初始化连接池配置
        return jedisFactory;
    }

}