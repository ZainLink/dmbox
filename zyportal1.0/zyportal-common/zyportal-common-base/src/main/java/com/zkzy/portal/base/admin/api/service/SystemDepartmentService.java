package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.constant.CodeObjectEX;
import com.zkzy.portal.base.admin.api.entity.SystemDepartment;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
public interface SystemDepartmentService {
    List<SystemDepartment> selectAll(String param);

//    List<SystemDepartment> selectBetween(String pid, String slv, String elv);
//    List<TreeModel> selectAllTree(String param);
    List<SystemDepartment> queryDepartmentchildListByMyId(String codeMyid);


    CodeObjectEX addOrUpdate(SystemDepartment systemDepartment, String oldPermission);
    CodeObject delbyId(String id);

    List<SystemDepartment> queryHzbTree(String s);
//    SystemOrganization getDepartmentByUserId(String id);
//    List<SystemDepartment> selectAllChildByPid(String pid);
}
