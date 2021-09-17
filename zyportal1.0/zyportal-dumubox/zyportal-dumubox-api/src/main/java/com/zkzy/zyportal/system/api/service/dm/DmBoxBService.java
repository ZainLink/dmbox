package com.zkzy.zyportal.system.api.service.dm;

import com.github.pagehelper.PageInfo;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.entity.dm.DmBoxB;

/**
 * Created by Thinkpad-W530 on 2021/4/1.
 */
public interface DmBoxBService {

    PageInfo getBoxList(int currentPage, int pageSize, String param);

    CodeObject updateBox(DmBoxB dmBoxB);


    PageInfo getBoxWhiteList(int currentPage, int pageSize, String param);

}
