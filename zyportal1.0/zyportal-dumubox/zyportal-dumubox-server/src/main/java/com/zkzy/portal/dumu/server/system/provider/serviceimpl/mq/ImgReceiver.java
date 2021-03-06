package com.zkzy.portal.dumu.server.system.provider.serviceimpl.mq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.zyportal.config.gas.Queues;
import com.zkzy.zyportal.system.api.entity.mq.DmStrangerH;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/4/23.
 */
@Component
@RabbitListener(queues = Queues.imgwarn)
public class ImgReceiver {

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

    private static final Logger LOGGER = LoggerFactory.getLogger(ImgReceiver.class);

    @RabbitHandler
    public void process(Map<String, String> data) {
        try {
            DmStrangerH dmStrangerH = JSON.parseObject(JSON.toJSONString(data), DmStrangerH.class);
            if (dmStrangerH != null) {
                Base64Util.base64StrToImage(base64Img + dmStrangerH.getFbase(), stranger + File.separator + dmStrangerH.getFurl());
//                Base64Util.commpressPicForScale(stranger + File.separator + dmStrangerH.getFurl(), stranger + File.separator + dmStrangerH.getFurl(), 100, 0.8);
                Base64Util.base64StrToImage(base64Img + dmStrangerH.getBbase(), stranger + File.separator + dmStrangerH.getBurl());
                Base64Util.compressPicForScale(stranger + File.separator + dmStrangerH.getBurl(), stranger + File.separator + dmStrangerH.getBurl(), 150, 0.9);
                LOGGER.info("==========================??????????????????==========================");
                LOGGER.info(dmStrangerH.getRemark());
                LOGGER.info("????????????" + stranger + File.separator + dmStrangerH.getFurl());
                LOGGER.info("????????????" + stranger + File.separator + dmStrangerH.getBurl());
                LOGGER.info("==========================??????????????????==========================");
                // ???????????????  ??????????????? false ?????????????????? ??????true ??????????????????
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                LOGGER.info("??????????????????");
                // ???????????????  ??????????????? false ?????????????????? ??????true ??????????????????
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            }
        } catch (Exception e) {
            LOGGER.error("??????????????????" + e.getMessage());
        }
    }
}
