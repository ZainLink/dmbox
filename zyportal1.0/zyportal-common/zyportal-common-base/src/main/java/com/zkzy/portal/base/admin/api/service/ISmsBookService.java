package com.zkzy.portal.base.admin.api.service;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.SmsBookB;
import com.zkzy.portal.base.admin.api.viewModel.ZtreeSimpleView;

import java.util.List;

public interface ISmsBookService {

    /**
     * 获取通讯录树结构
     *
     */
    List<ZtreeSimpleView> getBookZTree();

    /**
     * 新建通讯录
     *
     * @param book 通讯录信息
     */
    CodeObject addBook(SmsBookB book);

    /**
     * 修改通讯录
     *
     * @param book 通讯录信息
     */
    CodeObject updateBook(SmsBookB book);

    /**
     * 删除通讯录
     *
     * @param book 通讯录信息
     */
    CodeObject deleteBook(SmsBookB book);

    /**
     * 分页查询
     */
    PageInfo bookList(String sqlParam, Integer pageNumber, Integer pageSize);

}
