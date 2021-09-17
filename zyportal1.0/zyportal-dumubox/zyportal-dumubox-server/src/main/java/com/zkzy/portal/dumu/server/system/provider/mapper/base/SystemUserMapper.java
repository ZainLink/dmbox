//package com.zkzy.portal.dumu.server.system.provider.mapper.base;
//
//
//import com.zkzy.portal.common.service.dao.CrudDao;
//import com.zkzy.zyportal.system.api.entity.base.Groups;
//import com.zkzy.zyportal.system.api.entity.base.SystemUser;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
///**
// * Created by yupc on 2017/4/8.
// */
//@Mapper
//public interface SystemUserMapper extends CrudDao<SystemUser> {
//
//    /**
//     * 根据登录名称查询用户
//     *
//     * @param userName 登录名
//     * @return SysUser by login name
//     */
//    SystemUser getByUserName(String userName);
//
//
//    /**
//     * 根据登录名称查询用户
//     *
//     * @param userName 登录名
//     * @return SysUser by login name
//     */
//    SystemUser getByUserNameIgnoreStatus(String userName);
//
//
//    void addUserInfo(SystemUser systemUser);
//
//    void deleteUser_RoleById(String id);
//
//    Groups getGropsByUserName(@Param("username") String username);
//
//
//}
