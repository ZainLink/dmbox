package util.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/3/29.
 */
public class RegisterResponse implements Serializable {


    private String Name;

    private Long TimeStamp;

    private Integer Code;

    private String Message;

    private Data data = new Data();



    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }



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

    public class Data implements Serializable {

        private String Session;

        private String ServerVersion = "1.1.0";


        public String getSession() {
            return Session;
        }

        public void setSession(String session) {
            Session = session;
        }


        public String getServerVersion() {
            return ServerVersion;
        }

        public void setServerVersion(String serverVersion) {
            ServerVersion = serverVersion;
        }
    }

    @Override
    public String toString() {
        String param = "盒子";
        if (Code == 1) {
            param += "注册成功,Session=" + data.getSession();
        } else if (Code == 1001) {
            param += "注册失败";
        } else {
            param += "注册异常";
        }
        return param;
    }
}
