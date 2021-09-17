package com.zkzy.zyportal.system.api.constant;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/20.
 */
    public class CodeObject implements Serializable{

    private String code;
    private String desc;

    public CodeObject(){};

    public CodeObject(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CodeObject that = (CodeObject) o;

        if (!code.equals(that.code)) return false;
        return desc.equals(that.desc);
    }

    @Override
    public int hashCode() {
        int result = code.hashCode();
        result = 31 * result + desc.hashCode();
        return result;
    }
}
