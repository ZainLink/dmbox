package com.zkzy.portal.dumu.server.system.provider.serviceimpl.dm;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmBoxBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStfNMapper;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.DmBoxB;
import com.zkzy.zyportal.system.api.entity.dm.SSDeviceInfo;
import com.zkzy.zyportal.system.api.entity.dmr.DmBoxWhiteList;
import com.zkzy.zyportal.system.api.service.dm.DmBoxBService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * Created by Thinkpad-W530 on 2021/4/1.
 */
@Service
public class DmBoxBServiceImpl implements DmBoxBService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmBoxBServiceImpl.class);

    /**
     * 度目缓存前缀
     */
    public static final String REDIS_PREFIX_DUMU = "dumu:";

    public static final String BASE_NAME = "base";

    /**
     * 度目实时信息前缀
     */

    public static final String REDIS_PREFIX_DUMU2 = "dm-";


    public static final String REDIS_PREFIX_IP = "ip-";


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private DmBoxBMapper dmBoxBMapper;

    @Autowired
    private DmStfNMapper dmStfNMapper;

    @Value("${dumu.face}")
    private String facePath;


    public SSDeviceInfo getDevicedSSinfo(String uuid) throws Exception {
        if (redisTemplate.hasKey(REDIS_PREFIX_DUMU2 + uuid)) {
            String jsonStr = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU2 + uuid);
            SSDeviceInfo ssDeviceInfo = JSON.parseObject(jsonStr, SSDeviceInfo.class);
            if (redisTemplate.hasKey(REDIS_PREFIX_DUMU + uuid)) {
                String value = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU + uuid);
                ssDeviceInfo.setSession(value);
            }
            if (redisTemplate.hasKey(REDIS_PREFIX_IP + uuid)) {
                ssDeviceInfo.setDeviceIP(redisTemplate.opsForValue().get(REDIS_PREFIX_IP + uuid));
            } else {
                DmBoxB dmBoxB = dmBoxBMapper.selectByPrimaryKey(uuid);
                if (dmBoxB != null && StringUtils.isNoneEmpty(dmBoxB.getDeviceip())) {
                    redisTemplate.opsForValue().set(REDIS_PREFIX_IP + dmBoxB.getDeviceuuid(), dmBoxB.getDeviceip());
                    ssDeviceInfo.setDeviceIP(redisTemplate.opsForValue().get(REDIS_PREFIX_IP + uuid));
                } else {
                    return null;
                }
            }
            return ssDeviceInfo;
        } else {
            return null;
        }
    }


    @Override
    public PageInfo getBoxList(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmBoxB> list = dmBoxBMapper.selectAll(param);
            SSDeviceInfo ssDeviceInfo = null;
            for (DmBoxB b : list) {
                ssDeviceInfo = this.getDevicedSSinfo(b.getDeviceuuid());
                if (ssDeviceInfo != null) {
                    b.setDeviceip(ssDeviceInfo.getDeviceIP());
                    if (StringUtils.isNoneEmpty(ssDeviceInfo.getSession())) {
                        b.setOnline(1);
                    } else {
                        b.setOnline(0);
                    }

                }
            }
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeObject updateBox(DmBoxB dmBoxB) {
        try {
            int a = dmBoxBMapper.updateByPrimaryKey(dmBoxB);
            redisTemplate.opsForValue().set(BASE_NAME + dmBoxB.getDeviceuuid(), dmBoxB.getName());
            redisTemplate.opsForValue().set(REDIS_PREFIX_IP + dmBoxB.getDeviceuuid(), dmBoxB.getDeviceip());
            return a > 0 ? ReturnCode.SUCCESS : ReturnCode.FAILED;//修改成功
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;
        }
    }

    @Override
    public PageInfo getBoxWhiteList(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmBoxWhiteList> list = dmStfNMapper.selectFaceByBox(param);
            String path = null;
            for (DmBoxWhiteList d : list) {
                path = facePath + "/" + d.getUuid() + ".jpg";
                d.setUname(this.getStr(d.getStLabel()));
                d.setImgurl(Base64Util.imageToBase64Str(path));
            }
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }


    public String getStr(String codes) {
        if (StringUtils.isNoneEmpty(codes)) {
            String codeStr = "";
            String[] code = codes.split(",");
            for (int i = 0; i < code.length; i++) {
                String codemyid = BASE_NAME + code[i];
                String str = redisTemplate.opsForValue().get(codemyid);
                if (i == 0 && StringUtils.isNoneEmpty(str)) {
                    codeStr += str;
                } else if (StringUtils.isNoneEmpty(str)) {
                    codeStr += "," + str;
                }

            }
            return codeStr;
        } else {
            return "";
        }
    }
}
