package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.entity.SystemParameter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
public interface SystemParameterService {
    List<SystemParameter> selectAll(String param);
}
