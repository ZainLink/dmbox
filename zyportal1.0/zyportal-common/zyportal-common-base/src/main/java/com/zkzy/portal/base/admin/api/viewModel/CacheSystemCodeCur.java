package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public class CacheSystemCodeCur implements Serializable {
    private Long curCodeId;
    private String curCodeMyid;
    private String curName;
    private boolean hasChild;
    private List<CacheSystemCodeChild> curChild;

    public Long getCurCodeId() {
        return curCodeId;
    }

    public void setCurCodeId(Long curCodeId) {
        this.curCodeId = curCodeId;
    }

    public String getCurCodeMyid() {
        return curCodeMyid;
    }

    public void setCurCodeMyid(String curCodeMyid) {
        this.curCodeMyid = curCodeMyid;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public List<CacheSystemCodeChild> getCurChild() {
        return curChild;
    }

    public void setCurChild(List<CacheSystemCodeChild> curChild) {
        this.curChild = curChild;
    }
}

