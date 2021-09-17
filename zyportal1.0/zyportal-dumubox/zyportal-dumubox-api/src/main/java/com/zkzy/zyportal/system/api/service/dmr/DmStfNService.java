package com.zkzy.zyportal.system.api.service.dmr;

import com.github.pagehelper.PageInfo;
import com.zkzy.zyportal.system.api.constant.CodeObject;

/**
 * Created by Thinkpad-W530 on 2021/4/20.
 */
public interface DmStfNService {

    // 人员导入站点名白名单
    CodeObject importWhiteList(String unid, String userids, Integer LimitTime, String sttm, String endtime);

    void importPeople(String unid, String userid, Integer LimitTime, String sttm, String endtime);

    // 人员删除站点白名单
    CodeObject delWhiteList(String unid, String userids);

    void delPeople(String unid, String userid);


    //白名单查询
    PageInfo getWhiteList(int currentPage, int pageSize, String param);

}
