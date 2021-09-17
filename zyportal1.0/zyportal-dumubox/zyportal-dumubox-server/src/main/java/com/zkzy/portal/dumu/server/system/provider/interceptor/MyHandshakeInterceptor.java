package com.zkzy.portal.dumu.server.system.provider.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkzy.portal.common.utils.StringHelper;
import com.zkzy.portal.common.web.security.AbstractTokenUtil;
import com.zkzy.zyportal.system.api.entity.base.Groups;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * Created by Thinkpad-W530 on 2021/4/23.
 */

@Component
@Order(10)
public class MyHandshakeInterceptor implements HandshakeInterceptor {


    /**
     * 携带Token的HTTP头
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * Token工具类
     */
    @Autowired
    private AbstractTokenUtil jwtTokenUtil;

    public static final String GROUPS = "groups-";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse
            response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        //将用户id放入socket处理器的会话(WebSocketSession)中
        ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
        //获取token
        String token = serverHttpRequest.getServletRequest().getParameter("token");
//        if (StringUtils.isNoneEmpty(token) && !jwtTokenUtil.validateToken(token)) {
//            return false;
//        }
//
//        ;
//        //获取帐号是否正确
//        String username = StringHelper.isNotBlank(token) ? jwtTokenUtil.getUsernameFromToken(token) : null;
//        if (!StringUtils.isNoneEmpty(username)) {
//            return false;
//        }
//        String jsonStr = redisTemplate.opsForValue().get(GROUPS + username);
//
//        if (StringUtils.isEmpty(jsonStr)) {
//            return false;
//        }

        Groups groups = null;
        if (StringUtils.isNoneEmpty(token)) {
            groups = new Groups();
            groups.setGroups(token);
        } else {
            groups = new Groups();
            groups.setGroups("admin");
        }
        if (groups == null) {
            return false;
        }

        //有加入ws的权限 session 内加入分组信息
        attributes.put("group", groups.getGroups());


        String session = serverHttpRequest.getServletRequest().getSession().getId();
        //用户发送信息
        attributes.put("username", session);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    }
}
