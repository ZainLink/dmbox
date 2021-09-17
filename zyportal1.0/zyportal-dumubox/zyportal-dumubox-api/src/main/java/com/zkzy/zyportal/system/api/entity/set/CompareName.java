package com.zkzy.zyportal.system.api.entity.set;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/4/14.
 */
public class CompareName implements Serializable {
    private String Name;

    private Integer  PersonType;


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
