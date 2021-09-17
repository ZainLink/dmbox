//package com.zkzy.portal.dumu.client.controller.base;
//
//import com.alibaba.fastjson.JSON;
//import com.zkzy.portal.dumu.client.common.controller.BaseController;
//import com.zkzy.zyportal.system.api.constant.ReturnCode;
//
//import com.zkzy.zyportal.system.api.entity.base.SystemOrganization;
//import com.zkzy.zyportal.system.api.service.base.RedisService;
//import com.zkzy.zyportal.system.api.service.base.SystemOrganizationService;
//import com.zkzy.zyportal.system.api.viewModel.CacheSystemCodeCur;
//
//import com.zkzy.zyportal.system.api.viewModel.ZtreeSimpleView;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiOperation;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Administrator on 2017/12/6 0006.
// */
//
//@RestController
//@RequestMapping("/sys/cacheData")
//@Api(tags = "前台获取缓存数据Controller")
//@ApiModel("cacheData")
//public class CacheDataController extends BaseController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(CacheDataController.class);
//    @Autowired
//    private RedisService redisService;
//    @Autowired
//    private SystemOrganizationService systemOrganizationService;
//
//
//    @PostMapping(value = "getAllCacheData", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "获取所有缓存的数据")
//    public Map<String, Object> getAllCacheData() {
//        Map<String, Object> map = new HashMap<>();
//        //数据字典
//        Map<String, CacheSystemCodeCur> systemCode = this.cacheSystemCode();
//        if (systemCode != null) {
//            map.put("systemCodeData", systemCode);
//        }
//        //组织
//        List<ZtreeSimpleView> orgTreeList = this.getOrganizationTreeNode();
//        if (orgTreeList != null) {
//            map.put("organizationTreeNodes", orgTreeList);
//        }
//        return makeMessage(ReturnCode.SUCCESS, map);
//    }
//
//    @PostMapping(value = "getCacheSystemCode", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "获取缓存的数据字典")
//    public Map<String, Object> getCacheSystemCode() {
//        Map<String, CacheSystemCodeCur> returnMap = this.cacheSystemCode();
//        if (returnMap != null) {
//            String json = JSON.toJSONString(returnMap);
//            return makeMessage(ReturnCode.SUCCESS, returnMap);
//        } else {
//            return makeMessage(ReturnCode.FAILED);
//        }
//    }
//
//    @PostMapping(value = "/getOrgztree", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "获取缓存的组织ztree数据")
//    public Map<String, Object> getOrgztree() {
//        List<ZtreeSimpleView> zsList = this.getOrganizationTreeNode();
//        if (zsList != null) {
//            return makeMessage(ReturnCode.SUCCESS, zsList);
//        } else {
//            return makeMessage(ReturnCode.FAILED);
//        }
//
//    }
//
//
//    /**
//     * 数据字典缓存
//     *
//     * @return
//     */
//    public Map<String, CacheSystemCodeCur> cacheSystemCode() {
//        try {
//            Map<String, CacheSystemCodeCur> returnMap = redisService.getCacheSystemCode();
//            return returnMap;
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            return null;
//        }
//    }
//
//    /**
//     * 组织
//     *
//     * @return
//     */
//    public List<ZtreeSimpleView> getOrganizationTreeNode() {
//        try {
//            List<SystemOrganization> list = systemOrganizationService.selectAll("");
//            List<ZtreeSimpleView> zsList = new ArrayList<>();
//            for (SystemOrganization so : list) {
//                ZtreeSimpleView zs = new ZtreeSimpleView();
//                zs.setId(so.getOrganizationId());
//                zs.setName(so.getFullName());
//                zs.setpId(so.getPid());
//                zs.setValue(so.getMyid());
//                zsList.add(zs);
//            }
//            return zsList;
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            return null;
//        }
//    }
//
//
//}
