package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.ImDevelopAccount;
import com.zkzy.portal.base.admin.api.entity.SocialContacts;
import com.zkzy.portal.base.admin.api.entity.SocialGroup;
import com.zkzy.portal.base.admin.api.entity.SystemUser;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13 0013.
 */

public interface SocialService {

    SocialContacts loginAccount(String userId);

    String getSig(String account,String timestamp) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    List<SocialContacts> getContactsNoGroup(String name);

    Map<String, List<SocialContacts>> getContacts();

    SocialContacts createSubAccount(String friendlyName, SystemUser systemUser);

    CodeObject closeSubAccount(String userId);

    SocialContacts createAccount(SystemUser systemUser);

    int updateAccount(SystemUser systemUser);

    int saveImdevelopAccount(ImDevelopAccount imDevelopAccount);

    String getAppId();

    ImDevelopAccount getImDevelopAccount();

    int saveMyGroup(SocialGroup socialGroup);

    List<SocialGroup> getMyGroupList(String userId);

    SocialGroup getGroupDetail(String id);


    //向IM服务端请求的接口

    String imServerCreateGroup(String userName,String groupName,String type,String permission,String declared,String target,String groupDomain);

    int inviteJoinGroup(String groupId,String [] members);

    int kickGroup(String groupId,String member);


    int dismissGroup(String groupId);

    int updateGroupDetail(String groupId,String groupName,String declared);
}
