package com.zkzy.portal.dumu.server.system.provider.serviceimpl.mq;

import com.alibaba.fastjson.JSON;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.portal.dumu.server.system.provider.mapper.mq.DmStrangerHMapper;
import com.zkzy.zyportal.config.gas.Queues;
import com.zkzy.zyportal.system.api.entity.mq.DmStrangerH;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.Map;

import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/4/22.
 */

@Component
@RabbitListener(queues = Queues.capture)
public class CaptureReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptureReceiver.class);

    @Autowired
    private DmStrangerHMapper dmStrangerHMapper;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private static final String base64Img = "data:image/jpg;base64,";

    @Value("${dumu.black}")
    private String blackImg;

    @Value("${dumu.white}")
    private String whiteImg;

    @Value("${dumu.vip}")
    private String vipImg;

    @Value("${dumu.other}")
    private String other;

    @Value("${dumu.stranger}")
    private String stranger;


    @RabbitHandler
    public void process(Map<String, String> data) {
        try {

            DmStrangerH dmStrangerH = JSON.parseObject(JSON.toJSONString(data), DmStrangerH.class);
            if (dmStrangerH != null) {
                dmStrangerH.setId(uuid());
                //图片未过期
                dmStrangerH.setImgstate("0");
                int a = dmStrangerHMapper.insert(dmStrangerH);
            } else {
                LOGGER.info("抓拍入库失败");
            }
        } catch (Exception e) {
            LOGGER.error("抓拍入库异常" + e.getMessage());
        }

    }
}
