//package com.zkzy.portal.dumu.client.controller.Person;
//
//import com.zkzy.portal.dumu.client.common.controller.BaseController;
//import com.zkzy.portal.dumu.client.controller.Manage.NameListManageController;
//import com.zkzy.zyportal.system.api.constant.ReturnCode;
//import com.zkzy.zyportal.system.api.service.Person.PersonSetService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * Created by Thinkpad-W530 on 2021/4/2.
// */
//@Validated
//@RestController
//@RequestMapping("/person")
//@Api(tags = "人员入库管理Controller")
//public class PersonController extends BaseController {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
//
//    @Autowired
//    private PersonSetService personSetService;
//
//    private static final ExecutorService addpool = Executors.newFixedThreadPool(3);
//
//    private static final ExecutorService delpool = Executors.newFixedThreadPool(3);
//
//
//    @PostMapping(value = "/addPerson", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "人员入库")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
//    public Map<String, Object> addPerson(
//            @RequestParam(name = "DeviceUUID", required = true) String DeviceUUID,
//            @RequestParam(name = "userIds", required = true) String userIds,
//            @RequestParam(name = "unid", required = true) String unid,
//            @RequestParam(name = "personType", required = true) Integer personType,
//            @RequestParam(name = "LimitTime", required = true) Integer LimitTime,
//            @RequestParam(name = "sttm", required = false, defaultValue = "") String sttm,
//            @RequestParam(name = "endtime", required = false, defaultValue = "") String endtime
//    ) {
//        try {
//            if (addpool != null) {
//                addpool.submit(new Runnable() {
//                    @Override
//                    public void run() {
//                        String[] idlist = userIds.split(",");
//                        if (LimitTime == 1 && StringUtils.isNoneEmpty(sttm, endtime)) {
//                            for (int i = 0; i < idlist.length; i++) {
//                                personSetService.addPerson(DeviceUUID, idlist[i], personType, LimitTime, sttm, endtime, unid);
//                            }
//
//                        } else {
//                            for (int i = 0; i < idlist.length; i++) {
//                                personSetService.addPerson(DeviceUUID, idlist[i], personType, LimitTime, "", "", unid);
//                            }
//                        }
//                    }
//                });
//            }
//            return makeMessage(ReturnCode.TB002);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            return makeMessage(ReturnCode.UNKNOWN_ERROR);
//        }
//
//    }
//
//    @PostMapping(value = "/delPerson", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "删除库中人脸信息")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
//    public Map<String, Object> delPerson(
//            @RequestParam(name = "DeviceUUID", required = true) String DeviceUUID,
//            @RequestParam(name = "userIds", required = true) String userIds,
//            @RequestParam(name = "unid", required = true) String unid,
//            @RequestParam(name = "personType", required = true) Integer personType
//    ) {
//        try {
//            if (delpool != null) {
//                delpool.submit(new Runnable() {
//                    @Override
//                    public void run() {
//                        personSetService.dePersonList(DeviceUUID, userIds, personType, unid);
//                    }
//                });
//            }
//            return makeMessage(ReturnCode.TB003);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            return makeMessage(ReturnCode.UNKNOWN_ERROR);
//        }
//
//    }
//
//}
