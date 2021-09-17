package com.zkzy.portal.dumu.client.controller.dmr;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.dumu.client.common.controller.BaseController;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.DmBoxB;
import com.zkzy.zyportal.system.api.service.dm.DmStationBService;
import com.zkzy.zyportal.system.api.service.dmr.DmStfRService;
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
 * Created by Thinkpad-W530 on 2021/4/21.
 */
@Validated
@RestController
@RequestMapping("/dmr/box")
@Api(tags = "盒子绑定Controller")
public class DmStfRController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmStfRController.class);


    @Autowired
    private DmStfRService dmStfRService;

    @Autowired
    private DmStationBService dmStationBService;


    @PostMapping(value = "/bindBoxes", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "站点绑定盒子多选（仅管理员）")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> bindBoxes(
            @RequestParam(name = "deviceuuids", required = true) String deviceuuids,
            @RequestParam(name = "unid", required = true) String unid
    ) {
        try {
            return makeMessage(dmStfRService.stBindBoxes(unid, deviceuuids));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/unbind", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "站点解绑盒子单选（仅管理员）")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> unbind(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "unid", required = true) String unid
    ) {
        try {
            return makeMessage(dmStfRService.unbind(unid, deviceuuid));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @GetMapping(value = "/selectBindBoxListForStWithAdmin", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "根据站点id查询绑定的盒子列表")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> selectBindBoxListForStWithAdmin(
            @RequestParam(name = "unid", required = true) String unid,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";


            if (StringUtils.isNotEmpty(unid)) {
                param += " AND A .UNID ='" + unid + "' ";
            }
            if (StringUtils.isNotEmpty(name)) {
                param += " AND  C. NAME LIKE '%" + name + "%' ";
            }
            PageInfo pageInfo = dmStationBService.selectBindBoxListForSt(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


    @GetMapping(value = "/selectBindBoxFace", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "查询盒子下的白名单")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> selectBindBoxFace(
            @RequestParam(name = "deviceuuid", required = true) String deviceuuid,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";


            if (StringUtils.isNotEmpty(deviceuuid)) {
                param += " AND  B.DEVICEUUID ='" + deviceuuid + "' ";
            }

            PageInfo pageInfo = dmStationBService.selectBindNameListSt(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }

}
