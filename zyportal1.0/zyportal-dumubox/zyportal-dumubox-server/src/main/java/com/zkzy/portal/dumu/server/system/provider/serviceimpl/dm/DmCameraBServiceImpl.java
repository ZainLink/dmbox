package com.zkzy.portal.dumu.server.system.provider.serviceimpl.dm;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmCameraBMapper;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.DmBoxB;
import com.zkzy.zyportal.system.api.entity.dm.DmCameraB;
import com.zkzy.zyportal.system.api.entity.dm.SSDeviceInfo;
import com.zkzy.zyportal.system.api.service.dm.DmCameraBService;
import com.zkzy.zyportal.system.api.service.set.BoxSetService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

import static com.zkzy.portal.common.utils.DateHelper.getDateTime;

/**
 * Created by Thinkpad-W530 on 2021/4/8.
 */
@Service
public class DmCameraBServiceImpl implements DmCameraBService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmCameraBServiceImpl.class);

    @Autowired
    private DmCameraBMapper dmCameraBMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static final String BASE_NAME = "base";


    public static final String ST_ID = "st";

    @Autowired
    private BoxSetService boxSetService;


    @Override
    public PageInfo getCameraList(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmCameraB> list = dmCameraBMapper.selectAll(param);
            for (DmCameraB b : list) {
                b.setUname(this.getStr(b.getUnid()));
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
    public CodeObject updateCamera(DmCameraB dmCameraB) {
        try {
            int a = dmCameraBMapper.updateBaseByPrimaryKey(dmCameraB);
            redisTemplate.opsForValue().set(ST_ID + dmCameraB.getDeviceuuid() + "_" + dmCameraB.getChannelno(), dmCameraB.getUnid());
            redisTemplate.opsForValue().set(dmCameraB.getDeviceuuid() + "_" + dmCameraB.getChannelno(), dmCameraB.getSubname());
            return a > 0 ? ReturnCode.SUCCESS : ReturnCode.FAILED;//修改成功
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public CodeObject updateProtocol(String deviceuuid, int ChannelNo, String ip, int Port, String name, String password, String protocol) {
        //查询现有通道协议状态 为空 且protocol 为1 则 添加设备
        try {
            String result = "";
            DmCameraB dmCameraB = dmCameraBMapper.selectByPrimaryKey(deviceuuid, ChannelNo);
            if ("1".equals(protocol)) {//为1 为onvif协议
                if (StringUtils.isEmpty(dmCameraB.getProtocol())) {
                    //新增
                    result = boxSetService.addSubDevice(deviceuuid, ChannelNo, ip, Port, name, password, protocol);
                } else if ("2".equals(dmCameraB.getProtocol())) {
                    //新增
                    result = boxSetService.addSubDevice(deviceuuid, ChannelNo, ip, Port, name, password, protocol);
                } else {
                    //修改
                    result = boxSetService.editSubDevice(deviceuuid, ChannelNo, ip, Port, name, password, protocol);
                }
            } else {
                if (StringUtils.isNoneEmpty(dmCameraB.getProtocol()) && "1".equals(dmCameraB.getProtocol())) {
                    //删除
                    result = boxSetService.deleteSubDevice(deviceuuid, ChannelNo);
                }
            }
            String str = "";
            if (StringUtils.isNoneEmpty(result)) {
                str = JSONObject.parseObject(result).getString("Code");
            }

            if (StringUtils.isNoneEmpty(str) && "1".equals(str)) {
                if ("1".equals(protocol)) {
                    dmCameraB.setSubdeviceip(ip);
                }
                dmCameraB.setProtocol(protocol);
                dmCameraBMapper.updateByProto(dmCameraB);
                return ReturnCode.SUCCESS;
            } else if (StringUtils.isEmpty(str) && "2".equals(dmCameraB.getProtocol())) {
                dmCameraB.setProtocol(protocol);
                dmCameraBMapper.updateByProto(dmCameraB);
                return ReturnCode.SUCCESS;
            } else {
                return ReturnCode.FAILED;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;
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
