package com.zkzy.zyportal.system.api.service.base;



import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.entity.base.SystemUser;


import java.util.List;
import java.util.Map;

public interface ISystemUserService {


    SystemUser getUserByLoginName(String userName);


    SystemUser getUserInfoByLoginName(String userName);

    CodeObject addUserInfoForPro(SystemUser systemUser);


    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUserById(String id);

}
