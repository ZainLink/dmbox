package com.zkzy.portal.base.admin.api.service;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.SystemCode;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
public interface SystemCodeService {

    List<SystemCode> selectAll(String param);

    PageInfo selectparams(int currentPage, int pageSize, String sqlParam);

    CodeObject addSystemCode(SystemCode systemCode);

    CodeObject delSystemCode(SystemCode systemCode);

    CodeObject updateSystemCode(SystemCode systemCode);

     SystemCode selectById(SystemCode systemCode);

    SystemCode selectByMyid(SystemCode systemCode);

    List<SystemCode> selectByMyidFromRedis(String myId);

    List<SystemCode> selectPemisson(String param);

    List<SystemCode> selectParentid(String param);

    List<SystemCode> selectAllparams(String param);

    List<SystemCode> selectTimeTask();
    List<SystemCode> selectAllchilds(long parentid);
    List selectBeanAndClass(String parentId, String Id);

}
