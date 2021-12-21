package com.zkzy.LogIn;

import com.zkzy.facelib.FaceLib;
import com.zkzy.init.SdkInit;
import com.zkzy.sdk.HCNetSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Thinkpad-W530 on 2021/12/20.
 */
public class LogIn implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogIn.class);

    public static Map<String, Integer> users = new ConcurrentHashMap();

    private String ip;

    private short port;

    private String username;

    private String password;


    public LogIn(String ip, short port, String username, String password) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
        try{
            //登录
            int userid = this.login_V40(ip, port, username, password);
            if(userid!=-1){
                //创建当前超脑白名单
                FaceLib.CreateFDLib(0,userid);
                //布防
                this.setUpAlarm(userid);
                LOGGER.info(ip+"登录程序执行完成");
            }else {
                LOGGER.info(ip+"登录程序执行失败");
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }



    }


    public int login_V40(String m_sDeviceIP, short wPort, String m_sUsername, String m_sPassword) {
        /* 注册 */
        // 设备登录信息
        HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();

        // 设备信息
        HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();
        m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
        System.arraycopy(m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());
        m_strLoginInfo.wPort = wPort;
        m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
        System.arraycopy(m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());
        m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
        System.arraycopy(m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());
        // 是否异步登录：false- 否，true- 是
        m_strLoginInfo.bUseAsynLogin = false;
        // write()调用后数据才写入到内存中
        m_strLoginInfo.write();
        int userId = SdkInit.hCNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_strDeviceInfo);
        if (userId == -1) {
            LOGGER.error("登录失败，错误码为" + SdkInit.hCNetSDK.NET_DVR_GetLastError());
            return -1;
        } else {
            LOGGER.info("登录成功");
            m_strDeviceInfo.read();
            return userId;
        }
    }


    public void setUpAlarm(int lUserID) throws Exception {
        //设置回调
//        FMessageCallBack fMessageCallBack = new FMessageCallBack();

        //布防
        HCNetSDK.LPNET_DVR_SETUPALARM_PARAM net_dvr_setupalarm_param = new HCNetSDK.LPNET_DVR_SETUPALARM_PARAM();
        net_dvr_setupalarm_param.dwSize = net_dvr_setupalarm_param.size();
        net_dvr_setupalarm_param.byDeployType=1;
        net_dvr_setupalarm_param.byFaceAlarmDetection = 0;
        net_dvr_setupalarm_param.write();
        int lHandle = SdkInit.hCNetSDK.NET_DVR_SetupAlarmChan_V41(lUserID, net_dvr_setupalarm_param);
        if (lHandle < 0) {
            LOGGER.error("布防失败");
            return;
        } else {
            LOGGER.info("布防成功");
        }
    }

    public static Map<String, Integer> getUsers() {
        return users;
    }

    public static void setUsers(Map<String, Integer> users) {
        LogIn.users = users;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public short getPort() {
        return port;
    }

    public void setPort(short port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
