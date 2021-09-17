package com.zkzy.portal.dumu.client.controller.dumu;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.common.web.util.WebUtils;
import com.zkzy.portal.dumu.client.common.controller.BaseController;
import com.zkzy.portal.dumu.client.security.model.AuthUser;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.DmBoxB;
import com.zkzy.zyportal.system.api.entity.dm.DmFaceB;
import com.zkzy.zyportal.system.api.service.dm.DmBoxBService;
import com.zkzy.zyportal.system.api.service.dm.DmStationBService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/4/1.
 */
@Validated
@RestController
@RequestMapping("/dm/box")
@Api(tags = "盒子管理Controller")
public class DmBoxBController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmBoxBController.class);

    @Autowired
    private DmBoxBService dmBoxBService;

    @Autowired
    private DmStationBService dmStationBService;


    @GetMapping(value = "/boxInfoList", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "盒子列表查询")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> boxInfoList(
            @RequestParam(name = "deviceuuid", required = false, defaultValue = "") String deviceuuid,
            @RequestParam(name = "deviceid", required = false, defaultValue = "") String deviceid,
            @RequestParam(name = "devicemac", required = false, defaultValue = "") String devicemac,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";


            if (StringUtils.isNotEmpty(deviceuuid)) {
                param += " AND DEVICEUUID LIKE '%" + deviceuuid + "%' ";
            }

            if (StringUtils.isNotEmpty(deviceid)) {
                param += " AND DEVICEID LIKE '%" + deviceid + "%' ";
            }

            if (StringUtils.isNotEmpty(devicemac)) {
                param += " AND DEVICEMAC LIKE '%" + devicemac + "%' ";
            }

            if (StringUtils.isNotEmpty(name)) {
                param += " AND NAME LIKE '%" + name + "%' ";
            }

            PageInfo pageInfo = dmBoxBService.getBoxList(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


    @GetMapping(value = "/selectBindBoxListForSt", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "站点查询绑定的盒子接口")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> selectBindBoxListForSt(
            @RequestParam(name = "unid", required = true) String unid,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";
            if (StringUtils.isNotEmpty(unid)) {
                param += " AND A .UNID = '" + unid + "' ";
            } else {
                param += " AND A .UNID = '" + uuid() + "' ";
            }

            if (StringUtils.isNotEmpty(name)) {
                param += " AND C.NAME LIKE '%" + name + "%' ";
            }

            PageInfo pageInfo = dmStationBService.selectBindBoxListForSt(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


    @GetMapping(value = "/selectBindStListForBox", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "站点查询绑定的盒子接口")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> selectBindStListForBox(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "uname", required = false, defaultValue = "") String uname,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";
            if (StringUtils.isNotEmpty(deviceuuid)) {
                param += " AND B.DEVICEUUID = '" + deviceuuid + "' ";
            } else {
                param += " AND B.DEVICEUUID = '" + uuid() + "' ";
            }

            if (StringUtils.isNotEmpty(uname)) {
                param += " AND A.UNAME LIKE '%" + uname + "%' ";
            }

            PageInfo pageInfo = dmStationBService.selectBindBoxListForSt(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


    @GetMapping(value = "/getBoxWhiteList", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "获取盒子白名单列表")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> getBoxWhiteList(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "unid", required = true) String unid,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";
            if (StringUtils.isNotEmpty(deviceuuid)) {
                param += " AND B.DEVICEUUID = '" + deviceuuid + "' ";
            } else {
                param += " AND B.DEVICEUUID = '" + uuid() + "' ";
            }
            if (StringUtils.isNotEmpty(unid)) {
                param += " AND B .UNID = '" + unid + "' ";
            } else {
                param += " AND B .UNID = '" + uuid() + "' ";
            }

            if (StringUtils.isNotEmpty(name)) {
                param += " AND A.NAME LIKE '%" + name + "%' ";
            }

            PageInfo pageInfo = dmBoxBService.getBoxWhiteList(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


    @PostMapping(value = "/updateBox", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "更新盒子基础信息")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> updateFace(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "deviceip", required = true) String deviceip,
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "remark", required = false, defaultValue = "") String remark
    ) {
        try {
            DmBoxB dmBoxB = new DmBoxB();
            dmBoxB.setDeviceuuid(deviceuuid);
            dmBoxB.setName(name);
            dmBoxB.setDeviceip(deviceip);
            dmBoxB.setRemark(remark);
            return makeMessage(dmBoxBService.updateBox(dmBoxB));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }
}
