package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/3/30.
 */
public class CaptureInfoResponse implements Serializable {

    private String Name = "captureInfoResponse";

    private Long TimeStamp;

    private Integer Code;

    private String Message;

    private String Session;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        TimeStamp = timeStamp;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

    @Override
    public String toString() {
        String param = "盒子";
        if (Code == 1) {
            param += "抓拍成功,Session=" + Session;
        } else if (Code == 1001) {
            param += "抓拍失败，设备未注册";
        } else {
            param += "抓拍异常";
        }
        return param;
    }
}
