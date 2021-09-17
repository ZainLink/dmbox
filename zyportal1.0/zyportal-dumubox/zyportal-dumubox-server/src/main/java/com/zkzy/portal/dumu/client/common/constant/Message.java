package com.zkzy.portal.dumu.client.common.constant;

import com.zkzy.zyportal.system.api.constant.CodeObject;
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

    public static final String RES_Name = "Name";

    public static final String RES_TimeStamp = "TimeStamp";

    public static final String RES_Data = "Data";

    public static final String RES_CODE = "Code";

    public static final String RES_Message = "Message";

    public static final String RES_Session = "Session";


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

    /**
     * 个人中心用户数据
     */
    public static final String USER_SYSTEM_DATA = "user";
    /**
     * HTTP状态码与系统错误代码对应关系
     */
    public static final Map<Integer, String> STATUS_CODE_MAP;

    static {
        Map<Integer, String> codeMappingMap = new HashMap<>();
        codeMappingMap.put(HttpStatus.SC_INTERNAL_SERVER_ERROR, ReturnCode.INTERNAL_SERVER_ERROR);
        codeMappingMap.put(HttpStatus.SC_METHOD_NOT_ALLOWED, ReturnCode.METHOD_NOT_ALLOWED);
        codeMappingMap.put(HttpStatus.SC_BAD_REQUEST, ReturnCode.BAD_REQUEST);
        codeMappingMap.put(HttpStatus.SC_NOT_FOUND, ReturnCode.NOT_FOUND);
        codeMappingMap.put(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE, ReturnCode.UNSUPPORTED_MEDIA_TYPE);
        codeMappingMap.put(HttpStatus.SC_NOT_ACCEPTABLE, ReturnCode.NOT_ACCEPTABLE);
        codeMappingMap.put(HttpStatus.SC_UNAUTHORIZED, ReturnCode.UNAUTHORIZED);
        codeMappingMap.put(HttpStatus.SC_FORBIDDEN, ReturnCode.FORBIDDEN);

        STATUS_CODE_MAP = Collections.unmodifiableMap(codeMappingMap);
    }

    private Message() {
        throw new IllegalAccessError("Utility class");
    }

    public static Map<String,Object> makeMessage(CodeObject ro){
        Map<String, Object> codeMap=new HashMap<String,Object>();
        codeMap.put(Message.RETURN_FIELD_CODE,ro.getCode());
        codeMap.put(Message.RETURN_FIELD_CODE_DESC,ro.getDesc());
        return  codeMap;
    }
}
