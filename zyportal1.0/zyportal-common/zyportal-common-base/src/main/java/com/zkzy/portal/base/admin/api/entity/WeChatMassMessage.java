package com.zkzy.portal.base.admin.api.entity;

import java.io.Serializable;

public class WeChatMassMessage implements Serializable {
    private String id;

    private String filter;

    private String isToAll;

    private String tagId;

    private String mpnews;

    private String mediaId;

    private String msgtype;

    private String title;

    private String description;

    private String thumbMediaId;

    private Integer sendIgnoreReprint;

    private String wechatid;

    private String sendtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter == null ? null : filter.trim();
    }

    public String getIsToAll() {
        return isToAll;
    }

    public void setIsToAll(String isToAll) {
        this.isToAll = isToAll == null ? null : isToAll.trim();
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId == null ? null : tagId.trim();
    }

    public String getMpnews() {
        return mpnews;
    }

    public void setMpnews(String mpnews) {
        this.mpnews = mpnews == null ? null : mpnews.trim();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype == null ? null : msgtype.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId == null ? null : thumbMediaId.trim();
    }

    public Integer getSendIgnoreReprint() {
        return sendIgnoreReprint;
    }

    public void setSendIgnoreReprint(Integer sendIgnoreReprint) {
        this.sendIgnoreReprint = sendIgnoreReprint;
    }

    public String getWechatid() {
        return wechatid;
    }

    public void setWechatid(String wechatid) {
        this.wechatid = wechatid == null ? null : wechatid.trim();
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime == null ? null : sendtime.trim();
    }
}