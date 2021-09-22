package com.zkzy.portal.base.admin.api.entity;

import java.io.Serializable;

public class WeChatTag implements Serializable {
    private String id;

    private Integer tagid;

    private String tagname;

    private String wechatid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname == null ? null : tagname.trim();
    }

    public String getWechatid() {
        return wechatid;
    }

    public void setWechatid(String wechatid) {
        this.wechatid = wechatid;
    }

    @Override
    public boolean equals(Object obj) {
        WeChatTag weChatTag = (WeChatTag)obj;
        return tagid== weChatTag.getTagid()&&wechatid.equals(weChatTag.getWechatid());
    }
}