package com.zkzy.portal.base.admin.api.constant;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/20.
 */
    public class CodeObjectEX extends CodeObject implements Serializable{

    private CodeObject co;
    private Object obj;

    public CodeObjectEX(CodeObject co) {
        this.co = co;
    }

    public CodeObjectEX(CodeObject co, Object obj) {
        this.co = co;
        this.obj = obj;
    }

    public CodeObject getCo() {
        return co;
    }

    public void setCo(CodeObject co) {
        this.co = co;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CodeObjectEX that = (CodeObjectEX) o;

        if (co != null ? !co.equals(that.co) : that.co != null) return false;
        return obj != null ? obj.equals(that.obj) : that.obj == null;
    }

    @Override
    public int hashCode() {
        int result = co != null ? co.hashCode() : 0;
        result = 31 * result + (obj != null ? obj.hashCode() : 0);
        return result;
    }
}
