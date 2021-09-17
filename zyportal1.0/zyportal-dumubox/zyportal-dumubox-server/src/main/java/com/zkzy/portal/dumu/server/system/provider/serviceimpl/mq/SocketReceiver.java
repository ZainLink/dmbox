package com.zkzy.portal.dumu.server.system.provider.serviceimpl.mq;

import com.alibaba.fastjson.JSON;
import com.zkzy.portal.dumu.server.system.provider.websocket.WebSocketServer;
import com.zkzy.zyportal.config.gas.Queues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Thinkpad-W530 on 2021/4/22.
 */
@Component
@RabbitListener(queues = Queues.socketwarn)
public class SocketReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketReceiver.class);

    @RabbitHandler
    public void process(Map<String, String> data) {
        try {
            String param = JSON.toJSONString(data);
            String unid = data.get("unid");
            WebSocketServer.sendMessage(param, unid);
        } catch (Exception e) {
            LOGGER.error("websocket消息异常" + e.getMessage());
        }

    }
}
