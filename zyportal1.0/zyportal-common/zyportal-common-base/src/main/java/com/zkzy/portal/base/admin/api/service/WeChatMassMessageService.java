package com.zkzy.portal.base.admin.api.service;

import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.WeChatMessage;
import com.zkzy.portal.base.admin.api.entity.WeChatMessageList;

/**
 * Created by Jisy on 2017/7/21.
 */
public interface WeChatMassMessageService {

    CodeObject sendMessageByTag(WeChatMessage weChatMessage);

    CodeObject sendMessageByTagList(WeChatMessageList weChatMessages);

    //CodeObject sendMessageByOpenids(String weChatId, String openids, String msgType, JSONObject message, String access_token);

    CodeObject sendMessageToAll(WeChatMessage weChatMessage);

//    CodeObject sendMpMessageToAll(WeChatMessage weChatMessage);
}
