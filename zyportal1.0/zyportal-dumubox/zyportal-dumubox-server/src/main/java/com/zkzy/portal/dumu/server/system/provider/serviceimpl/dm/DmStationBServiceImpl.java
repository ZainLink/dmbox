package com.zkzy.portal.dumu.server.system.provider.serviceimpl.dm;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.portal.common.utils.DateUtils;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmBoxBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmNameBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmStationBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.WxGasDstBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.*;
import com.zkzy.portal.dumu.server.system.provider.mapper.mq.DmStrangerHMapper;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.base.SystemUser;
import com.zkzy.zyportal.system.api.entity.dm.*;
import com.zkzy.zyportal.system.api.entity.dmr.DmStR;
import com.zkzy.zyportal.system.api.entity.dmr.DmStfR;
import com.zkzy.zyportal.system.api.entity.dmr.DmStnR;
import com.zkzy.zyportal.system.api.entity.mq.DmStrangerH;
import com.zkzy.zyportal.system.api.service.Manage.NameListManageService;
import com.zkzy.zyportal.system.api.service.Person.PersonSetService;
import com.zkzy.zyportal.system.api.service.base.ISystemUserService;
import com.zkzy.zyportal.system.api.service.dm.DmStationBService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import static com.zkzy.portal.common.utils.DateHelper.getDateTime;
import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/3/23.
 */
@Service
public class DmStationBServiceImpl implements DmStationBService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmStationBServiceImpl.class);

    @Autowired
    private DmStationBMapper dmStationBMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static final String BASE_NAME = "base";

    /**
     * 度目缓存前缀
     */
    public static final String REDIS_PREFIX_DUMU = "dumu:";

    private static final String base64Img = "data:image/jpg;base64,";


    /**
     * 度目实时信息前缀
     */

    public static final String REDIS_PREFIX_DUMU2 = "dm-";


    public static final String REDIS_PREFIX_IP = "ip-";


    @Autowired
    private DmBoxBMapper dmBoxBMapper;

    @Autowired
    private DmStRMapper dmStRMapper;

    @Autowired
    private DmStnRMapper dmStnRMapper;

    @Autowired
    private DmStnfRMapper dmStnfRMapper;

    @Autowired
    private DmNameBMapper dmNameBMapper;

    @Autowired
    private NameListManageService nameListManageService;

    @Autowired
    private DmStfRMapper dmStfRMapper;

    @Autowired
    private DmStfNMapper dmStfNMapper;

    @Autowired
    private DmStrangerHMapper dmStrangerHMapper;

    @Autowired
    private WxGasDstBMapper wxGasDstBMapper;

    @Value("${dumu.stranger}")
    private String stranger;


    public SSDeviceInfo getDevicedSSinfo(String uuid) throws Exception {
        if (redisTemplate.hasKey(REDIS_PREFIX_DUMU2 + uuid)) {
            String jsonStr = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU2 + uuid);
            SSDeviceInfo ssDeviceInfo = JSON.parseObject(jsonStr, SSDeviceInfo.class);
            if (redisTemplate.hasKey(REDIS_PREFIX_DUMU + uuid)) {
                String value = redisTemplate.opsForValue().get(REDIS_PREFIX_DUMU + uuid);
                ssDeviceInfo.setSession(value);
            }
            if (redisTemplate.hasKey(REDIS_PREFIX_IP + uuid)) {
                ssDeviceInfo.setDeviceIP(redisTemplate.opsForValue().get(REDIS_PREFIX_IP + uuid));
            } else {
                DmBoxB dmBoxB = dmBoxBMapper.selectByPrimaryKey(uuid);
                if (dmBoxB != null && StringUtils.isNoneEmpty(dmBoxB.getDeviceip())) {
                    redisTemplate.opsForValue().set(REDIS_PREFIX_IP + dmBoxB.getDeviceuuid(), dmBoxB.getDeviceip());
                    ssDeviceInfo.setDeviceIP(redisTemplate.opsForValue().get(REDIS_PREFIX_IP + uuid));
                } else {
                    return null;
                }
            }
            return ssDeviceInfo;
        } else {
            return null;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeObject addDmStation(DmStationB dmStationB) {
        try {
            String uid = uuid();
            String time = getDateTime();
            dmStationB.setUnid(uid);
            dmStationB.setCreatedate(time);
            dmStationB.setModifydate(time);
//            this.createUser(dmStationB);
            int a = dmStationBMapper.insert(dmStationB);
            DmStfR dmStfR = new DmStfR();
            String lgs = uuid();
            dmStfR.setUnid(uid);
            dmStfR.setPersontype(new BigDecimal(2));
            dmStfR.setName("白名单");
            dmStfR.setLgs(lgs);
            dmStfR.setNum(new BigDecimal(0));
            dmStfRMapper.insert(dmStfR);
            redisTemplate.opsForValue().set(BASE_NAME + uid, dmStationB.getUname());
            return a > 0 ? ReturnCode.SUCCESS : ReturnCode.FAILED;//新增成功
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;//未知错误
        }
    }


    public void addDmStation2(DmStationB dmStationB) {
        try {
            String time = getDateTime();
            dmStationB.setCreatedate(time);
            dmStationB.setModifydate(time);
//            this.createUser(dmStationB);
            int a = dmStationBMapper.insert(dmStationB);
            DmStfR dmStfR = new DmStfR();
            String lgs = uuid();
            dmStfR.setUnid(dmStationB.getUnid());
            dmStfR.setPersontype(new BigDecimal(2));
            dmStfR.setName("白名单");
            dmStfR.setLgs(lgs);
            dmStfR.setNum(new BigDecimal(0));
            dmStfRMapper.insert(dmStfR);
            redisTemplate.opsForValue().set(BASE_NAME + dmStationB.getUnid(), dmStationB.getUname());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeObject loadDmStation() {
        try {
            List<WxGasDstB> list = wxGasDstBMapper.selectAll();
            list.forEach(wxGasDstB -> {
                DmStationB dmStationB = dmStationBMapper.selectByPrimaryKey(wxGasDstB.getCid());
                if (dmStationB == null) {
                    dmStationB = new DmStationB();
                    dmStationB.setUnid(wxGasDstB.getCid());
                    dmStationB.setCreater("系统");
                    dmStationB.setModifyer("系统");
                    dmStationB.setUname(wxGasDstB.getCname());
                    dmStationB.setLname(wxGasDstB.getCleader());
                    dmStationB.setLtel(wxGasDstB.getCtel());
                    dmStationB.setAddress(wxGasDstB.getCaddress());
                    dmStationB.setAreacode(wxGasDstB.getAreacode());
                    dmStationB.setAreaname(wxGasDstB.getAreaname());
                    dmStationB.setLat(wxGasDstB.getLat());
                    dmStationB.setLng(wxGasDstB.getLng());
                    this.addDmStation2(dmStationB);
                } else {
                    dmStationB = new DmStationB();
                    dmStationB.setUnid(wxGasDstB.getCid());
                    dmStationB.setModifyer("系统");
                    dmStationB.setUname(wxGasDstB.getCname());
                    dmStationB.setLname(wxGasDstB.getCleader());
                    dmStationB.setLtel(wxGasDstB.getCtel());
                    dmStationB.setAddress(wxGasDstB.getCaddress());
                    dmStationB.setAreacode(wxGasDstB.getAreacode());
                    dmStationB.setAreaname(wxGasDstB.getAreaname());
                    dmStationB.setLat(wxGasDstB.getLat());
                    dmStationB.setLng(wxGasDstB.getLng());
                    this.updateDmStation(dmStationB);
                }

            });

            return ReturnCode.SUCCESS;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;//未知错误
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeObject delDmStation(String unid) {
        try {
            if (StringUtils.isNoneEmpty(unid)) {
                //查询站点所有的绑定关系
                int num = dmStRMapper.selectBindNumByUnid(unid);
                if (num == 0) {
//                    systemUserService.deleteUserById(unid);
                    dmStationBMapper.deleteByPrimaryKey(unid);
                    redisTemplate.delete(BASE_NAME + unid);
                    dmStfRMapper.deleteByUnid(unid);
                    dmStfNMapper.deleteByUnid(unid);
                    return ReturnCode.SUCCESS;
                } else {
                    return ReturnCode.FAILEDST;
                }

            } else {
                return ReturnCode.NULL_OBJECT;
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;//未知错误
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeObject updateDmStation(DmStationB dmStationB) {
        try {
            if (StringUtils.isNoneEmpty(dmStationB.getUnid())) {
                String time = getDateTime();
                dmStationB.setModifydate(time);
                int a = dmStationBMapper.updateByPrimaryKey(dmStationB);
                redisTemplate.delete(BASE_NAME + dmStationB.getUnid());
                redisTemplate.opsForValue().set(BASE_NAME + dmStationB.getUnid(), dmStationB.getUname());
                return a > 0 ? ReturnCode.SUCCESS : ReturnCode.FAILED;//更新成功
            } else {
                return ReturnCode.NULL_OBJECT;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;//未知错误
        }
    }

    @Override
    public PageInfo selectStationList(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmStationB> list = dmStationBMapper.selectAll(param);
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public PageInfo selectStationWarnList(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmStrangerH> list = dmStrangerHMapper.selectAll(param);
            String fbase = null;
            String bbase = null;

            for (DmStrangerH a : list) {
                if (a.getImgstate().equals("0")) {
                    fbase = Base64Util.imageToBase64Str(stranger + File.separator + a.getFurl());
                    bbase = Base64Util.imageToBase64Str(stranger + File.separator + a.getBurl());
                    a.setBbase(bbase);
                    a.setFbase(fbase);
                }
            }
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeObject stBindBox(String unid, String deviceUUid) {
        try {
            DmStationB dmStationB = dmStationBMapper.selectByPrimaryKey(unid);
            DmBoxB dmBoxB = dmBoxBMapper.selectByPrimaryKey(deviceUUid);
            if (dmStationB != null && dmBoxB != null) {
                String time = getDateTime();
                DmStR dmStR = new DmStR();
                dmStR.setUnid(unid);
                dmStR.setDeviceuuid(deviceUUid);
                dmStR.setBindtime(time);
                DmStR d = dmStRMapper.selectByPrimaryKey(unid, deviceUUid);
                if (d == null) {
                    dmStRMapper.insert(dmStR);
                    this.createDefaultName(unid, deviceUUid);
                    return ReturnCode.SUCCESS;
                } else {
                    return ReturnCode.SUCCESS;
                }

            } else {
                return ReturnCode.ERROR;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;//未知错误
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeObject delBindBox(String unid, String deviceUUid) {
        try {
            DmStationB dmStationB = dmStationBMapper.selectByPrimaryKey(unid);
            DmBoxB dmBoxB = dmBoxBMapper.selectByPrimaryKey(deviceUUid);
            if (dmStationB != null && dmBoxB != null) {
                //删除站点与和字库人脸的绑定关系（逻辑+盒子）
                this.delStnfR(unid, deviceUUid);
                //删除站点与盒子库的绑定关系（逻辑+盒子）
                this.delStnR(unid, deviceUUid);
                //删除站点与盒子的绑定关系（逻辑）
                dmStRMapper.deleteByPrimaryKey(unid, deviceUUid);
                return ReturnCode.SUCCESS;
            } else {
                return ReturnCode.ERROR;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;//未知错误
        }
    }

    /**
     * 查询出盒子库人脸 与站点的关系 不需要删除人脸 因为一会会直接删库
     *
     * @param unid
     * @param deviceUUid
     * @throws Exception
     */
    public void delStnfR(String unid, String deviceUUid) throws Exception {
        dmStnfRMapper.deleteBySt(unid, deviceUUid);
    }


    /**
     * 删除人脸库（逻辑） 命令下发删除 盒子库
     *
     * @param unid
     * @param deviceUUid
     * @throws Exception
     */
    public void delStnR(String unid, String deviceUUid) throws Exception {
        List<DmStnR> list = dmStnRMapper.selectPraviteNames(unid, deviceUUid);
        //删除站点下所有非默认库
        for (DmStnR r : list) {
            nameListManageService.deleteNameList(deviceUUid, r.getPersontype().intValue());
        }
        //逻辑关系删除
        dmStnRMapper.deleteBySt(unid, deviceUUid);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeObject stAddName(String unid, String deviceUUid, String name) {
//        try {
//            DmStationB dmStationB = dmStationBMapper.selectByPrimaryKey(unid);
//            DmBoxB dmBoxB = dmBoxBMapper.selectByPrimaryKey(deviceUUid);
//
//            if (dmStationB != null && dmBoxB != null && dmNameB != null) {
//                String time = getDateTime();
//                DmStnR dmStnR = null;
//                dmStnR = new DmStnR();
//                dmStnR.setUnid(unid);
//                dmStnR.setDeviceuuid(deviceUUid);
//                dmStnR.setNamelistfacenum(new BigDecimal(0));
//                dmStnR.setPersontype(new BigDecimal(personType));
//                dmStnR.setBindtime(time);
//                dmStnRMapper.insert(dmStnR);
//                return ReturnCode.SUCCESS;
//            } else {
//                return ReturnCode.ERROR;
//            }
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            return ReturnCode.UNKNOWN_ERROR;//未知错误
//        }
        return null;
    }


    /**
     * 解除站点与盒子人脸库的关系
     * 待完善库功能
     *
     * @param unid
     * @param deviceUUid
     * @param personType
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeObject delBindName(String unid, String deviceUUid, Integer personType) {
        try {
            DmStationB dmStationB = dmStationBMapper.selectByPrimaryKey(unid);
            DmBoxB dmBoxB = dmBoxBMapper.selectByPrimaryKey(deviceUUid);
            DmNameB dmNameB = dmNameBMapper.selectByPrimaryKey(deviceUUid, new BigDecimal(personType));
            if (dmStationB != null && dmBoxB != null && dmNameB != null) {
                dmStnRMapper.deleteByPrimaryKey(unid, deviceUUid, new BigDecimal(personType));
                dmStnfRMapper.deleteBySt(unid, deviceUUid);
                return ReturnCode.SUCCESS;
            } else {
                return ReturnCode.ERROR;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;//未知错误
        }
    }

    @Override
    public PageInfo selectBindBoxListForSt(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmStR> list = dmStRMapper.selectAll(param);
            SSDeviceInfo ssDeviceInfo = null;
            for (DmStR a : list) {
                a.setAreaname(this.getStr(a.getAreacode()));
                ssDeviceInfo = this.getDevicedSSinfo(a.getDeviceuuid());
                if (ssDeviceInfo != null) {
                    if (StringUtils.isNoneEmpty(ssDeviceInfo.getSession())) {
                        a.setOnline(1);
                    } else {
                        a.setOnline(0);
                    }

                }
            }
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public PageInfo selectBindNameListSt(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmStnR> list = dmStnRMapper.selectAll(param);
            for (DmStnR a : list) {
                int stnum = dmStnfRMapper.selectCountForSt(a.getUnid(), a.getDeviceuuid(), a.getPersontype());
                int totalnum = dmStnfRMapper.selectCountForAdmin(a.getDeviceuuid(), a.getPersontype());
                a.setNamelistfacenum(new BigDecimal(stnum));
                a.setTotalnum(new BigDecimal(totalnum));
            }
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<DmStList> selectlist() {
        try {
            List<DmStList> list = dmStationBMapper.selectlist();
            return list;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }


    public void createDefaultName(String unid, String deviceUUid) throws Exception {
        String time = getDateTime();
        DmStnR dmStnR = null;
        dmStnR = new DmStnR();
        dmStnR.setUnid(unid);
        dmStnR.setDeviceuuid(deviceUUid);
        dmStnR.setNamelistfacenum(new BigDecimal(0));
        dmStnR.setPersontype(new BigDecimal(1));//黑名单
        dmStnR.setBindtime(time);
        dmStnRMapper.insert(dmStnR);
        dmStnR = new DmStnR();
        dmStnR.setUnid(unid);
        dmStnR.setDeviceuuid(deviceUUid);
        dmStnR.setNamelistfacenum(new BigDecimal(0));
        dmStnR.setPersontype(new BigDecimal(2));//白名单
        dmStnR.setBindtime(time);
        dmStnRMapper.insert(dmStnR);
        dmStnR = new DmStnR();
        dmStnR.setUnid(unid);
        dmStnR.setDeviceuuid(deviceUUid);
        dmStnR.setNamelistfacenum(new BigDecimal(0));
        dmStnR.setPersontype(new BigDecimal(3));//vip
        dmStnR.setBindtime(time);
        dmStnRMapper.insert(dmStnR);
    }


//    public void createUser(DmStationB dmStationB) throws Exception {
//        SystemUser systemUser = new SystemUser();
//        String uid = dmStationB.getUnid();
//        systemUser.setId(uid);
//        systemUser.setProjectmainid(dmStationB.getUnid());
//        //用户名规则: "operaman@"+运维人员名称+随机数
//        String account = "dumu-" + dmStationB.getUname() + (int) ((Math.random() * 9 + 1) * 100000);
//        systemUser.setUsername(account);
//        systemUser.setPassword("123456");
//        systemUser.setRealname(dmStationB.getUname());
//        systemUser.setTel(dmStationB.getLtel());
//        systemUser.setRoleId("2ae2af3c771d45589182377164cf36ec");
//        systemUser.setRoleName("dm_video");
//        systemUser.setAreaCode(dmStationB.getAreacode());
//        systemUser.setStatus("1");
//        systemUserService.addUserInfoForPro(systemUser);
//        dmStationB.setAccount(account);
//    }


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
