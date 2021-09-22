package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.SystemTableProperty;

import java.util.List;

/**
 * Created by Administrator on 2017/6/19 0019.
 */
public interface SystemTablePropertyService {

    SystemTableProperty selectByPrimaryKey(String stpid);

    CodeObject insert(SystemTableProperty systemTableProperty, String tablename, String tableid);

    CodeObject deleteByPrimaryKey(String systemTablePropertyid);

    CodeObject update(SystemTableProperty systemTableProperty);

    List<SystemTableProperty> selectByTablename(String tablename);

    List<SystemTableProperty> selectByTableid(String tableid);

    List<SystemTableProperty> selectToShow(String tableid);

    int deleteByTableid(String tableid);

    int disableById(String id);

    List<SystemTableProperty> selectDisabled(String tableId);
}
