package com.zkzy.zyportal.system.api.entity.set;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/4/13.
 */
public class FaceParametersRequest implements Serializable {


    private String Name;

    private String UUID;

    private String Session;

    private FaceParametersRequestData Data = new FaceParametersRequestData();

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

    public FaceParametersRequestData getData() {
        return Data;
    }

    public void setData(FaceParametersRequestData data) {
        Data = data;
    }
}
