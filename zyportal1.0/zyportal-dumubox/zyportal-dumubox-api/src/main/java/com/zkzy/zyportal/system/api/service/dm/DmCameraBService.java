package com.zkzy.zyportal.system.api.service.dm;

import com.github.pagehelper.PageInfo;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.entity.dm.DmCameraB;

/**
 * Created by Thinkpad-W530 on 2021/4/8.
 */
public interface DmCameraBService {
    PageInfo getCameraList(int currentPage, int pageSize, String param);

    CodeObject updateCamera(DmCameraB dmCameraB);

    CodeObject updateProtocol(String deviceuuid, int ChannelNo, String ip, int Port, String name, String password, String protocol);


}
