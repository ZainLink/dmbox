package com.zkzy.zyportal.system.api.service.dmr;

import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.entity.dm.DmBoxB;

/**
 * Created by Thinkpad-W530 on 2021/4/20.
 */
public interface DmStfRService {
    // 站点和 盒子绑定 需要选择 通道（多选）
    CodeObject stBindBoxes(String unid, String deviceuuids);

    void bindBox(String unid, String deviceuuid);


    CodeObject unbind(String unid, String deviceuuid);
}
