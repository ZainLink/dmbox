package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.WeChatMessage;

/**
 * Created by Jisy on 2017/7/18.
 */
public interface WeChatKFMessageService {

    //发送客服消息
    public CodeObject sendServiceMessage(WeChatMessage weChatMessage);
}
