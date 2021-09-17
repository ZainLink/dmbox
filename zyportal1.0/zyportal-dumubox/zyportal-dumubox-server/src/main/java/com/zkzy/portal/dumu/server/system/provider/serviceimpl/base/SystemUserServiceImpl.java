//package com.zkzy.portal.dumu.server.system.provider.serviceimpl.base;
//
//
//import com.zkzy.portal.dumu.server.system.provider.mapper.base.*;
//
//
//import com.zkzy.portal.common.utils.RandomHelper;
//import com.zkzy.zyportal.system.api.constant.CodeObject;
//import com.zkzy.zyportal.system.api.constant.ReturnCode;
//import com.zkzy.zyportal.system.api.entity.base.*;
//import com.zkzy.zyportal.system.api.service.base.ISystemUserService;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by yupc on 2017/4/8.
// */
//@Service
//public class SystemUserServiceImpl implements ISystemUserService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(SystemUserServiceImpl.class);
//
//    /**
//     * 系统用户Mapper
//     */
//    @Autowired
//    private SystemUserMapper systemUserMapper;
//
//    /**
//     * 系统角色Mapper
//     */
//    @Autowired
//    private SystemRoleMapper sysRoleMapper;
//
//
//    /**
//     * 系统菜单Mapper
//     */
//    @Autowired
//    private SystemMenuMapper sysMenuMapper;
//
//
//    @Autowired
//    private SystemUserOrganizationMapper systemUserOrganizationMapper;
//
//
//    @Autowired
//    private SystemUserRoleMapper systemUserRoleMapper;
//
//    @Override
//    public SystemUser getUserByLoginName(String userName) {
//        SystemUser user = systemUserMapper.getByUserName(userName);
//        if (user == null) {
//            return null;
//        }
//        String userId = user.getId();
//        user.setRoles(sysRoleMapper.findListByUserId(userId));
//        List<SystemMenu> menuList = sysMenuMapper.findListByUserId(userId);
//        user.setMenus(menuList);
//        return user;
//    }
//
//    @Override
//    public SystemUser getUserInfoByLoginName(String userName) {
//        SystemUser user = systemUserMapper.getByUserName(userName);
//        if (user == null) {
//            return null;
//        }
//        String userId = user.getId();
//        user.setRoles(sysRoleMapper.findListByUserId(userId));
//        return user;
//    }
//
//    @Override
//    public CodeObject addUserInfoForPro(SystemUser systemUser) {
//        try {
//            //判断是否存在
//            SystemUser oldUser = systemUserMapper.getByUserNameIgnoreStatus(systemUser.getUsername());
//            if (oldUser != null) {//用户存在
//                return ReturnCode.USER_EXIST;
//            }
//
//            systemUser.setCreateDate(new Date());
//            //2017-07-10
////                socialService.createSubAccount(systemUser.getRealname(),systemUser);
//            //2017-07-25
////                socialService.createAccount(systemUser);
//            //2018-01-17
//            boolean needIm = Boolean.valueOf(systemUser.getNeedImAccount());
//            systemUserMapper.addUserInfo(systemUser);
//
//            //新增组织关系
//            String[] organizationNames = systemUser.getOrganizeName() == null ? null : systemUser.getOrganizeName().split(",");
//            String[] organizationIds = systemUser.getOrganizeId() == null ? null : systemUser.getOrganizeId().split(",");
//            if (organizationNames != null && organizationIds != null && organizationNames.length == organizationIds.length) {
//                for (int i = 0; i < organizationNames.length; i++) {
//                    if (organizationIds[i].trim().length() > 0) {
//                        SystemUserOrganization systemUserOrganization = new SystemUserOrganization();
//                        systemUserOrganization.setId(RandomHelper.uuid());
//                        systemUserOrganization.setUserid(systemUser.getId());
//                        systemUserOrganization.setOrganizationid(organizationIds[i]);
//
//                        systemUserOrganizationMapper.insert(systemUserOrganization);
//                    }
//
//                }
//            }
//
//            //新增角色关系
//            String[] roleNames = systemUser.getRoleName() == null ? null : systemUser.getRoleName().split(",");
//            String[] roleIds = systemUser.getRoleId() == null ? null : systemUser.getRoleId().split(",");
//            if (roleNames != null && roleIds != null && roleNames.length == roleIds.length) {
//                for (int j = 0; j < roleNames.length; j++) {
//                    if (roleIds[j].trim().length() > 0) {
//                        SystemUserRole systemUserRole = new SystemUserRole();
//                        systemUserRole.setId(RandomHelper.uuid());
//                        systemUserRole.setUserId(systemUser.getId());
//                        systemUserRole.setRoleId(roleIds[j]);
//
//                        systemUserRoleMapper.insert(systemUserRole);
//
//                    }
//                }
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ReturnCode.AREA_FAILED;//新增失败
//        } finally {
//
//        }
//        return ReturnCode.AREA_SUCCESS;//新增成功
//    }
//
//    @Override
//    public void deleteUserById(String id) {
//        systemUserMapper.deleteById(id);
//        systemUserMapper.deleteUser_RoleById(id);
//    }
//
//
//}
