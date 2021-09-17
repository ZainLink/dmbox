package com.zkzy.zyportal.system.api.entity.set;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/4/22.
 */
public class HTTPParametersRequest implements Serializable {

    private String Name = "setHTTPParametersRequest";

    private String UUID;

    private String Session;

    private HTTPParametersRequestData Data = new HTTPParametersRequestData();

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

    public HTTPParametersRequestData getData() {
        return Data;
    }

    public void setData(HTTPParametersRequestData data) {
        Data = data;
    }
}
