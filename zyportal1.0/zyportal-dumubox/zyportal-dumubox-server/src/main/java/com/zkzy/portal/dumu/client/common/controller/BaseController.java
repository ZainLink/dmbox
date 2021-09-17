package com.zkzy.portal.dumu.client.common.controller;


import com.zkzy.portal.dumu.client.common.constant.Message;
import com.zkzy.portal.dumu.client.common.constant.ReturnCode;
import com.zkzy.portal.common.utils.BeanValidators;
import com.zkzy.portal.common.utils.Collections3;
import com.zkzy.zyportal.system.api.constant.CodeObject;
import com.zkzy.zyportal.system.api.constant.CodeObjectEX;
import com.zkzy.zyportal.system.api.entity.dm.CaptureInfoResponse;
import com.zkzy.zyportal.system.api.entity.dm.HeartbeatResponse;
import com.zkzy.zyportal.system.api.entity.dm.RegisterResponse;
import com.zkzy.zyportal.system.api.exception.base.BusinessException;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器支持类
 *
 * @author admin
 */
public abstract class BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     *
     * @param binder the binder
     */
//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
//        binder.registerCustomEditor(String.class, new StringEditor());
//        // Date 类型转换
//        binder.registerCustomEditor(Date.class, new JSpinner.DateEditor());
//    }

    /**
     * Handle business exception map.
     *
     * @param ex the ex
     * @return the map
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public Map<String, Object> handleBusinessException(BusinessException ex) {
        return makeErrorMessage(ReturnCode.INTERNAL_SERVER_ERROR, "User requests out of rate limit", ex.getMessage());
    }

    /**
     * Handle constraint violation exception map.
     * hibernate 全局验证方法异常处理
     *
     * @param ex the ex
     * @return the map
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> list = BeanValidators.extractMessage(ex);
        return makeErrorMessage(ReturnCode.INVALID_FIELD, "Invalid Field", Collections3.convertToString(list, ";"));
    }

    /**
     * Handle constraint violation exception map.
     * hibernate 全局验证方法异常处理
     *
     * @param ex the ex
     * @return the map
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handleAccessDeniedException(AccessDeniedException ex) {
        return makeErrorMessage(com.zkzy.zyportal.system.api.constant.ReturnCode.NO_AUTHORITY, ex.getMessage());
    }

    /**
     * Make error message map.
     *
     * @param code  the code
     * @param error the error
     * @param desc  the desc
     * @return the map
     */
    protected Map<String, Object> makeErrorMessage(String code, String error, String desc) {
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, code);
        message.put(Message.RETURN_FIELD_ERROR, error);
        message.put(Message.RETURN_FIELD_ERROR_DESC, desc);
        return message;
    }

    protected Map<String, Object> makeErrorMessage(CodeObject ro, String desc) {
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RETURN_FIELD_CODE, ro.getCode());
        message.put(Message.RETURN_FIELD_ERROR, desc);
        message.put(Message.RETURN_FIELD_ERROR_DESC, ro.getDesc());
        return message;
    }

    public static Map<String, Object> makeMessage(CodeObject ro) {
        Map<String, Object> codeMap = new HashMap<String, Object>();
        codeMap.put(Message.RETURN_FIELD_CODE, ro.getCode());
        codeMap.put(Message.RETURN_FIELD_CODE_DESC, ro.getDesc());
        return codeMap;
    }

    public static Map<String, Object> makeMessage(CodeObject ro, Object o) {
        Map<String, Object> codeMap = new HashMap<String, Object>();
        codeMap.put(Message.RETURN_FIELD_CODE, ro.getCode());
        codeMap.put(Message.RETURN_FIELD_CODE_DESC, ro.getDesc());
        codeMap.put(Message.RETURN_FIELD_DATA, o);
        return codeMap;
    }

    public static Map<String, Object> makeMessageToJson(String string) {
        Map<String, Object> codeMap = new HashMap<String, Object>();
        if (StringUtils.isNoneEmpty(string)) {
            String str = JSONObject.fromObject(string).getString("Code");
            if ("1".equals(str)) {
                codeMap.put(Message.RETURN_FIELD_CODE, "0");
                codeMap.put(Message.RETURN_FIELD_CODE_DESC, "成功");
            } else {
                codeMap.put(Message.RETURN_FIELD_CODE, "-1");
                codeMap.put(Message.RETURN_FIELD_CODE_DESC, "失败");
            }

        } else {
            codeMap.put(Message.RETURN_FIELD_CODE, "-1");
            codeMap.put(Message.RETURN_FIELD_CODE_DESC, "失败");
        }
        return codeMap;
    }


    public static Map<String, Object> makeMessageToJsonResult(String string) {
        Map<String, Object> codeMap = new HashMap<String, Object>();
        if (StringUtils.isNoneEmpty(string)) {
            codeMap.put(Message.RETURN_FIELD_CODE, "0");
            codeMap.put(Message.RETURN_FIELD_CODE_DESC, "成功");
            codeMap.put(Message.RETURN_FIELD_DATA, JSONObject.fromObject(string));
        } else {
            codeMap.put(Message.RETURN_FIELD_CODE, "-1");
            codeMap.put(Message.RETURN_FIELD_CODE_DESC, "失败，请检查设备是否离线或设置是否正确");
        }
        return codeMap;
    }

    public static Map<String, Object> makeMessageEX(CodeObjectEX ro) {
        Map<String, Object> codeMap = new HashMap<String, Object>();
        codeMap.put(Message.RETURN_FIELD_CODE, ro.getCo().getCode());
        codeMap.put(Message.RETURN_FIELD_CODE_DESC, ro.getCo().getDesc());
        codeMap.put(Message.RETURN_FIELD_DATA, ro.getObj());
        return codeMap;
    }


    protected Map<String, Object> makeRegisterMessage(RegisterResponse res) {
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RES_Name, res.getName());
        message.put(Message.RES_TimeStamp, res.getTimeStamp());
        message.put(Message.RES_Data, res.getData());
        message.put(Message.RES_CODE, res.getCode());
        message.put(Message.RES_Message, res.getMessage());
        return message;
    }


    protected Map<String, Object> makeHeartMessage(HeartbeatResponse res) {
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RES_Name, res.getName());
        message.put(Message.RES_TimeStamp, res.getTimeStamp());
        message.put(Message.RES_Session, res.getSession());
        message.put(Message.RES_CODE, res.getCode());
        message.put(Message.RES_Message, res.getMessage());
        return message;
    }

    protected Map<String, Object> makeCaptureMessage(CaptureInfoResponse res) {
        Map<String, Object> message = new HashMap<>();
        message.put(Message.RES_Name, res.getName());
        message.put(Message.RES_TimeStamp, res.getTimeStamp());
        message.put(Message.RES_Session, res.getSession());
        message.put(Message.RES_CODE, res.getCode());
        message.put(Message.RES_Message, res.getMessage());
        return message;
    }


}
