package com.zkzy.portal.base.admin.api.service;


import com.github.pagehelper.PageInfo;
import com.zkzy.portal.common.api.Paging;
import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.*;
import com.zkzy.portal.base.admin.api.entity.SystemUser;
import com.zkzy.portal.base.admin.api.viewModel.BrowserType;
import com.zkzy.portal.base.admin.api.viewModel.OsType;

import java.util.List;
import java.util.Map;

public interface ISystemUserService {

    SystemUser getUserById(String id);

    CodeObject updateInfoByWxOrTel(SystemUser systemUser);

    SystemUser getUserByOpenId(String openid);

    SystemUser getUserByTel(String tel);

    /**
     * 根据登录名获取用户
     *
     * @param userName 登录名
     * @return SystemUser user by login name
     */
    SystemUser getUserByLoginName(String userName);

    SystemUser getUserBasicInfoByName(String userName);

    /**
     * 注册用户
     */
    CodeObject registryUser(SystemUser user);

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     */
    CodeObject updateInfo(SystemUser user);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUserById(String id);

    /**
     * 分页查询
     */
    PageInfo findUserList(Paging page, SystemUser user);

    /**
     * 冻结账户
     *
     * @param username
     * @return
     */
    CodeObject freezeUser(String username);


    void updateOSAndBroswer(String OS, String browser, String username);

    /**
     * 查询用户导航菜单
     *
     * @param userId 用户ID
     * @return 菜单列表 menu nav
     */
    List<SysMenu> getMenuNav(String userId);

    /**
     * 查询用户菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表 menu list
     */
    List<SysMenu> getMenuList(String userId);

    /**
     * 查询用户菜单
     *
     * @param userId 用户ID
     * @return 菜单列表 menu tree
     */
    List<SysMenu> getMenuTree(String userId);

    /**
     * 删除菜单
     *
     * @param menuId 菜单ID
     */
    void deleteMenuById(String menuId);

    /**
     * 查询菜单
     *
     * @param menuId 菜单ID
     * @return 菜单 menu by id
     */
    SysMenu getMenuById(String menuId);

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return the sys menu
     */
    CodeObject saveMenu(SysMenu menu);


    /**
     * 查询角色列表
     *
     * @param page 分页信息
     * @param role 角色
     * @return 角色 page info
     */
    PageInfo<SystemRole> findRolePage(Paging page, SystemRole role);


    /**
     * 查询用户
     *
     * @param roleId 角色ID
     * @return 角色 role by id
     */
    SystemRole getRoleById(String roleId);

    /**
     * 保存角色
     *
     * @param role 角色
     * @return the sys role
     */
    CodeObject saveRole(SystemRole role);

    /**
     * 删除角色
     *
     * @param roleId 角色ID
     */
    CodeObject deleteRoleById(String roleId);

    Map<String, Object> rolePermission(String userId, String ids);

    Map<String, Object> getIdsByUserId(String userId);

    PageInfo userList(String sqlParam, Integer pageNumber, Integer pageSize, String orgids, String roleids);

    CodeObject addUserInfo(SystemUser systemUser);

    CodeObject addUserInfoForPro(SystemUser systemUser);

    CodeObject updateUserInfo(SystemUser systemUser);

    CodeObject delUserInfo(SystemUser systemUser);

    int getAllUserNum();

    int getSexNum(Integer sex);

    int getAgeNum(Integer bottomAage, Integer topAge);

    /**
     * 系统版本统计
     */
    List<OsType> selectAllOsTypes();

    /**
     * 用户浏览器双环
     */
    List<BrowserType> selectBrowInnerTypes();

    List<BrowserType> selectBrowOuterTypes();

    int updateLNGLAT(String username, String longitude, String latitude);

    CodeObject updateUserPassword(SystemUser systemUser);

    CodeObject deleteByOperamanId(String operamanid);
}
