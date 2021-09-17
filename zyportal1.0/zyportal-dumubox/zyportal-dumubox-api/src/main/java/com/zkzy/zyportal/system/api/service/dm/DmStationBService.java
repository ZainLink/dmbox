package com.zkzy.zyportal.system.api.service.dm;

import com.github.pagehelper.PageInfo;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.entity.dm.DmStList;
import com.zkzy.zyportal.system.api.entity.dm.DmStationB;

import java.util.List;

/**
 * Created by Thinkpad-W530 on 2021/3/23.
 */
public interface DmStationBService {


    CodeObject addDmStation(DmStationB dmStationB);

    CodeObject delDmStation(String unid);

    CodeObject updateDmStation(DmStationB dmStationB);

    PageInfo selectStationList(int currentPage, int pageSize, String param);


    PageInfo selectStationWarnList(int currentPage, int pageSize, String param);

    /**
     * 站点和盒子绑定
     *
     * @param unid
     * @param deviceUUid
     * @return
     */
    CodeObject stBindBox(String unid, String deviceUUid);


    /**
     * 站点和盒子解绑
     *
     * @param unid
     * @param deviceUUid
     * @return
     */
    CodeObject delBindBox(String unid, String deviceUUid);


    /**
     * 站点与盒子人脸库绑定
     *
     * @param unid
     * @param deviceUUid
     * @param name
     * @return
     */
    CodeObject stAddName(String unid, String deviceUUid, String name);


    /**
     * 站点与盒子人脸库解绑
     *
     * @param unid
     * @param deviceUUid
     * @param personType
     * @return
     */
    CodeObject delBindName(String unid, String deviceUUid, Integer personType);

    /**
     * 站点查询盒子接口
     *
     * @param currentPage
     * @param pageSize
     * @param param
     * @return
     */
    PageInfo selectBindBoxListForSt(int currentPage, int pageSize, String param);

    /**
     * 站点查询人脸库接口
     *
     * @param currentPage
     * @param pageSize
     * @param param
     * @return
     */
    PageInfo selectBindNameListSt(int currentPage, int pageSize, String param);

    List<DmStList> selectlist();

}
