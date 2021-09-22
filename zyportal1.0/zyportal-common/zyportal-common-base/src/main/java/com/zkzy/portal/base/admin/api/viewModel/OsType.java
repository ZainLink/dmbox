package com.zkzy.portal.base.admin.api.viewModel;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/8.
 */
public class OsType implements Serializable {
    private String ostype;
    private String count;

    public String getOstype() {
        return ostype;
    }

    public void setOstype(String ostype) {
        this.ostype = ostype;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


}
