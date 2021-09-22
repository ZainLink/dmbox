package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.SystemUploadnode;

import java.util.List;

/**
 * Created by admin on 2017/7/18.
 */
public interface SystemUploadNodeService {
List<SystemUploadnode> selectAll(String param);
    CodeObject insertNode(SystemUploadnode systemUploadnode);
    CodeObject updateNode(SystemUploadnode systemUploadnode);
    SystemUploadnode selectNodebyId(String id);
    CodeObject delNodeById(String id);
}
