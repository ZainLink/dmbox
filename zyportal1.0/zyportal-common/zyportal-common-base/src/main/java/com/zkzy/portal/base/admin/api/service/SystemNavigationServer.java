package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.entity.SystemNavigation;

/**
 * Created by Administrator on 2017/9/26 0026.
 */
public interface SystemNavigationServer {


    SystemNavigation selectByUserId (String userid);

    int saveByUserId(SystemNavigation systemNavigation);


}
