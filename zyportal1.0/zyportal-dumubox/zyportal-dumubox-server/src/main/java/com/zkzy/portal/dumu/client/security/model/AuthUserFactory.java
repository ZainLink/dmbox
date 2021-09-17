package com.zkzy.portal.dumu.client.security.model;

import com.zkzy.portal.common.utils.StringHelper;
import com.zkzy.zyportal.system.api.entity.base.SystemMenu;
import com.zkzy.zyportal.system.api.entity.base.SystemRole;
import com.zkzy.zyportal.system.api.entity.base.SystemUser;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Auth user factory.
 *
 * @author admin
 */
public final class AuthUserFactory {

    private AuthUserFactory() {
    }

    /**
     * Create auth user.
     *
     * @param user the user
     * @return the auth user
     */
    public static AuthUser create(SystemUser user) {
        AuthUser authUser = new AuthUser(user.getId());
        authUser.setLoginName(user.getUsername());
        authUser.setName(user.getRealname());
        authUser.setEmail(user.getEmail());
        authUser.setPhone(user.getTel());
        authUser.setPassword(user.getPassword());
        authUser.setEnabled(user.getStatus().equals(SystemUser.ENABLED)?true:false);
        authUser.setAuthorities(mapToGrantedAuthorities(user.getRoles(), user.getMenus()));
        return authUser;
    }

    public static AuthSystemUsers create_AuthSystemUsers(SystemUser user) {
        AuthSystemUsers authUser = new AuthSystemUsers(user.getId());
        authUser.setLoginName(user.getUsername());
        authUser.setName(user.getRealname());
        authUser.setEmail(user.getEmail());
        authUser.setPhone(user.getTel());
        authUser.setPassword(user.getPassword());
        try {
            BeanUtils.copyProperties(authUser,user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        authUser.setEnabled(user.getStatus().equals(SystemUser.ENABLED)?true:false);
        authUser.setAuthorities(mapToGrantedAuthorities(user.getRoles(), user.getMenus()));
        return authUser;
    }

    /**
     * 权限转换
     *
     * @param sysRoles 角色列表
     * @param sysMenus 菜单列表
     * @return 权限列表
     */
    private static List<SimpleGrantedAuthority> mapToGrantedAuthorities(List<SystemRole> sysRoles, List<SystemMenu> sysMenus) {

        List<SimpleGrantedAuthority> authorities =
                sysRoles.stream().filter(SystemRole::getEnabled)
                        .map(sysRole -> new SimpleGrantedAuthority(sysRole.getIdentification())).collect(Collectors.toList());

        // 添加基于Permission的权限信息
        sysMenus.stream().filter(menu -> StringHelper.isNotBlank(menu.getPermission())).forEach(menu -> {
            // 添加基于Permission的权限信息
            authorities.add(new SimpleGrantedAuthority(menu.getPermission()));
        });

        return authorities;
    }

}
