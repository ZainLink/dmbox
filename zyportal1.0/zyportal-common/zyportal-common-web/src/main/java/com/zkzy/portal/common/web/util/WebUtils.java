package com.zkzy.portal.common.web.util;

import cn.jiguang.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkzy.portal.base.admin.api.entity.SystemUser;
import com.zkzy.portal.common.upload.DiskFileOperator;
import com.zkzy.portal.common.upload.util.FileIndex;
import com.zkzy.portal.common.utils.FileHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Web 工具类
 *
 * @author admin
 */
public final class WebUtils {

    /**
     * 文件临时存储路径
     */
    private static final String TEMP_FILE_PATH = "/tmp";

    private WebUtils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 文件类型转换
     *
     * @param file MultipartFile
     * @return File file
     */
    public static File transfer(MultipartFile file) {
        File upFile = new File(FileHelper.addEndSlash(new DiskFileOperator().getWorkFolderName()) + file.getOriginalFilename());
        try {
            file.transferTo(upFile);
        } catch (IllegalStateException | IOException ex) {
            ex.printStackTrace();
        }
        String a = upFile.getAbsolutePath();
        return upFile;
    }

    /**
     * 构建FileIndex
     *
     * @param file   MultipartFile
     * @param folder 文件路径
     * @return FileIndex file index
     */
    public static FileIndex buildFileIndex(MultipartFile file, String folder) {
        return new FileIndex(transfer(file), file.getOriginalFilename(), folder);
    }

    /**
     * 获取当前登录者对象
     *
     * @param <T> the type parameter
     * @return the current user
     */
    @SuppressWarnings("unchecked")
    public static <T extends UserDetails> T getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return (T) authentication.getPrincipal();
        T currentUserAuthUser = (T) getCurrentUserAuthUser(authentication);
        return currentUserAuthUser != null ? (T) currentUserAuthUser : null;
    }

    public final static AuthUser getCurrentUserAuthUser(Authentication authentication) {
        AuthUser authUser = null;
        try {
            if (authentication == null) {
                authentication = SecurityContextHolder.getContext().getAuthentication();
            }
            SystemUser user = getUserInfos(authentication);
            if (user != null) {
                authUser = new AuthUser(user.getId());
                authUser.setLoginName(user.getUsername());
                authUser.setName(user.getRealname());
                authUser.setEmail(user.getEmail());
                authUser.setPhone(user.getTel());
                authUser.setPassword(user.getPassword());
                authUser.setEnabled(user.getStatus().equals(SystemUser.ENABLED) ? true : false);
//                authUser.setAuthorities(getAuthorities(authentication));
                authUser.setManagerId(user.getManagerId());
                authUser.setManagerName(user.getManagerName());
                authUser.setMobile(user.getTel());

                BeanUtils.copyProperties(authUser, user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authUser;
    }

    public final static SystemUser getUserInfos(Authentication authentication) {
        SystemUser systemUser = null;
        try {
            if (authentication == null) {
                authentication = SecurityContextHolder.getContext().getAuthentication();
            }
            Map<String, Object> map = (HashMap) authentication.getPrincipal();
            String jsonUserInfo = JSONObject.toJSONString(map.get(AuthConstant.USERINFOS));

            if (org.apache.commons.lang3.StringUtils.isNotBlank(jsonUserInfo)) {
                systemUser = JSON.parseObject(jsonUserInfo, SystemUser.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return systemUser;
    }

    public final static List<SimpleGrantedAuthority> getAuthorities(Authentication authentication) {
        List<SimpleGrantedAuthority> grantedAuthorities = null;
        try {
            if (authentication == null) {
                authentication = SecurityContextHolder.getContext().getAuthentication();
            }
            Map<String, Object> map = (HashMap) authentication.getPrincipal();
            Object o = map.get(AuthConstant.AUTHORITIES);
            if (o != null) {
                grantedAuthorities = (List<SimpleGrantedAuthority>) o;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return grantedAuthorities;
    }

}
