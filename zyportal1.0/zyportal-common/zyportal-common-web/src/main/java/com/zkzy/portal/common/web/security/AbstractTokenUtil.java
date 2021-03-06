package com.zkzy.portal.common.web.security;

import com.google.gson.Gson;
import com.zkzy.portal.common.redis.RedisRepository;
import com.zkzy.portal.common.utils.StringHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Token 工具类
 *
 * @author admin
 */
public abstract class AbstractTokenUtil {

    /**
     * Logger
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTokenUtil.class);

    /**
     * Token 类型
     */
    public static final String TOKEN_TYPE_BEARER = "Bearer";
    /**
     * 权限缓存前缀
     */
    public static final String REDIS_PREFIX_AUTH = "auth:";

    /**
     * 度目缓存前缀
     */
    public static final String REDIS_PREFIX_DUMU = "dumu:";
    /**
     * 用户信息缓存前缀
     */
    public static final String REDIS_PREFIX_USER = "user-details:";

    public static final String REDIS_PREFIX_ONLINEUSER = "onlineUser-";

    /**
     * redis repository
     */
    @Autowired
    private RedisRepository redisRepository;
    /**
     * secret
     */
    private String secret;
    /**
     * 过期时间
     */
    private Long expiration = 5l;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取用户名
     *
     * @param token Token
     * @return String
     */
    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims != null ? claims.getSubject() : null;
    }

    /**
     * 获取过期时间
     *
     * @param token Token
     * @return Date
     */
    public Date getExpiredFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims != null ? claims.getExpiration() : null;
    }

    /**
     * 获得 Claims
     *
     * @param token Token
     * @return Claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.warn("getClaimsFromToken exception", e);
            claims = null;
        }
        return claims;
    }

    /**
     * 计算过期时间
     *
     * @return Date
     */
    private Date generateExpired() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 判断 Token 是否过期
     *
     * @param token Token
     * @return Boolean
     */
    private Boolean isTokenExpired(String token) {
        Date expirationDate = getExpiredFromToken(token);
        return expirationDate.before(new Date());
    }


    /**
     * 度目设备信息缓存
     *
     * @return
     */
    public String dumuToken(String uuid, String value) {
//        String token = Jwts.builder()
//                .setSubject(uuid)
//                .setExpiration(generateExpired())
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();

        String key = REDIS_PREFIX_DUMU + uuid;

        redisTemplate.opsForValue().set(key, value, 120, TimeUnit.SECONDS);
//        redisRepository.setExpire(key, value, 300);
        return value;
    }

    /**
     * 生成 Token
     *
     * @param userDetails 用户信息
     * @return String
     */
    public String generateToken(UserDetails userDetails) {
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(generateExpired())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        String key = REDIS_PREFIX_AUTH + userDetails.getUsername();
        redisRepository.setExpire(key, token, expiration);
        putUserDetails(userDetails);

        putOnlineUserDetails(userDetails);
        return token;
    }

    /**
     * 验证 Token
     *
     * @param token Token
     * @return Boolean
     */
    public Boolean validateToken(String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_AUTH + username;
        String redisToken = redisRepository.get(key);
        return StringHelper.isNotEmpty(token) && !isTokenExpired(token) && token.equals(redisToken);
    }

    /**
     * 移除 Token
     *
     * @param token Token
     */
    public void removeToken(String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_AUTH + username;
        redisRepository.del(key);
        delUserDetails(username);

        removeOnlineUserDetails(username);
    }

    /**
     * 获得用户信息 Json 字符串
     *
     * @param token Token
     * @return String
     */
    protected String getUserDetailsString(String token) {
        final String username = getUsernameFromToken(token);
        String key = REDIS_PREFIX_USER + username;
        return redisRepository.get(key);
    }

    /**
     * 获得用户信息
     *
     * @param token Token
     * @return UserDetails
     */
    public abstract UserDetails getUserDetails(String token);

    /**
     * 存储用户信息
     *
     * @param userDetails 用户信息
     */
    private void putUserDetails(UserDetails userDetails) {
        String key = REDIS_PREFIX_USER + userDetails.getUsername();
        redisRepository.setExpire(key, new Gson().toJson(userDetails), expiration);
    }

    /**
     * 删除用户信息
     *
     * @param username 用户名
     */
    private void delUserDetails(String username) {
        String key = REDIS_PREFIX_USER + username;
        redisRepository.del(key);
    }

    /**
     * 存储在线用户信息
     *
     * @param userDetails
     */
    private void putOnlineUserDetails(UserDetails userDetails) {
//        Map<String,String> map=new HashMap<String,String>();
//        if(redisRepository.exists(REDIS_PREFIX_ONLINEUSER)){
//            map = redisRepository.getHashValue(REDIS_PREFIX_ONLINEUSER);
//        }
//        map.put(userDetails.getUsername(),new Gson().toJson(userDetails));
//        redisRepository.putHashValues(REDIS_PREFIX_ONLINEUSER,map);

        String key = REDIS_PREFIX_ONLINEUSER + userDetails.getUsername();
        redisRepository.setExpire(key, new Gson().toJson(userDetails), expiration);


    }

    /**
     * 删除在线用户信息
     *
     * @param username
     */
    private void removeOnlineUserDetails(String username) {
//        Map<String,String> map=new HashMap<String,String>();
//        if(redisRepository.exists(REDIS_PREFIX_ONLINEUSER)){
//            map = redisRepository.getHashValue(REDIS_PREFIX_ONLINEUSER);
//        }
//        if(map.containsKey(username)){
//            map.remove(username);
//            redisRepository.del(REDIS_PREFIX_ONLINEUSER);
//            redisRepository.putHashValues(REDIS_PREFIX_ONLINEUSER,map);
//        }

        String key = REDIS_PREFIX_ONLINEUSER + username;
        redisRepository.del(key);
    }

    public List<String> onlineUserList() {
        String key = REDIS_PREFIX_ONLINEUSER + "*";
        Set onlineSet = redisRepository.getSet(key);
        List<String> onlineUserKey = new ArrayList(onlineSet);
        List<UserDetails> detailList = new ArrayList<UserDetails>();
        if (onlineUserKey.size() == 0) {
            return null;
        }
        return onlineUserKey;
//        for(String uerKey:onlineUserKey){
//
//        }
//        return detailList;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
