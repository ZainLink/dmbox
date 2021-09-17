package com.zkzy.zyportal.system.api.service.Manage;

import com.github.pagehelper.PageInfo;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.entity.dm.NameListManage;
import io.swagger.models.auth.In;

/**
 * Created by Thinkpad-W530 on 2021/4/1.
 */
public interface NameListManageService {

    CodeObject getNamelistInfo(String uuid);

    String addNameList(String uuid, String name, String unid,Integer type);

    String deleteNameList(String uuid, Integer personType);

    String modifyNameList(String uuid, Integer personType, String name);

    String cleanNameList(String uuid, Integer personType);

    PageInfo getNamelistInfos(int currentPage, int pageSize, String param);


}
