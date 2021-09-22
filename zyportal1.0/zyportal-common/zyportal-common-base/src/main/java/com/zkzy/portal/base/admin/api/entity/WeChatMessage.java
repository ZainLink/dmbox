package com.zkzy.portal.base.admin.api.entity;

import java.io.Serializable;

public class WeChatMessage implements Serializable,Cloneable {
    private String id;  //编号

    private String touser;  //客服消息发送对象

    private String isToAll; //是否对所有人群发，true群发

    private String tagid;   //根据标签群发的标签id

    private String msgtype; //发送消息类型（text、image、mpnews、voice、video、mpvideo、music）

    private String content; //文本/图文消息内容

    private String mediaId; //媒体文件id

    private String fileUrl; //上传文件（图片、语音、视频）网络地址

    private String mediaFileUrl;    //暂无用

    private String mpnewsId;    //图文消息id

    private String title;   //标题

    private String description; //描述

    private String musicurl;    //音乐文件链接

    private String author;  //作者

    private String url; //音乐链接

    private String picurl;  //暂无用

    private String fromuser;    //发送人

    private Integer type;   //发送类型（客服、群发）

    private String wechatid;    //微信公众号id

    private String sendtime;    //发送时间

    private byte[] contents;    //图文消息二进制内容

    private int issend;     //是否发送成功，1为成功，0为失败

    private String messageId;   //消息id(用于多条消息对应总消息)

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public WeChatMessage(){
        this.type = -1;
    }

    public WeChatMessage(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser == null ? null : touser.trim();
    }

    public String getIsToAll() {
        return isToAll;
    }

    public void setIsToAll(String isToAll) {
        this.isToAll = isToAll == null ? null : isToAll.trim();
    }

    public String getTagid() {
        return tagid;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid == null ? null : tagid.trim();
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype == null ? null : msgtype.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getMediaFileUrl() {
        return mediaFileUrl;
    }

    public void setMediaFileUrl(String mediaFileUrl) {
        this.mediaFileUrl = mediaFileUrl == null ? null : mediaFileUrl.trim();
    }

    public String getMpnewsId() {
        return mpnewsId;
    }

    public void setMpnewsId(String mpnewsId) {
        this.mpnewsId = mpnewsId == null ? null : mpnewsId.trim();
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

    public String getMusicurl() {
        return musicurl;
    }

    public void setMusicurl(String musicurl) {
        this.musicurl = musicurl == null ? null : musicurl.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser == null ? null : fromuser.trim();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public int getIssend(){
        return issend;
    }

    public void setIssend(int issend){
        this.issend = issend;
    }

    @Override
    public WeChatMessage clone(){
        WeChatMessage message = null;
        try{
            message = (WeChatMessage)super.clone();
        }catch(Exception e){
            e.printStackTrace();
        }
        return message;
    }
}