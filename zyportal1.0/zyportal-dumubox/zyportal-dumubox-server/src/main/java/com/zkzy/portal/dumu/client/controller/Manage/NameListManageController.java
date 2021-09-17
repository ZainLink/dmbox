package com.zkzy.portal.dumu.client.controller.Manage;


import com.github.pagehelper.PageInfo;
import com.zkzy.portal.dumu.client.common.controller.BaseController;
import com.zkzy.portal.dumu.client.controller.register.RegisterController;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.service.Manage.NameListManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Thinkpad-W530 on 2021/4/1.
 */
@Validated
@RestController
@RequestMapping("/boxManage")
@Api(tags = "人脸库(盒子)管理Controller")
public class NameListManageController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NameListManageController.class);

    @Autowired
    private NameListManageService nameListManageService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @PostMapping(value = "/loadNamelistInfo", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "人脸库同步")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> getNamelistInfo(
            @RequestParam(name = "DeviceUUID", required = true) String DeviceUUID
    ) {
        try {
            return makeMessage(nameListManageService.getNamelistInfo(DeviceUUID));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }

//    @PostMapping(value = "/addNameInfo", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "人脸库新增")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
//    public Map<String, Object> addNameInfo(
//            @RequestParam(name = "DeviceUUID", required = true) String DeviceUUID,
//            @RequestParam(name = "unid", required = true) String unid,
//            @RequestParam(name = "Name", required = true) String Name,
//            @RequestParam(name = "type", required = true) Integer type
//    ) {
//        try {
//            return makeMessageToJson(nameListManageService.addNameList(DeviceUUID, Name, unid, type));
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            return makeMessage(ReturnCode.UNKNOWN_ERROR);
//        }
//
//    }
//
//
//    @PostMapping(value = "/delNameInfo", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "人脸库删除")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
//    public Map<String, Object> delNameInfo(
//            @RequestParam(name = "DeviceUUID", required = true) String DeviceUUID,
//            @RequestParam(name = "PersonType", required = true) Integer PersonType
//    ) {
//        try {
//            if (PersonType == 1 || PersonType == 2 || PersonType == 3) {
//                return makeMessage(ReturnCode.RL_FAILED);
//            } else {
//                return makeMessageToJson(nameListManageService.deleteNameList(DeviceUUID, PersonType));
//            }
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            return makeMessage(ReturnCode.UNKNOWN_ERROR);
//        }
//    }
//
//
//    @PostMapping(value = "/modifyName", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "人脸库修改")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
//    public Map<String, Object> modifyName(
//            @RequestParam(name = "DeviceUUID", required = true) String DeviceUUID,
//            @RequestParam(name = "PersonType", required = true) Integer PersonType,
//            @RequestParam(name = "Name", required = true) String Name
//    ) {
//        try {
//            if (PersonType == 1 || PersonType == 2 || PersonType == 3) {
//                return makeMessage(ReturnCode.RL_FAILED2);
//            } else {
//                return makeMessageToJson(nameListManageService.modifyNameList(DeviceUUID, PersonType, Name));
//            }
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            return makeMessage(ReturnCode.UNKNOWN_ERROR);
//        }
//    }


//    @PostMapping(value = "/cleanNameList", produces = "application/json; charset=UTF-8")
//    @ApiOperation(value = "清空人脸库")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
//    public Map<String, Object> cleanNameList(
//            @RequestParam(name = "DeviceUUID", required = true) String DeviceUUID,
//            @RequestParam(name = "PersonType", required = true) Integer PersonType
//    ) {
//        try {
//            return makeMessageToJson(nameListManageService.cleanNameList(DeviceUUID, PersonType));
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            return makeMessage(ReturnCode.UNKNOWN_ERROR);
//        }
//    }


    @GetMapping(value = "/getNamelistInfo ", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "人脸库列表查询")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> faceInfoList(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";


            if (StringUtils.isNotEmpty(deviceuuid)) {
                param += " AND DEVICEUUID = '" + deviceuuid + "' ";
            } else {
                param += " AND DEVICEUUID = '' ";
            }

            if (StringUtils.isNotEmpty(name)) {
                param += " AND NAME LIKE '%" + name + "%' ";
            }

            PageInfo pageInfo = nameListManageService.getNamelistInfos(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }
}
