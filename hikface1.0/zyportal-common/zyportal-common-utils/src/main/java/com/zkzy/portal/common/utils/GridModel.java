package com.zkzy.portal.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhangzy on 2017/5/12.
 */
public class GridModel {
    private List rows = new ArrayList();
    private List footer = new ArrayList();
    private Long total = 0L;
    private int pages=0;
    private Object ExtraData;

    private boolean ret;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getFooter() {
        return footer;
    }

    public void setFooter(List footer) {
        this.footer = footer;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Object getExtraData() {
        return ExtraData;
    }

    public void setExtraData(Object extraData) {
        ExtraData = extraData;
    }
}
