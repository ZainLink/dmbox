package com.zkzy.portal.dumu.client.controller.dumu;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zkzy.portal.dumu.client.common.controller.BaseController;
import com.zkzy.portal.dumu.client.security.model.AuthUser;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.DmFaceB;
import com.zkzy.zyportal.system.api.service.dm.DmFaceBService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.zkzy.portal.common.utils.DateHelper.getDateTime;
import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/3/29.
 */
@Validated
@RestController
@RequestMapping("/dm/dmFace")
@Api(tags = "人脸管理Controller")
public class DmFaceBController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmFaceBController.class);

    @Autowired
    private DmFaceBService dmFaceBService;

    private static final ExecutorService pool = Executors.newFixedThreadPool(2);


    @PostMapping(value = "/addFace", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "新增人脸")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public Map<String, Object> addFace(
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "sex", required = true) String sex,
            @RequestParam(name = "tel", required = true) String tel,
            @RequestParam(name = "imgurl", required = true) String imgurl,
            @RequestParam(name = "stLabel", required = true) String stLabel,
            @RequestParam(name = "id", required = false, defaultValue = "") String id,
            @RequestParam(name = "nation", required = false, defaultValue = "") String nation,
            @RequestParam(name = "birth", required = false, defaultValue = "") String birth,
            @RequestParam(name = "address", required = false, defaultValue = "") String address
    ) {
        try {
//            AuthUser user = WebUtils.getCurrentUser();
            DmFaceB dmFaceB = new DmFaceB();
            dmFaceB.setId(id);
            dmFaceB.setName(name);
            dmFaceB.setSex(sex);
            dmFaceB.setTel(tel);
            dmFaceB.setImgurl(imgurl);
            dmFaceB.setNation(nation);
            dmFaceB.setBirth(birth);
            dmFaceB.setAddress(address);
            dmFaceB.setStLabel(stLabel);
            dmFaceB.setCreater("系统");
            dmFaceB.setModifyer("系统");
            return makeMessage(dmFaceBService.addDmFace(dmFaceB));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/addFaceList", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "新增人脸(批量)")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public Map<String, Object> addFaceList(
            @RequestBody String param
    ) {
        try {
//            AuthUser user = WebUtils.getCurrentUser();
            param = StringEscapeUtils.unescapeHtml(param);
            List<DmFaceB> dlist = JSON.parseArray(param, DmFaceB.class);
            if (dlist.size() > 0) {
                if (pool != null) {
                    pool.submit(new Runnable() {
                        @Override
                        public void run() {
                            String name = null;
                            String sex = null;
                            String tel = null;
                            String imgurl = null;
                            String stLabel = null;
                            for (DmFaceB dmFaceB : dlist) {
                                name = dmFaceB.getName();
                                sex = dmFaceB.getSex();
                                tel = dmFaceB.getTel();
                                stLabel = dmFaceB.getStLabel();
                                imgurl = dmFaceB.getImgurl();
                                dmFaceB.setCreater("系统");
                                dmFaceB.setModifyer("系统");
                                if (StringUtils.isNoneEmpty(name, sex, tel, imgurl, stLabel)) {
                                    dmFaceBService.addDmFace(dmFaceB);
                                }
                            }
                        }
                    });
                }
            }
            return makeMessage(ReturnCode.SUCCESS);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @PostMapping(value = "/delFace", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "删除人脸")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public Map<String, Object> delFace(
            @RequestParam(name = "uuid", required = true) String uuid
    ) {
        try {
            return makeMessage(dmFaceBService.delDmFace(uuid));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }
    }

    @PostMapping(value = "/updateFace", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "更新人脸")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public Map<String, Object> updateFace(
            @RequestParam(name = "uuid", required = true) String uuid,
            @RequestParam(name = "name", required = true) String name,
            @RequestParam(name = "sex", required = true) String sex,
            @RequestParam(name = "tel", required = true) String tel,
            @RequestParam(name = "imgurl", required = true) String imgurl,
            @RequestParam(name = "stLabel", required = true) String stLabel,
            @RequestParam(name = "id", required = false, defaultValue = "") String id,
            @RequestParam(name = "nation", required = false, defaultValue = "") String nation,
            @RequestParam(name = "birth", required = false, defaultValue = "") String birth,
            @RequestParam(name = "address", required = false, defaultValue = "") String address
    ) {
        try {
//            AuthUser user = WebUtils.getCurrentUser();
            DmFaceB dmFaceB = new DmFaceB();
            dmFaceB.setUuid(uuid);
            dmFaceB.setId(id);
            dmFaceB.setName(name);
            dmFaceB.setSex(sex);
            dmFaceB.setTel(tel);
            dmFaceB.setImgurl(imgurl);
            dmFaceB.setNation(nation);
            dmFaceB.setBirth(birth);
            dmFaceB.setAddress(address);
            dmFaceB.setStLabel(stLabel);
            dmFaceB.setModifyer("系统");
            return makeMessage(dmFaceBService.updateDmFace(dmFaceB));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.UNKNOWN_ERROR);
        }

    }


    @GetMapping(value = "/faceInfoList", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "人脸信息列表查询")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "Authorization", required = true, paramType = "header",
                            dataType = "string", value = "authorization header", defaultValue = "Bearer ")
            }
    )
    public Map<String, Object> faceInfoList(
            @RequestParam(name = "id", required = false, defaultValue = "") String id,
            @RequestParam(name = "name", required = false, defaultValue = "") String name,
            @RequestParam(name = "sex", required = false, defaultValue = "") String sex,
            @RequestParam(name = "tel", required = false, defaultValue = "") String tel,
            @RequestParam(name = "nation", required = false, defaultValue = "") String nation,
            @RequestParam(name = "address", required = false, defaultValue = "") String address,
            @RequestParam(name = "stLabel", required = false, defaultValue = "") String stLabel,
            @RequestParam(name = "pageNumber", required = true, defaultValue = "1") String pageNumber,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10") String pageSize
    ) {
        try {
            String param = "";


            if (StringUtils.isNotEmpty(id)) {
                param += " AND ID LIKE '%" + id + "%' ";
            }

            if (StringUtils.isNotEmpty(name)) {
                param += " AND NAME LIKE '%" + name + "%' ";
            }

            if (StringUtils.isNotEmpty(sex)) {
                param += " AND SEX = '" + sex + "' ";
            }

            if (StringUtils.isNotEmpty(tel)) {
                param += " AND TEL LIKE '%" + tel + "%' ";
            }
            if (StringUtils.isNotEmpty(nation)) {
                param += " AND NATION LIKE '%" + nation + "%' ";
            }

            if (StringUtils.isNotEmpty(address)) {
                param += " AND ADDRESS LIKE '%" + address + "%' ";
            }

            if (StringUtils.isNotEmpty(stLabel)) {
                param += " AND ST_LABEL LIKE '%" + stLabel + "%' ";
            }
            PageInfo pageInfo = dmFaceBService.selectFaceList(Integer.valueOf(pageNumber), Integer.valueOf(pageSize), param);
            return makeMessage(ReturnCode.SUCCESS, pageInfo);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return makeMessage(ReturnCode.FAILED);
        }
    }


}
