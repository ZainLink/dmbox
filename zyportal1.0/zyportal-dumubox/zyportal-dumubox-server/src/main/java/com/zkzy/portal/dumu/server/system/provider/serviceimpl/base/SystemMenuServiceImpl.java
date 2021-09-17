//package com.zkzy.portal.dumu.server.system.provider.serviceimpl.base;
//
//import com.zkzy.portal.dumu.server.system.provider.mapper.base.SystemIpMapper;
//import com.zkzy.zyportal.system.api.entity.base.SystemIp;
//import com.zkzy.zyportal.system.api.service.base.SystemMenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * Created by Administrator on 2017/6/26 0026.1
// */
//@Service("systemMenuServiceImpl")
//public class SystemMenuServiceImpl implements SystemMenuService {
//
//    @Autowired
//    private SystemIpMapper systemIpMapper;
//
//
//    @Override
//    public void saveIp(SystemIp systemIp) {
//        try {
//            systemIpMapper.insert(systemIp);
//        } catch (Exception e) {
//            systemIpMapper.updateByPrimaryKey(systemIp);
//        }
//    }
//}
//
//
