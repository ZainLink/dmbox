package com.zkzy.portal.dumu.server.system.provider.config;

/**
 * Created by Thinkpad on 2019/4/2.
 */

import com.zkzy.portal.dumu.server.system.provider.interceptor.MyHandshakeInterceptor;
import com.zkzy.portal.dumu.server.system.provider.websocket.WebSocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;

/**
 * 开启WebSocket支持
 *
 * @author zzy
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    /**
     * 注入拦截器
     */
    @Resource
    private MyHandshakeInterceptor myHandshakeInterceptor;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        webSocketHandlerRegistry
                //添加myHandler消息处理对象，和websocket访问地址
                .addHandler(myHandler(), "/ws")
                //设置允许跨域访问
                .setAllowedOrigins("*")
                //添加拦截器可实现用户链接前进行权限校验等操作
                .addInterceptors(myHandshakeInterceptor);
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new WebSocketServer();
    }
}
