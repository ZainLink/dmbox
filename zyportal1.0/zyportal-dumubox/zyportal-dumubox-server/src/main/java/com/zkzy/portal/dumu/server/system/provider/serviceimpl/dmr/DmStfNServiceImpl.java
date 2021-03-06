package com.zkzy.portal.dumu.server.system.provider.serviceimpl.dmr;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmFaceBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmStationBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStRMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStfNMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStfRMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStnRMapper;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.DmCameraB;
import com.zkzy.zyportal.system.api.entity.dm.DmFaceB;
import com.zkzy.zyportal.system.api.entity.dm.DmStationB;
import com.zkzy.zyportal.system.api.entity.dmr.*;
import com.zkzy.zyportal.system.api.service.Manage.NameListManageService;
import com.zkzy.zyportal.system.api.service.Person.PersonSetService;
import com.zkzy.zyportal.system.api.service.dmr.DmStfNService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.zkzy.portal.common.utils.DateHelper.getDateTime;
import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/4/21.
 */
@Service
public class DmStfNServiceImpl implements DmStfNService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmStfNServiceImpl.class);

    @Autowired
    private DmStfNMapper dmStfNMapper;

    @Autowired
    private DmStationBMapper dmStationBMapper;

    @Autowired
    private DmFaceBMapper dmFaceBMapper;

    @Autowired
    private DmStRMapper dmStRMapper;

    @Autowired
    private PersonSetService personSetService;

    @Autowired
    private DmStfNService dmStfNService;

    @Autowired
    private DmStfRMapper dmStfRMapper;


    @Autowired
    private NameListManageService nameListManageService;

    @Autowired
    private DmStnRMapper dmStnRMapper;


    @Value("${dumu.face}")
    private String facePath;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static final String BASE_NAME = "base";

    private static final ExecutorService addpool = Executors.newFixedThreadPool(3);

    private static final ExecutorService delpool = Executors.newFixedThreadPool(3);


    @Override

    public CodeObject importWhiteList(String unid, String userids, Integer LimitTime, String sttm, String endtime) {
        if (addpool != null) {
            addpool.submit(new Runnable() {
                @Override
                public void run() {
                    String[] userid = userids.split(",");
                    for (int i = 0; i < userid.length; i++) {
                        dmStfNService.importPeople(unid, userid[i], LimitTime, sttm, endtime);
                    }

                    //???????????????????????????????????????
                    int num = dmStfNMapper.selectWhiteNum(unid, new BigDecimal(2));
                    DmStfR dmStfR = null;

                    while (true) {
                        dmStfR = dmStfRMapper.selectByPrimaryKey(unid, new BigDecimal(2));
                        if (dmStfR != null) {
                            dmStfR.setNum(new BigDecimal(num));
                            int a = dmStfRMapper.updateByPrimaryKey(dmStfR);
                            if (a > 0) {
                                dmStfR.setLgs(uuid());
                                dmStfRMapper.updateLgs(dmStfR);
                                LOGGER.info("????????????");
                                break;
                            }
                        } else {
                            break;
                        }
                    }

                    String time = getDateTime();

                    //????????? ??????????????????????????????
                    List<DmStR> list = dmStRMapper.selectBoxesByUnid(unid);
                    for (DmStR d : list) {
                        //????????????
                        nameListManageService.getNamelistInfo(d.getDeviceuuid());
                        //?????????????????????2??????
                        DmStnR dmStnR = dmStnRMapper.selectInfoByPrimaryKey(unid, d.getDeviceuuid(), new BigDecimal(2));
                        if (dmStnR == null) {
                            dmStnR = new DmStnR();
                            dmStnR.setUnid(unid);
                            dmStnR.setDeviceuuid(d.getDeviceuuid());
                            dmStnR.setPersontype(new BigDecimal(2));
                            dmStnR.setBindtime(time);
                            dmStnR.setNamelistfacenum(new BigDecimal(num));
                            dmStnRMapper.insert(dmStnR);
                        } else {
                            dmStnR = new DmStnR();
                            dmStnR.setUnid(unid);
                            dmStnR.setDeviceuuid(d.getDeviceuuid());
                            dmStnR.setPersontype(new BigDecimal(2));
                            dmStnR.setNamelistfacenum(new BigDecimal(num));
                            dmStnRMapper.updateByPrimaryKey(dmStnR);
                        }
                    }

                }
            });
        }
        return ReturnCode.TB002;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importPeople(String unid, String userid, Integer LimitTime, String sttm, String endtime) {
        try {
            //????????????id ??? ?????? id ????????????
            DmStationB dmStationB = dmStationBMapper.selectByPrimaryKey(unid);
            DmFaceB dmFaceB = dmFaceBMapper.selectByPrimaryKey(userid);
            if (dmStationB != null && dmFaceB != null) {
                String time = getDateTime();
                //????????????????????????
                DmStfN dmStfN = new DmStfN();
                dmStfN.setUnid(unid);
                dmStfN.setPersontype(new BigDecimal(2));
                dmStfN.setDatatime(time);
                dmStfN.setLimittime(LimitTime.longValue());
                dmStfN.setSttm(sttm);
                dmStfN.setEndtime(endtime);
                dmStfN.setUuid(userid);
                int a = dmStfNMapper.updateByPrimaryKey(dmStfN);
                if (a == 0) {
                    dmStfNMapper.insert(dmStfN);
                }
                //????????? ??????????????????????????????
                List<DmStR> list = dmStRMapper.selectBoxesByUnid(unid);
                //???????????????????????? ????????? ?????????
                for (DmStR d : list) {
                    personSetService.addPerson(d.getDeviceuuid(), userid, 2, LimitTime, sttm, endtime, unid);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public CodeObject delWhiteList(String unid, String userids) {

        if (delpool != null) {
            delpool.submit(new Runnable() {
                @Override
                public void run() {
                    String[] userid = userids.split(",");
                    for (int i = 0; i < userid.length; i++) {
                        dmStfNService.delPeople(unid, userid[i]);
                    }

                    //???????????????????????????????????????
                    int num = dmStfNMapper.selectWhiteNum(unid, new BigDecimal(2));
                    DmStfR dmStfR = null;
                    while (true) {
                        dmStfR = dmStfRMapper.selectByPrimaryKey(unid, new BigDecimal(2));
                        if (dmStfR != null) {
                            dmStfR.setNum(new BigDecimal(num));
                            int a = dmStfRMapper.updateByPrimaryKey(dmStfR);
                            if (a > 0) {
                                dmStfR.setLgs(uuid());
                                dmStfRMapper.updateLgs(dmStfR);
                                LOGGER.info("????????????");
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    //????????? ??????????????????????????????
                    List<DmStR> list = dmStRMapper.selectBoxesByUnid(unid);
                    String time = getDateTime();
                    //??????????????????
                    for (DmStR d : list) {
                        personSetService.dePersonList(d.getDeviceuuid(), userids, 2, unid);
                        //????????????
                        nameListManageService.getNamelistInfo(d.getDeviceuuid());

                        //?????????????????????2??????
                        DmStnR dmStnR = dmStnRMapper.selectInfoByPrimaryKey(unid, d.getDeviceuuid(), new BigDecimal(2));
                        if (dmStnR == null) {
                            dmStnR = new DmStnR();
                            dmStnR.setUnid(unid);
                            dmStnR.setDeviceuuid(d.getDeviceuuid());
                            dmStnR.setPersontype(new BigDecimal(2));
                            dmStnR.setBindtime(time);
                            dmStnR.setNamelistfacenum(new BigDecimal(num));
                            dmStnRMapper.insert(dmStnR);
                        } else {
                            dmStnR = new DmStnR();
                            dmStnR.setUnid(unid);
                            dmStnR.setDeviceuuid(d.getDeviceuuid());
                            dmStnR.setPersontype(new BigDecimal(2));
                            dmStnR.setNamelistfacenum(new BigDecimal(num));
                            dmStnRMapper.updateByPrimaryKey(dmStnR);
                        }

                    }
                }
            });
        }
        return ReturnCode.TB003;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delPeople(String unid, String userid) {
        try {
            //????????????id ??? ?????? id ????????????
            DmStationB dmStationB = dmStationBMapper.selectByPrimaryKey(unid);
            DmFaceB dmFaceB = dmFaceBMapper.selectByPrimaryKey(userid);
            if (dmStationB != null && dmFaceB != null) {
                //???????????????????????????
                dmStfNMapper.deleteByPrimaryKey(unid, new BigDecimal(2), userid);
            }


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public PageInfo getWhiteList(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmWhiteList> list = dmStfNMapper.selectAll(param);
            String path = null;
            for (DmWhiteList a : list) {
                path = facePath + "/" + a.getUuid() + ".jpg";
                a.setStLabelstr(this.getStr(a.getStLabel()));
                a.setImgurl(Base64Util.imageToBase64Str(path));
            }
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }


    public String getStr(String codes) {
        if (StringUtils.isNoneEmpty(codes)) {
            String codeStr = "";
            String[] code = codes.split(",");
            for (int i = 0; i < code.length; i++) {
                String codemyid = BASE_NAME + code[i];
                String str = redisTemplate.opsForValue().get(codemyid);
                if (i == 0 && StringUtils.isNoneEmpty(str)) {
                    codeStr += str;
                } else if (StringUtils.isNoneEmpty(str)) {
                    codeStr += "," + str;
                }

            }
            return codeStr;
        } else {
            return "";
        }
    }
}
