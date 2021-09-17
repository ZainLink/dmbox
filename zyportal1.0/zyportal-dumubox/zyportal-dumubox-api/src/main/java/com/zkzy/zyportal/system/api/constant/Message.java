package com.zkzy.zyportal.system.api.constant;

import org.apache.http.HttpStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据常量
 *
 * @author admin
 */
public final class Message {

    /**
     * 状态
     */
    public static final String RETURN_FIELD_CODE = "code";
    /**
     * 状态描述
     */
    public static final String RETURN_FIELD_CODE_DESC = "desc";
    /**
     * 错误信息
     */
    public static final String RETURN_FIELD_ERROR = "error";
    /**
     * 错误描述
     */
    public static final String RETURN_FIELD_ERROR_DESC = "desc";
    /**
     * 返回数据
     */
    public static final String RETURN_FIELD_DATA = "data";


    public static Map<String,Object> makeMessage(CodeObject ro){
        Map<String, Object> codeMap=new HashMap<String,Object>();
        codeMap.put(Message.RETURN_FIELD_CODE,ro.getCode());
        codeMap.put(Message.RETURN_FIELD_CODE_DESC,ro.getDesc());
        return  codeMap;
    }
}
