package com.zkzy.portal.dumu.client.controller.dumu;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.zkzy.portal.dumu.client.common.controller.BaseController;
import com.zkzy.portal.dumu.client.security.model.AuthUser;
import com.zkzy.portal.common.web.util.WebUtils;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.DmStationB;
import com.zkzy.zyportal.system.api.service.dm.DmStationBService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/3/24.
 */
@Validated
@RestController
@RequestMapping("/dm/dmStationB")
@Api(tags = "站点管理Controller")
public class DmStationBController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmStationBController.class);

    @Autowired
    private DmStationBService dmStationBService;


    public static final String REDIS_LED_OPEN = "LEDOPEN";

    public static final String REDIS_GB_OPEN = "GBOPEN";


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${warnpush.ledopen}")
    private String ledopen;

    @Value("${warnpush.gbopen}")
    private String gbopen;


    @PostMapping(value = "/createStation", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "新建站点")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> createQrcode(
            @RequestParam(name = "uname", required = true) String uname,
            @RequestParam(name = "lname", required = true) String lname,
            @RequestParam(name = "ltel", required = true) String ltel,
            @RequestParam(name = "areacode", required = true) String areacode,
            @RequestParam(name = "areaname", required = true) String areaname,
            @RequestParam(name = "lng", required = false, defaultValue = "") String lng,
            @RequestParam(name = "lat", required = false, defaultValue = "") String lat,
            @RequestParam(name = "address", required = false, defaultValue = "") String address
    ) {
        try {
//            AuthUser user = WebUtils.getCurrentUser();
            DmStationB dmStationB = new DmStationB();
            dmStationB.setCreater("系统");
            dmStationB.setModifyer("系统");
            dmStationB.setUname(uname);
            dmStationB.setLname(lname);
            dmStationB.setLtel(ltel);
            dmStationB.setAddress(address);
            dmStationB.setAreacode(areacode);
            dmStationB.setAreaname(areaname);
            dmStationB.setLat(lat);
            dmStationB.setLng(lng);
            return makeMessage(dmStationBService.addDmStation(dmStationB));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/loadDmStation", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "载入储配站")
    public Map<String, Object> loadDmStation() {
        try {
            return makeMessage(dmStationBService.loadDmStation());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/pushText", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "是否推送led和广播")
    public Map<String, Object> pushText(
            @RequestParam(name = "led", required = true, defaultValue = "0") String led,
            @RequestParam(name = "gb", required = true, defaultValue = "0") String gb
    ) {
        try {

            redisTemplate.opsForValue().set(REDIS_LED_OPEN, led);

            redisTemplate.opsForValue().set(REDIS_GB_OPEN, gb);

            return makeMessage(ReturnCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/getPushType", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "查看推送状态")
    public Map<String, Object> getPushType(
    ) {
        try {

            String ledtype = "";
            String gbtype = "";

            if (redisTemplate.hasKey(REDIS_LED_OPEN)) {
                ledtype = redisTemplate.opsForValue().get(REDIS_LED_OPEN);
            } else {
                ledtype = ledopen;
            }

            if (redisTemplate.hasKey(REDIS_GB_OPEN)) {
                gbtype = redisTemplate.opsForValue().get(REDIS_GB_OPEN);
            } else {
                gbtype = gbopen;
            }

            Map<String, Object> map = new HashedMap();
            map.put("led", ledtype);
            map.put("gb", gbtype);
            return makeMessage(ReturnCode.SUCCESS, map);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/delStation", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "删除站点")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> createQrcode(
            @RequestParam(name = "unid", required = true) String unid
    ) {
        try {

            return makeMessage(dmStationBService.delDmStation(unid));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/updateStation", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "更新站点")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> updateStation(
            @RequestParam(name = "unid", required = true) String unid,
            @RequestParam(name = "uname", required = true) String uname,
            @RequestParam(name = "lname", required = true) String lname,
            @RequestParam(name = "ltel", required = true) String ltel,
            @RequestParam(name = "areacode", required = true) String areacode,
            @RequestParam(name = "areaname", required = true) String areaname,
            @RequestParam(name = "lng", required = false, defaultValue = "") String lng,
            @RequestParam(name = "lat", required = false, defaultValue = "") String lat,
            @RequestParam(name = "address", required = false, defaultValue = "") String address
    ) {
        try {
//            AuthUser user = WebUtils.getCurrentUser();
            DmStationB dmStationB = new DmStationB();
            dmStationB.setUnid(unid);
            dmStationB.setModifyer("系统");
            dmStationB.setUname(uname);
            dmStationB.setLname(lname);
            dmStationB.setLtel(ltel);
            dmStationB.setAddress(address);
            dmStationB.setAreacode(areacode);
            dmStationB.setAreaname(areaname);
            dmStationB.setLat(lat);
            dmStationB.setLng(lng);
            return makeMessage(dmStationBService.updateDmStation(dmStationB));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @GetMapping(value = "/stationInfoList", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "站点信息列表查询（管理员）")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> stationInfoList(
            @RequestParam(name = "uname", required = false, defaultValue = "") String uname,
            @RequestParam(name = "lname", required = false, defaultValue = "") String lname,
            @RequestParam(name = "ltel", required = false, defaultValue = "") String ltel,
            @RequestParam(name = "address", required = false, defaultValue = "") String address,
            @RequestParam(name = "areacode", required = false, defaultValue = "") String areacode,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";


            if (StringUtils.isNotEmpty(uname)) {
                param += " AND A.UNAME LIKE '%" + uname + "%' ";
            }

            if (StringUtils.isNotEmpty(lname)) {
                param += " AND A.LNAME LIKE '%" + lname + "%' ";
            }


            if (StringUtils.isNotEmpty(ltel)) {
                param += " AND A.LTEL LIKE '%" + ltel + "%' ";
            }
            if (StringUtils.isNotEmpty(address)) {
                param += " AND A.ADDRESS LIKE '%" + address + "%' ";
            }

            if (StringUtils.isNotEmpty(areacode)) {
                param += " AND A.AREACODE LIKE '%" + areacode + "%' ";
            }
            PageInfo pageInfo = dmStationBService.selectStationList(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


    @GetMapping(value = "/stationInfoListForSt", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "站点信息列表查询（站点）")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> stationInfoListForSt(
            @RequestParam(name = "lname", required = false, defaultValue = "") String lname,
            @RequestParam(name = "ltel", required = false, defaultValue = "") String ltel,
            @RequestParam(name = "address", required = false, defaultValue = "") String address,
            @RequestParam(name = "areacode", required = false, defaultValue = "") String areacode,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize,
            @RequestParam(name = "unid", required = false, defaultValue = "") String unid
    ) {
        try {
            String param = "";
//            AuthUser user = WebUtils.getCurrentUser();


            if (StringUtils.isNotEmpty(unid)) {
                param += " AND A.UNID = '" + unid + "' ";
            } else {
                param += " AND A.UNID = '" + uuid() + "' ";
            }

            if (StringUtils.isNotEmpty(lname)) {
                param += " AND A.LNAME LIKE '%" + lname + "%' ";
            }


            if (StringUtils.isNotEmpty(ltel)) {
                param += " AND A.LTEL LIKE '%" + ltel + "%' ";
            }
            if (StringUtils.isNotEmpty(address)) {
                param += " AND A.ADDRESS LIKE '%" + address + "%' ";
            }

            if (StringUtils.isNotEmpty(areacode)) {
                param += " AND A.AREACODE LIKE '%" + areacode + "%' ";
            }
            PageInfo pageInfo = dmStationBService.selectStationList(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


    @GetMapping(value = "/selectStationWarnList", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "预警历史查询")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> selectStationWarnList(
            @RequestParam(name = "unid", required = false, defaultValue = "") String unid,
            @RequestParam(name = "uname", required = false, defaultValue = "") String uname,
            @RequestParam(name = "deviceuuid", required = false, defaultValue = "") String deviceuuid,
            @RequestParam(name = "channelno", required = false, defaultValue = "") String channelno,
            @RequestParam(name = "sttm", required = false, defaultValue = "") String sttm,
            @RequestParam(name = "endtm", required = false, defaultValue = "") String endtm,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";


            if (StringUtils.isNotEmpty(unid)) {
                param += " AND UNID = '" + unid + "' ";
            }

            if (StringUtils.isNotEmpty(uname)) {
                param += " AND UNAME LIKE '%" + uname + "%' ";
            }

            if (StringUtils.isNotEmpty(deviceuuid)) {
                param += " AND DEVICEUUID = '" + deviceuuid + "' ";
            }

            if (StringUtils.isNotEmpty(channelno)) {
                param += " AND CHANNELNO = " + channelno + " ";
            }

            if (StringUtil.isNotEmpty(sttm)) {
                param += " and RECEIVETIME >= '" + sttm + "' ";
            }
            if (StringUtil.isNotEmpty(endtm)) {
                param += " and RECEIVETIME <= '" + endtm + "' ";
            }

            PageInfo pageInfo = dmStationBService.selectStationWarnList(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


    @GetMapping(value = "/selectStationWarnListForSt", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "预警历史查询(站点)")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> selectStationWarnListForSt(
            @RequestParam(name = "deviceuuid", required = false, defaultValue = "") String deviceuuid,
            @RequestParam(name = "channelno", required = false, defaultValue = "") String channelno,
            @RequestParam(name = "sttm", required = false, defaultValue = "") String sttm,
            @RequestParam(name = "endtm", required = false, defaultValue = "") String endtm,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize,
            @RequestParam(name = "unid", required = false, defaultValue = "") String unid
    ) {
        try {
            String param = "";


            if (StringUtils.isNotEmpty(unid)) {
                param += " AND A.UNID = '" + unid + "' ";
            } else {
                param += " AND A.UNID = '" + uuid() + "' ";
            }

            if (StringUtils.isNotEmpty(deviceuuid)) {
                param += " AND DEVICEUUID = '" + deviceuuid + "' ";
            }

            if (StringUtils.isNotEmpty(channelno)) {
                param += " AND CHANNELNO = " + channelno + " ";
            }

            if (StringUtil.isNotEmpty(sttm)) {
                param += " and RECEIVETIME >= '" + sttm + "' ";
            }
            if (StringUtil.isNotEmpty(endtm)) {
                param += " and RECEIVETIME <= '" + endtm + "' ";
            }

            PageInfo pageInfo = dmStationBService.selectStationWarnList(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


    @GetMapping(value = "/getStlist", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "获取所有站点")
//    @ApiImplicitParams(
//            {
//                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
//                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
//            }
//    )
    public Map<String, Object> getStlist() {
        try {
            return makeMessage(ReturnCode.SUCCESS, dmStationBService.selectlist());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }

}
