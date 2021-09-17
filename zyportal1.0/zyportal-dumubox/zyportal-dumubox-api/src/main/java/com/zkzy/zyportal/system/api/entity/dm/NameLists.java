package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/4/1.
 */
public class NameLists implements Serializable {
    private String Name;

    private Integer PersonType;

    private Integer NameListFaceNum;

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

    public Integer getNameListFaceNum() {
        return NameListFaceNum;
    }

    public void setNameListFaceNum(Integer nameListFaceNum) {
        NameListFaceNum = nameListFaceNum;
    }
}
