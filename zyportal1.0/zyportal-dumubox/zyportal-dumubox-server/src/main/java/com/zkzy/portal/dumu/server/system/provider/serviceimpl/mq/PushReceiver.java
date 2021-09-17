package com.zkzy.portal.dumu.server.system.provider.serviceimpl.mq;

import com.alibaba.fastjson.JSON;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.zyportal.config.gas.Queues;
import com.zkzy.zyportal.system.api.entity.mq.DmStrangerH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/4/22.
 */
@Component
@RabbitListener(queues = Queues.pushwarn)
public class PushReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushReceiver.class);

    @RabbitHandler
    public void process(Map<String, String> data) {
        try {
            LOGGER.info("预警消息推送完毕");
        } catch (Exception e) {
            LOGGER.error("预警消息异常" + e.getMessage());
        }

    }
}
