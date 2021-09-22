package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;

/**
 * Created by Thinkpad on 2019/8/26.
 */
public class SystemCodeList implements Serializable {
    private Long codeId;
    private String codeMyid;
    private String name;
    private Long parentId;
    private String description;
    private Long depth;

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public String getCodeMyid() {
        return codeMyid;
    }

    public void setCodeMyid(String codeMyid) {
        this.codeMyid = codeMyid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDepth() {
        return depth;
    }

    public void setDepth(Long depth) {
        this.depth = depth;
    }
}
