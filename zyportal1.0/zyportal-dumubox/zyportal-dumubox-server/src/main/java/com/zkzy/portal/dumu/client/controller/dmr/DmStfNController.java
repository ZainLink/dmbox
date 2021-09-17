package com.zkzy.portal.dumu.client.controller.dmr;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.common.utils.DateHelper;
import com.zkzy.portal.dumu.client.common.controller.BaseController;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.service.dmr.DmStfNService;
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
 * Created by Thinkpad-W530 on 2021/4/21.
 */
@Validated
@RestController
@RequestMapping("/dm/dmStfN")
@Api(tags = "站点白名单管理Controller")
public class DmStfNController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmStfNController.class);

    @Autowired
    private DmStfNService dmStfNService;


    @PostMapping(value = "/importWhiteList", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "站点导入白名单")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> importWhiteList(
            @RequestParam(name = "unid", required = true) String unid,
            @RequestParam(name = "userids", required = true) String userids,
            @RequestParam(name = "LimitTime", required = true) Integer LimitTime,
            @RequestParam(name = "sttm", required = false, defaultValue = "") String sttm,
            @RequestParam(name = "endtime", required = false, defaultValue = "") String endtime
    ) {
        try {
            if (LimitTime == 1 && StringUtils.isNoneEmpty(sttm, endtime) && DateHelper.isValidDate(sttm) && DateHelper.isValidDate(endtime)) {
                return makeMessage(dmStfNService.importWhiteList(unid, userids, 1, sttm, endtime));
            } else if (LimitTime == 0) {
                return makeMessage(dmStfNService.importWhiteList(unid, userids, 0, "", ""));
            } else {
                return makeMessage(ReturnCode.FFCS);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/delWhiteList", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "站点删除白名单")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> delWhiteList(
            @RequestParam(name = "unid", required = true) String unid,
            @RequestParam(name = "userids", required = true) String userids
    ) {
        try {
            return makeMessage(dmStfNService.delWhiteList(unid, userids));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @GetMapping(value = "/getWhiteList", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "站点白名单列表获取")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> getWhiteList(
            @RequestParam(name = "unid", required = true) String unid,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "tel", required = false, defaultValue = "") String tel,
            @RequestParam(name = "LimitTime", required = false, defaultValue = "") String LimitTime,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";
            if (StringUtils.isNotEmpty(unid)) {
                param += " AND C.UNID ='" + unid + "' ";
            } else {
                param += " AND C.UNID ='" + uuid() + "' ";
            }

            if (StringUtils.isNotEmpty(name)) {
                param += " AND  A .NAME LIKE '%" + name + "%' ";
            }

            if (StringUtils.isNotEmpty(tel)) {
                param += " AND  A .TEL LIKE '%" + tel + "%' ";
            }

            if (StringUtils.isNotEmpty(LimitTime)) {
                param += " AND B.LIMITTIME =" + LimitTime + " ";
            }
            PageInfo pageInfo = dmStfNService.getWhiteList(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


}
