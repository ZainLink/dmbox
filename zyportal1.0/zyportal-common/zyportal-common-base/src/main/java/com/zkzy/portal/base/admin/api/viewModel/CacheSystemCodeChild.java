package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public class CacheSystemCodeChild implements Serializable {
    private Long codeId;
    private String codeMyid;
    private String name;

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
}
