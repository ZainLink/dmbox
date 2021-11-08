package com.zkzy.portal.dumu.server.system.provider.serviceimpl.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.portal.dumu.server.system.provider.mapper.base.WxBroadcastBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.base.WxLedBMapper;
import com.zkzy.zyportal.config.gas.Queues;
import com.zkzy.zyportal.system.api.entity.base.GbReturn;
import com.zkzy.zyportal.system.api.entity.base.LedReturn;
import com.zkzy.zyportal.system.api.entity.base.WxBroadcastB;
import com.zkzy.zyportal.system.api.entity.base.WxLedB;
import com.zkzy.zyportal.system.api.entity.dm.RegisterRequest;
import com.zkzy.zyportal.system.api.entity.mq.DmStrangerH;
import com.zkzy.zyportal.system.api.util.DmHttpUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/4/22.
 */
@Component
@RabbitListener(queues = Queues.pushwarn)
public class PushReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushReceiver.class);

    @Autowired
    private WxBroadcastBMapper wxBroadcastBMapper;

    @Autowired
    private WxLedBMapper wxLedBMapper;

    private static final String dmled = "dmled-";

    private static final String dmgb = "dmgb-";


    public static final String REDIS_LED_OPEN = "LEDOPEN";

    public static final String REDIS_GB_OPEN = "GBOPEN";


    @Value("${warnpush.led}")
    private String ledurl;

    @Value("${warnpush.gb}")
    private String gburl;


    @Value("${warnpush.ledopen}")
    private String ledopen;

    @Value("${warnpush.gbopen}")
    private String gbopen;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @RabbitHandler
    public void process(Map<String, String> data) {
        String ledtype = "";
        String gbtype = "";
        try {
            if (redisTemplate.hasKey(REDIS_LED_OPEN)) {
                ledtype = redisTemplate.opsForValue().get(REDIS_LED_OPEN);
            } else {
                ledtype = ledopen;
            }

            if (redisTemplate.hasKey(REDIS_GB_OPEN)) {
                gbtype = redisTemplate.opsForValue().get(REDIS_GB_OPEN);
            } else {
                gbtype = gbopen;
            }
            String unid = data.get("unid"); //场站id
            String subname = data.get("subname");
            List<WxLedB> list = new ArrayList<>();
            List<WxBroadcastB> gblist = new ArrayList<>();
            if (StringUtils.isNoneEmpty(unid)) {
                String sql = " AND CID='" + unid + "' ";
                if ("1".equals(ledtype)) {
                    list = wxLedBMapper.selectAll(sql);
                }

                if ("1".equals(gbtype)) {
                    gblist = wxBroadcastBMapper.selectAll(sql);
                }


            }
            String leds = "";
            if (list.size() > 0 && !redisTemplate.hasKey(dmled + unid)) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == 0) {
                        leds += list.get(i).getId();
                    } else {
                        leds += "," + list.get(i).getId();
                    }
                }
                Map<String, Object> map = new HashedMap();
                map.put("ledIds", leds);
                String content = "警告/n" + subname + "/n检测到有陌生人进入";
                map.put("content", content);
                String result = DmHttpUtil.httpPostFormRequest(ledurl, map);
                String str = "";
                if (StringUtils.isNoneEmpty(result)) {
                    str = JSONObject.parseObject(result).getString("code");
                }
                if ("0".equals(str)) {
                    LedReturn led = JSON.parseObject(result, LedReturn.class);
                    led.getData().forEach(datas -> {
                        if (datas.isSuccess()) {
                            LOGGER.info(subname + "led已推送");
                            redisTemplate.opsForValue().set(dmled + unid, subname, 60, TimeUnit.SECONDS);
                        }
                    });

                } else {
                    LOGGER.info(subname + "led请求失败");
                }


            } else {
                LOGGER.info(subname + "的led还不能推送消息");
            }
            String gb = "";
            if (gblist.size() > 0 && !redisTemplate.hasKey(dmgb + unid)) {
                for (int i = 0; i < gblist.size(); i++) {
                    if (i == 0) {
                        gb += gblist.get(i).getId();
                    } else {
                        gb += "," + gblist.get(i).getId();
                    }
                }
                Map<String, Object> gbmap = new HashedMap();
                gbmap.put("broadcastIds", gb);
                String content = "警告/n" + subname + "/n检测到有陌生人进入";
                gbmap.put("content", content);
                String result = DmHttpUtil.httpPostFormRequest(gburl, gbmap);
                String str = "";
                if (StringUtils.isNoneEmpty(result)) {
                    str = JSONObject.parseObject(result).getString("code");
                }
                if ("0".equals(str)) {
                    GbReturn gbs = JSON.parseObject(result, GbReturn.class);
                    gbs.getData().forEach(datas -> {
                        if (datas.isSuccess()) {
                            LOGGER.info(subname + "广播已推送");
                            redisTemplate.opsForValue().set(dmgb + unid, subname, 60, TimeUnit.SECONDS);
                        }
                    });
                } else {
                    LOGGER.info(subname + "广播推送失败");
                    redisTemplate.opsForValue().set(dmgb + unid, subname, 60, TimeUnit.SECONDS);
                }

            } else {
                LOGGER.info(subname + "的广播的还不能推送消息");
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("预警消息异常" + e.getMessage());
        }

    }


//    public static void main(String[] args) {
//        String json="{\"code\":\"0\",\"data\":[{\"msg\":\"下发控制url请求异常\",\"success\":false,\"ledId\":\"e6e2556306354efc95f2a765f672af6e11\",\"name\":\"美湖LED测试\"}],\"desc\":\"成功\"}";
//
//        LedReturn led = JSON.parseObject(json.trim(), LedReturn.class);
//        System.out.println();
//    }
}
