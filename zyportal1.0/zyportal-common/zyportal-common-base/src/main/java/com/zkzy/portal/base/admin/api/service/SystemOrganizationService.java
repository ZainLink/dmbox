package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.constant.CodeObjectEX;
import com.zkzy.portal.base.admin.api.entity.SystemOrganization;
import com.zkzy.portal.base.admin.api.viewModel.TreeModel;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
public interface SystemOrganizationService {
    List<SystemOrganization> selectAll(String param);

    List<SystemOrganization> selectBetween(String pid,String slv,String elv);

    List<TreeModel> selectAllTree(String param);

    List<SystemOrganization> queryOrganizationchildListByMyId(String codeMyid);


    CodeObjectEX saveOrganiza(SystemOrganization systemOrganization, String oldPermission);
    CodeObject delbyId(String id);

    SystemOrganization getOrganizaByUserId(String id);

    List<SystemOrganization> selectAllChildByPid(String pid);
}
