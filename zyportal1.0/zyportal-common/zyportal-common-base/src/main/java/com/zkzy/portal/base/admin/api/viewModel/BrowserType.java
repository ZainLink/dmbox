package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/8.
 */
public class BrowserType implements Serializable{
    private String browsertype;
    private String count;

    public String getBrowsertype() {
        return browsertype;
    }

    public void setBrowsertype(String browsertype) {
        this.browsertype = browsertype;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }



}
