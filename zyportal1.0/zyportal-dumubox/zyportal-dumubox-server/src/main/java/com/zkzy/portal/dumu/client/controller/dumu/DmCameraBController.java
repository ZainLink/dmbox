package com.zkzy.portal.dumu.client.controller.dumu;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.dumu.client.common.controller.BaseController;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.DmBoxB;
import com.zkzy.zyportal.system.api.entity.dm.DmCameraB;
import com.zkzy.zyportal.system.api.service.dm.DmCameraBService;
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

/**
 * Created by Thinkpad-W530 on 2021/4/8.
 */

@Validated
@RestController
@RequestMapping("/ipc")
@Api(tags = "摄像头管理Controller")
public class DmCameraBController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmCameraBController.class);

    @Autowired
    private DmCameraBService dmCameraBService;

    @PostMapping(value = "/updateCamera", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "更新摄像头基础信息")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> updateCamera(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "channelno", required = true) Integer channelno,
            @RequestParam(name = "subname", required = true) String subname,
            @RequestParam(name = "unid", required = true) String unid,
            @RequestParam(name = "remark", required = false, defaultValue = "") String remark
    ) {
        try {
            DmCameraB dmCameraB = new DmCameraB();
            dmCameraB.setDeviceuuid(deviceuuid);
            dmCameraB.setChannelno(channelno);
            dmCameraB.setSubname(subname);
            dmCameraB.setUnid(unid);
            dmCameraB.setRemark(remark);
            return makeMessage(dmCameraBService.updateCamera(dmCameraB));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @GetMapping(value = "/getCameraList", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "摄像头列表查询")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> getCameraList(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "subname", required = false, defaultValue = "") String subname,
            @RequestParam(name = "unid", required = false, defaultValue = "") String unid,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";


            if (StringUtils.isNotEmpty(deviceuuid)) {
                param += " AND DEVICEUUID ='" + deviceuuid + "' ";
            }

            if (StringUtils.isNotEmpty(subname)) {
                param += " AND SUBNAME LIKE '%" + subname + "%' ";
            }

            if (StringUtils.isNotEmpty(unid)) {
                param += " AND UNID ='" + unid + "' ";
            }

            PageInfo pageInfo = dmCameraBService.getCameraList(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


}
