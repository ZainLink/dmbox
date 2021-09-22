package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/9/19.
 */
public class SystemCodeTree implements Serializable {
    private Long codeId;
    private String codeMyid;
    private String name;
    private Long parentId;
    private String description;
    private List nodes = new ArrayList();

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

    public List getNodes() {
        return nodes;
    }

    public void setNodes(List nodes) {
        this.nodes = nodes;
    }
}
