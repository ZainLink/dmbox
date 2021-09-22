package com.zkzy.portal.base.admin.api.service;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.common.api.Paging;
import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.SystemRole;
import com.zkzy.portal.base.admin.api.viewModel.ZtreeSimpleView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/21 0021.
 */
public interface SystemRoleService {
    PageInfo<SystemRole> roleList(String param, Paging page);
    CodeObject addRole(SystemRole systemRole);
    CodeObject delRole(SystemRole systemRole);
    CodeObject updateRole(SystemRole systemRole);
    List<ZtreeSimpleView> getRoleZtreeSimpleData(String sqlParam);
    List<SystemRole> findRoleJumppageByUserId(String userId);
}
