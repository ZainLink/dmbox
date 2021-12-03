package com.zkzy.portal.dumu.server.system.provider.serviceimpl.dm;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.portal.dumu.server.system.provider.mapper.dm.DmFaceBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStfNMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.dmr.DmStnfRMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.mq.DmKqrecordBMapper;
import com.zkzy.portal.dumu.server.system.provider.mapper.mq.DmKqrecordHMapper;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.constant.ReturnCode;
import com.zkzy.zyportal.system.api.entity.dm.DmFaceB;
import com.zkzy.zyportal.system.api.entity.dmr.DmStfN;
import com.zkzy.zyportal.system.api.entity.dmr.DmStnfR;
import com.zkzy.zyportal.system.api.entity.mq.DmKqrecordB;
import com.zkzy.zyportal.system.api.entity.mq.DmKqrecordH;
import com.zkzy.zyportal.system.api.service.Manage.NameListManageService;
import com.zkzy.zyportal.system.api.service.Person.PersonSetService;
import com.zkzy.zyportal.system.api.service.dm.DmFaceBService;
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
import java.util.List;

import static com.zkzy.portal.common.utils.DateHelper.getDateTime;
import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/3/29.
 */
@Service
public class DmFaceBServiceImpl implements DmFaceBService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DmFaceBServiceImpl.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public static final String BASE_NAME = "base";

    @Autowired
    private DmFaceBMapper dmFaceBMapper;

    @Autowired
    private DmStnfRMapper dmStnfRMapper;

    @Autowired
    private PersonSetService personSetService;

    @Autowired
    private DmStfNMapper dmStfNMapper;

    @Autowired
    private DmKqrecordBMapper dmKqrecordBMapper;

    @Autowired
    private DmKqrecordHMapper dmKqrecordHMapper;


    @Value("${dumu.face}")
    private String facePath;


    public String makeFaceFile(String base64, String id) throws Exception {
        String url = "/" + id + ".jpg";
        String path = facePath + File.separator + id + ".jpg";
        //文件夹不存在则自动创建
        File tempFile = new File(path);
        if (tempFile.getParentFile().exists()) {
            tempFile.delete();
        }
        return Base64Util.base64StrToImage(base64, path) ? url : null;
    }

    public void delFaceFile(String id) throws Exception {
        String path = facePath + File.separator + id + ".jpg";
        //文件夹不存在则自动创建
        File tempFile = new File(path);
        if (tempFile.getParentFile().exists()) {
            tempFile.delete();
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeObject addDmFace(DmFaceB dmFaceB) {
        try {
            String uid = uuid();
            String time = getDateTime();
            dmFaceB.setImgurl(this.makeFaceFile(dmFaceB.getImgurl(), uid));
            dmFaceB.setUuid(uid);
            dmFaceB.setCreatedate(time);
            dmFaceB.setModifydate(time);
            int a = dmFaceBMapper.insert(dmFaceB);
            return a > 0 ? ReturnCode.SUCCESS : ReturnCode.FAILED;//新增成功
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ReturnCode.UNKNOWN_ERROR;//未知错误
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CodeObject delDmFace(String uuid) {
        try {
            if (StringUtils.isNoneEmpty(uuid)) {
                //盒子中还有人脸信息 不能删除
                int a = dmStnfRMapper.selectByUUid(uuid);
                int b = dmStfNMapper.selectByUUid(uuid);
                if (a == 0 && b == 0) {
                    //删除主表人脸信息
                    dmFaceBMapper.deleteByPrimaryKey(uuid);
                    this.delFaceFile(uuid);
                    return ReturnCode.SUCCESS;
                } else {
                    return ReturnCode.FAILED2;
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
    public CodeObject updateDmFace(DmFaceB dmFaceB) {
        try {
            if (StringUtils.isNoneEmpty(dmFaceB.getUuid())) {
                String time = getDateTime();
                dmFaceB.setModifydate(time);
                dmFaceB.setImgurl(this.makeFaceFile(dmFaceB.getImgurl(), dmFaceB.getUuid()));
                List<DmStnfR> list = dmStnfRMapper.selectInfoByUUid(dmFaceB.getUuid());
                //同步信息
                for (DmStnfR d : list) {
                    DmStfN dmStfN = dmStfNMapper.selectByPrimaryKey(d.getUnid(), d.getPersontype(), d.getUuid());
                    if (dmStfN != null) {
                        personSetService.addPerson(d.getDeviceuuid(), d.getUuid(), d.getPersontype().intValue(), dmStfN.getLimittime().intValue(), dmStfN.getSttm(), dmStfN.getEndtime(), d.getUnid());
                    }
                }
                int a = dmFaceBMapper.updateByPrimaryKey(dmFaceB);
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
    public PageInfo selectFaceList(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmFaceB> list = dmFaceBMapper.selectAll(param);
            String path = null;
            for (DmFaceB a : list) {
                path = facePath + "/" + a.getUuid() + ".jpg";
                a.setStLabelstr(this.getStr(a.getStLabel()));
                a.setBoxLabelstr(this.getStr(a.getBoxLabel()));
                a.setImgurl(Base64Util.imageToBase64Str(path));
            }
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public PageInfo selectKqList(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmKqrecordB> list = dmKqrecordBMapper.selectAll(param);
            String path = null;
            for (DmKqrecordB a : list) {
                path = facePath + "/" + a.getUuid() + ".jpg";
                a.setStLabelstr(this.getStr(a.getStLabel()));
                a.setBoxLabelstr(this.getStr(a.getBoxLabel()));
                a.setImgurl(Base64Util.imageToBase64Str(path));
            }
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public PageInfo selectKqHList(int currentPage, int pageSize, String param) {
        try {
            PageHelper.startPage(currentPage, pageSize);
            List<DmKqrecordH> list = dmKqrecordHMapper.selectAll(param);
            for (DmKqrecordH a : list) {
                a.setStLabelstr(this.getStr(a.getStLabel()));
                a.setBoxLabelstr(this.getStr(a.getBoxLabel()));
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
