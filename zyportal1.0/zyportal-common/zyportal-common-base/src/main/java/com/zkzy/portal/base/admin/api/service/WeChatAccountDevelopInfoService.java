package com.zkzy.portal.base.admin.api.service;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.WeChatAccountDevelopInfo;


/**
 * Created by Jisy on 2017/7/12.
 */
public interface WeChatAccountDevelopInfoService {
    //增加微信公众号信息
    CodeObject add(WeChatAccountDevelopInfo info);

    //根据ID删除公众号信息
    CodeObject DeleteById(String id);

    //更新公众号信息
    CodeObject UpdateById(WeChatAccountDevelopInfo info);

    //根据id查询公众号信息
    WeChatAccountDevelopInfo selectById(String id);

    //根据类型查询公众号信息
    PageInfo selectByTypecode(int currentPage, int pageSize, String typecode);

    //查询公众号信息
    PageInfo selectAll(int currentPage, int pageSize, String param);

}
