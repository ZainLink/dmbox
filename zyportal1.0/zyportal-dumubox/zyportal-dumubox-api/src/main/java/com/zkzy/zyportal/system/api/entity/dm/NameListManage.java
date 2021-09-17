package com.zkzy.zyportal.system.api.entity.dm;

import com.zkzy.portal.common.utils.DateUtils;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/4/1.
 */
public class NameListManage implements Serializable {

    private String Name = "NameListManage";

    private String UUID;

    private String Session;

    private Long TimeStamp = DateUtils.dateToUnixTimestamp();

    private String Sign;

    private Data Data=new Data();

    public NameListManage.Data getData() {
        return Data;
    }

    public void setData(NameListManage.Data data) {
        Data = data;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

    public Long getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getSign() {
        return Sign;
    }

    public void setSign(String sign) {
        Sign = sign;
    }

    public class Data implements Serializable {

        private String Action;

        private String Name;

        private Integer PersonType;

        public String getAction() {
            return Action;
        }

        public void setAction(String action) {
            Action = action;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public Integer getPersonType() {
            return PersonType;
        }

        public void setPersonType(Integer personType) {
            PersonType = personType;
        }
    }
}
