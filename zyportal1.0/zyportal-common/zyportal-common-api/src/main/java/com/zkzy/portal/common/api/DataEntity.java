package com.zkzy.portal.common.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zkzy.portal.common.utils.RandomHelper;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 数据Entity类
 *
 * @author admin
 */
public abstract class DataEntity extends BaseEntity {

    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 更新日期
     */
    private Date updateDate;

    public DataEntity() {
        super();
    }

    public DataEntity(String id) {
        super(id);
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert() {
        // 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
        if (this.getIsNewRecord()) {
            setId(RandomHelper.uuid());
        }
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate() {
        this.updateDate = new Date();
    }

    public Date getCreateDate() {
        return createDate == null ? null : (Date) createDate.clone();
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate == null ? null : (Date) createDate.clone();
    }

    public Date getUpdateDate() {
        return updateDate == null ? null : (Date) updateDate.clone();
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate == null ? null : (Date) updateDate.clone();
    }


}
