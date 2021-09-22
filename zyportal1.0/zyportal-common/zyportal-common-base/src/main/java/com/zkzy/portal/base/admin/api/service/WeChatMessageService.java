package com.zkzy.portal.base.admin.api.service;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.WeChatMessage;

/**
 * Created by Jisy on 2017/7/21.
 */
public interface WeChatMessageService {

    PageInfo queryMessage(Integer pageNumber, Integer pageSize, String param);

//    CodeObject addMessage(WeChatMessage weChatMessage);

    CodeObject delMessage(WeChatMessage weChatMessage);

}
