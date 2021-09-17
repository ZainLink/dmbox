package com.zkzy.zyportal.system.api.entity.dm;

import io.swagger.models.auth.In;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/4/2.
 */
public class PersonListRequest implements Serializable {

    private String Name="personListRequest";

    private String UUID;

    private String Session;

    private Integer TimeStamp;

    private String Sign;

    private Data Data = new Data();

    public PersonListRequest.Data getData() {
        return Data;
    }

    public void setData(PersonListRequest.Data data) {
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

    public Integer getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Integer timeStamp) {
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

        private Integer PersonType;

        private PersonInfo PersonInfo = new PersonInfo();

        public String getAction() {
            return Action;
        }

        public void setAction(String action) {
            Action = action;
        }

        public Integer getPersonType() {
            return PersonType;
        }

        public void setPersonType(Integer personType) {
            PersonType = personType;
        }

        public PersonListRequest.Data.PersonInfo getPersonInfo() {
            return PersonInfo;
        }

        public void setPersonInfo(PersonListRequest.Data.PersonInfo personInfo) {
            PersonInfo = personInfo;
        }

        public class PersonInfo implements Serializable {

            private Integer PersonCover;

            private String PersonId;

            private String PersonName;

            private Integer Sex;

            private String IDCard;

            private String Nation;

            private String Birthday;

            private String Phone;

            private String Address;

            private Integer LimitTime;

            private String StartTime;

            private String EndTime;

            private String Label;

            private String PersonPhoto;

            public Integer getPersonCover() {
                return PersonCover;
            }

            public void setPersonCover(Integer personCover) {
                PersonCover = personCover;
            }

            public String getPersonId() {
                return PersonId;
            }

            public void setPersonId(String personId) {
                PersonId = personId;
            }

            public String getPersonName() {
                return PersonName;
            }

            public void setPersonName(String personName) {
                PersonName = personName;
            }

            public Integer getSex() {
                return Sex;
            }

            public void setSex(Integer sex) {
                Sex = sex;
            }

            public String getIDCard() {
                return IDCard;
            }

            public void setIDCard(String IDCard) {
                this.IDCard = IDCard;
            }

            public String getNation() {
                return Nation;
            }

            public void setNation(String nation) {
                Nation = nation;
            }

            public String getBirthday() {
                return Birthday;
            }

            public void setBirthday(String birthday) {
                Birthday = birthday;
            }

            public String getPhone() {
                return Phone;
            }

            public void setPhone(String phone) {
                Phone = phone;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String address) {
                Address = address;
            }

            public Integer getLimitTime() {
                return LimitTime;
            }

            public void setLimitTime(Integer limitTime) {
                LimitTime = limitTime;
            }

            public String getStartTime() {
                return StartTime;
            }

            public void setStartTime(String startTime) {
                StartTime = startTime;
            }

            public String getEndTime() {
                return EndTime;
            }

            public void setEndTime(String endTime) {
                EndTime = endTime;
            }

            public String getLabel() {
                return Label;
            }

            public void setLabel(String label) {
                Label = label;
            }

            public String getPersonPhoto() {
                return PersonPhoto;
            }

            public void setPersonPhoto(String personPhoto) {
                PersonPhoto = personPhoto;
            }
        }
    }
}
