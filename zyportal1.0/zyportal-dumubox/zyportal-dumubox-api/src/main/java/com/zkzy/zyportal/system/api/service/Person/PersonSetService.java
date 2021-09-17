package com.zkzy.zyportal.system.api.service.Person;

import com.zkzy.zyportal.system.api.entity.dmr.DmStfN;

import java.util.List;

/**
 * Created by Thinkpad-W530 on 2021/3/31.
 */
public interface PersonSetService {

    String addPerson(String deviceUUid, String userId, Integer personType, Integer LimitTime, String sttm, String endtime,String unid);

    String delPerson(String deviceUUid, String userId, Integer personType,String unid);

    String dePersonList(String deviceUUid, String userIds, Integer personType,String unid);

    void dePersonListByWhiteList(String deviceUUid, List<DmStfN> list, Integer personType, String unid);
}
