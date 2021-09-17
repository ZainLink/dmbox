package com.zkzy.zyportal.system.api.viewModel;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/23 0023.
 * zTree简单的数据类型
 */
public class ZtreeSimpleView implements Serializable {
    private String id;
    private String pId;
    private String name;
    private String value;

    private boolean open;
    private boolean checked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
