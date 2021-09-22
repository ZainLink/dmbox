package com.zkzy.portal.base.admin.api.service;

import com.github.pagehelper.PageInfo;
import com.zkzy.portal.base.admin.api.constant.CodeObject;
import com.zkzy.portal.base.admin.api.entity.EvaluationTables;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/16 0016.
 */
public interface EvaluationTablesService {

    public CodeObject insertEt(EvaluationTables evaluationTables);

    public CodeObject updateEt(EvaluationTables evaluationTables);

    public CodeObject saveOrUpdate(EvaluationTables evaluationTables);

    public CodeObject deleteById(String id);

    public EvaluationTables searchById(String id);

    public PageInfo getTablesList(int currentPage, int pageSize, String sqlParam);

    public EvaluationTables selectByTableName(String tablename);

    public PageInfo seachByLike(int currentPage, int pageSize, String searchinfo);

//    public PageInfo getData(int currentPage, int pageSize,String tablename) throws Exception;

    public List<Map<String, Object>> getData(String tablename, String[] fields) throws Exception;

    public CodeObject changDataValue(String tablename, String fieldname, String value, Map<String, Object> row) throws Exception;

    public CodeObject insertData(String tablename, Map<String, Object> params) throws Exception;

    public CodeObject deleteData(String tablename, Map<String, Object> row) throws Exception;

}
