package com.zkzy.portal.base.admin.api.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/01/10.
 */
public class WeChatMessageList implements Serializable,Cloneable{
    private String id;  //编号

    private List<WeChatMessage> weChatMessages;  //多条消息的集合

    private String toUser;  //客服消息发送对象

    private String isToAll; //是否对所有人群发，true群发

    private String fromUser;    //发送人

    private Integer type;   //发送类型（客服、群发）

    private String sendTime;    //发送时间

    private String tagId;         //根据标签群发的标签id

    private String msgType;        //发送消息类型（text、image、mpnews、voice、video、mpvideo、music）

    private String weChatId;       //微信公众号id

    private int isSend;           //是否发送成功，1为成功，0为失败

    private String title;         //取第一条消息的标题作为总消息的标题

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsToAll() {
        return isToAll;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsToAll(String isToAll) {
        this.isToAll = isToAll;
    }


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getIsSend() {
        return isSend;
    }

    public void setIsSend(int isSend) {
        this.isSend = isSend;
    }

    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public List<WeChatMessage> getWeChatMessages() {
        return weChatMessages;
    }

    public void setWeChatMessages(List<WeChatMessage> weChatMessages) {
        this.weChatMessages = weChatMessages;
    }
}
