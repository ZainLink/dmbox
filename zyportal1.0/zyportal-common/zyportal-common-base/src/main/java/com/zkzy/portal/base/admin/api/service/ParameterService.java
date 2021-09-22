package com.zkzy.portal.base.admin.api.service;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.base.admin.api.constant.CodeObject;

import com.zkzy.portal.base.admin.api.entity.SystemParameter;

import java.util.List;

/**
 * Created by admin on 2017/6/19.
 */
public interface ParameterService {
    PageInfo selectAll(int currentPage, int pageSize,String sqlParam);
    PageInfo selectGroup(int currentPage, int pageSize);
    CodeObject insertParam(SystemParameter parameter);
    CodeObject updateParamById(SystemParameter parameter);
    CodeObject delparamById(SystemParameter parameter);
    List<SystemParameter> selectAllparams(String sqlParam);
    SystemParameter seleclByMyId(String Id);
    SystemParameter selectByMyIdFromRedis(String id);
 }
