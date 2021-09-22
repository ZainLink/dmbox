package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;

public class FileRel implements Serializable {
    private String fileId;
    private String type;


    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FileRel() {
    }

    public FileRel(String fileId) {
        this.fileId = fileId;
    }

    public FileRel(String fileId, String type) {
        this.fileId = fileId;
        this.type = type;
    }
}
