//package com.zkzy.portal.dumu.server.system.provider.serviceimpl.base;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.zkzy.portal.dumu.server.system.provider.mapper.base.SystemCodeMapper;
//import com.zkzy.portal.dumu.server.system.provider.mapper.base.SystemParameterMapper;
//import com.zkzy.zyportal.system.api.constant.CodeObject;
//import com.zkzy.zyportal.system.api.constant.ReturnCode;
//
//import com.zkzy.zyportal.system.api.entity.base.SystemCode;
//import com.zkzy.zyportal.system.api.service.base.SystemCodeService;
//import com.zkzy.zyportal.system.api.viewModel.CacheSystemCodeChild;
//import com.zkzy.zyportal.system.api.viewModel.CacheSystemCodeCur;
//import com.zkzy.zyportal.system.api.viewModel.Json;
//
//import org.apache.commons.beanutils.BeanUtils;
//import org.apache.commons.collections.map.HashedMap;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import static com.zkzy.portal.common.utils.DateHelper.getEst8Date;
//
///**
// * Created by Administrator on 2017/4/17 0017.
// */
//
//@Service("systemCodeService")
//public class SystemCodeServiceImpl implements SystemCodeService {
//    public static final String BASE_NAME = "base";
//
//    @Autowired
//    private SystemCodeMapper systemCodeMapper;
//
//    @Autowired
//    private SystemParameterMapper parameterMapper;
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Override
//    public List<SystemCode> selectAll(String param) {
//        return systemCodeMapper.selectAll(param);
//    }
//
//    @Override
//    public PageInfo selectparams(int currentPage, int pageSize, String sqlParam) {
//        PageHelper.startPage(currentPage, pageSize);
//        List<SystemCode> list = systemCodeMapper.selectAll(sqlParam);
//        PageInfo pageInfo = new PageInfo(list);
//        return pageInfo;
//    }
//
//    @Override
//    public CodeObject addSystemCode(SystemCode systemCode) {
//        List<SystemCode> mycode = null;
//        if (systemCode != null) {
//            String systemCodeId = parameterMapper.selectsequence();
//            Date time = getEst8Date();
//            systemCode.setCreated(time);
//            systemCode.setLastmod(time);
//            systemCode.setStatus(ReturnCode.PERSISTENCE_STATUS);
//            systemCode.setCodeId(Long.parseLong(systemCodeId));
//            systemCode.setType("D");
//            if (systemCode.getParentId() == null || systemCode.getParentId().equals("")) {
//                systemCode.setState(ReturnCode.TREE_STATUS_OPEN);
//            } else {
//                String codeparam = "AND CODE_ID =" + systemCode.getParentId() + "";
//                String mycodeparam = "AND CODE_ID =" + systemCode.getParentId() + "";
//                mycode = systemCodeMapper.selectAll(mycodeparam);
//                List<SystemCode> code = systemCodeMapper.selectAll(codeparam);
//                String state = code.get(0).getState();
//                if (!ReturnCode.TREE_STATUS_CLOSED.equals(state)) {
//                    SystemCode sysCode = code.get(0);
//                    sysCode.setState(ReturnCode.TREE_STATUS_CLOSED);
//                    systemCodeMapper.updateStateById(sysCode);
//                }
//                systemCode.setState(ReturnCode.TREE_STATUS_OPEN);
//            }
//            systemCodeMapper.insert(systemCode);
//            redisTemplate.opsForValue().set(BASE_NAME + systemCode.getCodeMyid(), systemCode.getName());
//            if (mycode != null && mycode.get(0).getCodeMyid() != null) {
//                saveChildListRedis(mycode.get(0));
//            }
//            try {
//                this.changeCacheCode(systemCode, systemCode.getCodeMyid(), false);
//            } catch (Exception e) {
//
//            }
//            return ReturnCode.AREA_SUCCESS;
//        } else {
//            return ReturnCode.AREA_FAILED;
//        }
//    }
//
//    @Override
//    public CodeObject delSystemCode(SystemCode systemCode) {
//        if (systemCode != null) {
//            systemCode.setLastmod(getEst8Date());
//            systemCode.setStatus(ReturnCode.PERSISTENCE_DELETE_STATUS);
//            systemCodeMapper.delParamById(systemCode);
//            redisTemplate.delete(BASE_NAME + systemCode.getCodeMyid());
//            redisTemplate.delete(systemCode.getCodeMyid());
//            try {
//                this.changeCacheCode(systemCode, null, true);
//            } catch (Exception e) {
//
//            }
//            String codeparam = "AND CODE_ID =" + systemCode.getParentId() + "";
//            List<SystemCode> code = systemCodeMapper.selectAll(codeparam);
//            List<SystemCode> list = systemCodeMapper.selectTaskById(systemCode);
//            if (code != null && code.get(0).getCodeMyid() != null) {
//                saveChildListRedis(code.get(0));
//            }
//            if (list.size() <= 0) {
//                systemCode.setCodeId(systemCode.getParentId());
//                systemCode.setState(ReturnCode.TREE_STATUS_OPEN);
//                systemCodeMapper.updateStateById(systemCode);
//            }
//            return ReturnCode.DEL_SUCCESS;
//        } else {
//            return ReturnCode.DEL_FAILED;
//        }
//
//    }
//
//    @Override
//    public CodeObject updateSystemCode(SystemCode systemCode) {
//        if (systemCode != null) {
//            SystemCode oldSystemCode = systemCodeMapper.selectCodeById(systemCode);
//
//            systemCode.setLastmod(getEst8Date());
//            systemCodeMapper.updateParamById(systemCode);
//            SystemCode codeParam = systemCodeMapper.selectCodeById(systemCode);
//            String codeparam = "AND CODE_ID =" + systemCode.getParentId() + "";
//            List<SystemCode> code = systemCodeMapper.selectAll(codeparam);
//            redisTemplate.delete(BASE_NAME + systemCode.getCodeMyid());
//            redisTemplate.delete(systemCode.getCodeMyid());
//            redisTemplate.opsForValue().set(BASE_NAME + codeParam.getCodeMyid(), codeParam.getName());
//            if (code != null && code.get(0).getCodeMyid() != null) {
//                saveChildListRedis(code.get(0));
//            }
//            try {
//                this.changeCacheCode(systemCode, oldSystemCode.getCodeMyid(), false);
//            } catch (Exception e) {
//
//            }
//            return ReturnCode.UPDATE_SUCCESS;
//        } else {
//            return ReturnCode.UPDATE_FAILED;
//        }
//
//    }
//
//    @Override
//    public SystemCode selectById(SystemCode systemCode) {
//        SystemCode code = null;
//        if (systemCode != null) {
//            code = systemCodeMapper.selectCodeById(systemCode);
//        }
//        return code;
//    }
//
//    @Override
//    public SystemCode selectByMyid(SystemCode systemCode) {
//        SystemCode code = null;
//        if (systemCode != null) {
//            code = systemCodeMapper.selectCodeByMyId(systemCode);
//        }
//        return code;
//    }
//
//    @Override
//    public List<SystemCode> selectByMyidFromRedis(String myId) {
//        String jsonStr = redisTemplate.opsForValue().get(myId);
//        if (jsonStr != null && !"".equals(jsonStr)) {
//            JSONObject json = JSON.parseObject(jsonStr);
//            String param = json.get("systemCodeList").toString();
//            List<SystemCode> systemCode = JSON.parseArray(param, SystemCode.class);
//            return systemCode;
//        } else {
//            return new ArrayList<SystemCode>();
//        }
//    }
//
//    @Override
//    public List<SystemCode> selectPemisson(String param) {
//        List<SystemCode> code = null;
//        code = systemCodeMapper.selectPemisson(param);
//        return code;
//    }
//
//    @Override
//    public List<SystemCode> selectParentid(String param) {
//        List<SystemCode> code = null;
//        if (param != null) {
//            code = systemCodeMapper.selectParentid(param);
//        }
//        return code;
//    }
//
//    @Override
//    public List<SystemCode> selectAllparams(String param) {
//        return systemCodeMapper.selectAll(param);
//    }
//
//    @Override
//    public List<SystemCode> selectTimeTask() {
//        SystemCode systemCode = new SystemCode();
//        String id = "1";
//        systemCode.setParentId(Long.parseLong(id));
//        return systemCodeMapper.selectTaskById(systemCode);
//    }
//
//    @Override
//    public List<SystemCode> selectAllchilds(long parentid) {
//        SystemCode systemCode = new SystemCode();
//        systemCode.setParentId(parentid);
//        return systemCodeMapper.selectTaskById(systemCode);
//    }
//
//    @Override
//    public List selectBeanAndClass(String parentId, String id) {
//        if (!"".equals(parentId)) {
//            SystemCode systemCode = new SystemCode();
//            systemCode.setParentId(Long.parseLong(parentId));
//            List<SystemCode> list = systemCodeMapper.selectTaskById(systemCode);
//            List<Map<String, Object>> code = new ArrayList<>();
//            for (SystemCode type : list) {
//                Map<String, Object> map = new HashedMap();
//                Long pid = type.getCodeId();
//                SystemCode childcode = new SystemCode();
//                childcode.setParentId(pid);
//                List<SystemCode> child = systemCodeMapper.selectTaskById(childcode);
////                 for (int a=0;a<child.size();a++){
////                     child.get(a).setParentId(Long.parseLong(parentId));
////                 }
//
//                map.put("type", type.getName());
//                map.put("typedata", child);
//                code.add(map);
////                 code.addAll(child);
//            }
//            return code;
//        } else {
//            return null;
//        }
//
//
//    }
//
//    /**
//     * 存入子元素集合到缓存中
//     *
//     * @param pCode 父级对象
//     * @author zhangzy
//     * @version 2017年9月19日
//     * @see 1.0
//     */
//    public void saveChildListRedis(SystemCode pCode) {
//        try {
//            if (pCode != null) {
//                /**取出父code的子元素*/
//                long parentCode = pCode.getCodeId();
//                SystemCode systemCode = new SystemCode();
//                systemCode.setParentId(parentCode);
//                List<SystemCode> childList = systemCodeMapper.selectTaskById(systemCode);
//                /**如果存在子元素则存入缓存*/
//                if (childList != null && childList.size() > 0) {
//                    Json json = new Json();
//                    json.setStatus(true);
//                    json.setSystemCodeList(childList);
//                    String jsonStr = JSONObject.toJSONString(json);
//                    redisTemplate.opsForValue().set(pCode.getCodeMyid(), jsonStr);
//                }
//            }
//        } finally {
//            System.out.println("操作系统数据字典,数据缓存进redis结束");
//        }
//    }
//
//    /**
//     * 修改redis SystemCode  缓存
//     *
//     * @param systemCode
//     * @param isDel
//     * @throws InvocationTargetException
//     * @throws IllegalAccessException
//     */
//    public void changeCacheCode(SystemCode systemCode, String oldCodeMyId, Boolean isDel) throws InvocationTargetException, IllegalAccessException {
//        if (systemCode == null || isDel == null) {
//            return;
//        }
//        if (isDel) {//删除
//            String codeMyid = systemCode.getCodeMyid();
//            redisTemplate.delete(RedisServiceImpl.REDIS_SYSTEMCODE + codeMyid);
//        } else {//新增,编辑时
//            this.changeCurCode(systemCode, oldCodeMyId);
//        }
//        //修改上一级节点关系
//        Long parendId = systemCode.getParentId();
//        if (parendId != null) {
//            SystemCode parentCode = new SystemCode();
//            parentCode.setCodeId(parendId);
//            parentCode = systemCodeMapper.selectCodeById(parentCode);
//            this.changeCurCode(parentCode, parentCode.getCodeMyid());
//        }
//    }
//
//    public void changeCurCode(SystemCode systemCode, String oldCodeMyId) throws InvocationTargetException, IllegalAccessException {
//        redisTemplate.delete(RedisServiceImpl.REDIS_SYSTEMCODE + oldCodeMyId);
//
//        CacheSystemCodeCur cacheSystemCodeCur = new CacheSystemCodeCur();
//        cacheSystemCodeCur.setCurCodeId(systemCode.getCodeId());
//        cacheSystemCodeCur.setCurCodeMyid(systemCode.getCodeMyid());
//        cacheSystemCodeCur.setCurName(systemCode.getName());
//
//        String codeMyid = systemCode.getCodeMyid();
//        String param = " and PARENT_ID in (select code_id from system_code "
//                + " where CODE_MYID='" + codeMyid + "' and status='A' )  and status='A' order by SORT asc ";
//        List<SystemCode> childList = childList = systemCodeMapper.selectAll(param);
//        ;
//        if (childList != null && childList.size() > 0) {
//            cacheSystemCodeCur.setHasChild(true);
//            List<CacheSystemCodeChild> cacheChild = new ArrayList<>();
//            for (SystemCode childCode : childList) {
//                CacheSystemCodeChild cacheSystemCodeChild = new CacheSystemCodeChild();
//                BeanUtils.copyProperties(cacheSystemCodeChild, childCode);
//                cacheChild.add(cacheSystemCodeChild);
//            }
//            cacheSystemCodeCur.setCurChild(cacheChild);
//            redisTemplate.opsForValue().set(RedisServiceImpl.REDIS_SYSTEMCODE + codeMyid, JSONObject.toJSONString(cacheSystemCodeCur));
//        } else {
//            cacheSystemCodeCur.setHasChild(false);
//            redisTemplate.opsForValue().set(RedisServiceImpl.REDIS_SYSTEMCODE + codeMyid, JSONObject.toJSONString(cacheSystemCodeCur));
//        }
//    }
//
//
//}
