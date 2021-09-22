package com.zkzy.portal.common.web.logFilter;

import com.zkzy.portal.base.admin.api.entity.SystemLog;
import com.zkzy.portal.base.admin.api.service.SystemMenuService;
import com.zkzy.portal.common.web.util.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Administrator on 2017/7/6.
 */
public class LogDao implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogDao.class);

    private SystemLog systemLog;

    public LogDao(SystemLog log) {
        systemLog = log;
    }

    @Override
    public void run() {
        //归属地
        LOGGER.info("系统操作记录：用户" + systemLog.getPermission() + "，使用了帐号：" + systemLog.getUsername() + "在" + systemLog.getTime() + "请求了接口：" + systemLog.getUri() + ",他的IP是：" + systemLog.getIp() + ",使用的客户端为" + systemLog.getUseragent());
    }

}
