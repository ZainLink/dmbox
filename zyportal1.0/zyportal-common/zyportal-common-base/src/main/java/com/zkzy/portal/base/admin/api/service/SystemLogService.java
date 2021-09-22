package com.zkzy.portal.base.admin.api.service;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.base.admin.api.viewModel.LogPolyline;
import com.zkzy.portal.base.admin.api.viewModel.LogTop;
import com.zkzy.portal.base.admin.api.viewModel.UserLoginLog;

import java.util.List;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public interface SystemLogService {
    List<LogPolyline> queryLogGroupByDate(LogPolyline logPolyline);
    List<LogPolyline> queryAllLogGroupByPermission();
    List<UserLoginLog> queryUserLoginLog(UserLoginLog userLoginLog);
    List<LogTop> queryMenuTop(String sql);
    PageInfo selectUserLogs(String sql, int currentPage, int pageSize);
}
