package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thinkpad-W530 on 2021/4/1.
 */
public class NameListManageResponse implements Serializable {

    private String Name;

    private Data data = new Data();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data implements Serializable {
        private String Action;

        private List<NameLists> nameLists = new ArrayList<>();

        private Integer NameListsNum;

        public Integer getNameListsNum() {
            return NameListsNum;
        }

        public void setNameListsNum(Integer nameListsNum) {
            NameListsNum = nameListsNum;
        }

        public String getAction() {
            return Action;
        }

        public void setAction(String action) {
            Action = action;
        }

        public List<NameLists> getNameLists() {
            return nameLists;
        }

        public void setNameLists(List<NameLists> nameLists) {
            this.nameLists = nameLists;
        }
    }
}
