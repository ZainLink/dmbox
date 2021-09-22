package com.zkzy.portal.base.admin.api.service;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.SmsRecordB;

public interface ISmsRecordService {

    /**
     * 新建通讯记录
     *
     * @param Record 通讯记录信息
     */
    CodeObject addRecord(SmsRecordB Record);

    /**
     * 修改通讯记录
     *
     * @param Record 通讯记录信息
     */
    CodeObject updateRecord(SmsRecordB Record);

    /**
     * 删除通讯记录
     *
     * @param Record 通讯记录信息
     */
    CodeObject deleteRecord(SmsRecordB Record);

    /**
     * 分页查询
     */
    PageInfo recordList(String sqlParam, Integer pageNumber, Integer pageSize);

    /**
     * 发送短信
     */
    CodeObject sendSmsRecord();

}
