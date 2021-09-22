package com.zkzy.portal.base.admin.api.entity;


import java.io.Serializable;

public class SystemAttachmentR implements Serializable{

    private String mainId;

    private String fileId;

    private String type;

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId == null ? null : mainId.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SystemAttachmentR() {
    }

    public SystemAttachmentR(String mainId) {
        this.mainId = mainId;
    }

    public SystemAttachmentR(String fileId, String type) {
        this.fileId = fileId;
        this.type = type;
    }

    public SystemAttachmentR(String mainId, String fileId, String type) {
        this.mainId = mainId;
        this.fileId = fileId;
        this.type = type;
    }
}