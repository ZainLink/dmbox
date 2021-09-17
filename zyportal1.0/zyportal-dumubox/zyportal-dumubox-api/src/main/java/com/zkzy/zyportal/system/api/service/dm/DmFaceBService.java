package com.zkzy.zyportal.system.api.service.dm;

import com.github.pagehelper.PageInfo;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.entity.dm.DmFaceB;

/**
 * Created by Thinkpad-W530 on 2021/3/29.
 */
public interface DmFaceBService {


    CodeObject addDmFace(DmFaceB dmFaceB);

    CodeObject delDmFace(String uuid);

    CodeObject updateDmFace(DmFaceB dmFaceB);

    PageInfo selectFaceList(int currentPage, int pageSize, String param);

}
