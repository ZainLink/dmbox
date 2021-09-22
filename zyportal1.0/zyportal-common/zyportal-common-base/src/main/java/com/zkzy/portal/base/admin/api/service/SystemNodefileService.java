package com.zkzy.portal.base.admin.api.service;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.SystemNodefile;

import java.util.List;

/**
 * Created by admin on 2017/7/19.
 */
public interface SystemNodefileService {
    
    PageInfo selectAllinfoByPid(int currentPage, int pageSize, String sqlParam);

    List<SystemNodefile> selectAllbyPid(String param);

    CodeObject saveUploadFile(SystemNodefile systemNodefile);

    SystemNodefile saveUploadFileRId(SystemNodefile systemNodefile);

    String saveUploadFileReturnId(SystemNodefile systemNodefile);

    CodeObject delUploadFile(String id);

    CodeObject updateFile(SystemNodefile systemNodefile, String status);

    SystemNodefile getFileObject(String userid, String parentId, String fileData);

    List<SystemNodefile> getFileListByMainid(String mainId, String type);
}
