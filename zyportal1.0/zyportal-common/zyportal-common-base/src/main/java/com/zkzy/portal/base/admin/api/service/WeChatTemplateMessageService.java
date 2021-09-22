package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.constant.CodeObject;

/**
 * Created by Jisy on 2017/8/28.
 */

public interface WeChatTemplateMessageService {
    CodeObject sendMessage(String weChatId,String openid,String templateId,String data);
}
