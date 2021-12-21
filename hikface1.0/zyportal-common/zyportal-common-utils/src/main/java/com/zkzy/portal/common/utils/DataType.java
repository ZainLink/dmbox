package com.zkzy.portal.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16 0016.
 */
public class DataType {

    public static final String BLOB = "BLOB";

    public static final String CHAR="CHAR";

    public static final String NUMBER="NUMBER";

    public static final String VARCHAR = "VARCHAR";

    public static final String VARCHAR2 = "VARCHAR2";

    public static final String NVARCHAR2 = "NVARCHAR2";

    public static final String DATE="DATE";

    public static final String LONG="LONG";

    public static final String FLOAT="FLOAT";



    public static List<String> getDataTypes(){
        List<String> list = new ArrayList<>();
        list.add(BLOB);
        list.add(CHAR);
        list.add(NUMBER);
        list.add(VARCHAR);
        list.add(VARCHAR2);
        list.add(NVARCHAR2);
        list.add(DATE);
        list.add(LONG);
        list.add(FLOAT);
        return list;
    }



}
