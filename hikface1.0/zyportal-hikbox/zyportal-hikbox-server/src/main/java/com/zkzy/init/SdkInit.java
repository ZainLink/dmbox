package com.zkzy.init;

import com.zkzy.callback.FMessageCallBack;
import com.zkzy.sdk.HCNetSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/12/20.
 */
public class SdkInit implements Serializable {
    // 接口的实例，通过接口实例调用外部dll/so的函数
    public static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(SdkInit.class);

    public static HCNetSDK.MSGCallBack fMessageCallBack = new FMessageCallBack();

    public SdkInit() {
        try {
            //实例化sdk
            boolean initSuc = hCNetSDK.NET_DVR_Init();
            //设置重连 超时时间
            hCNetSDK.NET_DVR_SetConnectTime(2000, 1);
            hCNetSDK.NET_DVR_SetReconnect(10000, true);
            if (initSuc) {
                LOGGER.info("SDK初始化成功");
            } else {
                LOGGER.error("SDK初始化失败");
            }
            // 打印SDK日志
            hCNetSDK.NET_DVR_SetLogToFile(3, ".\\SDKLog\\", false);

            //设置回调函数
            Boolean success = hCNetSDK.NET_DVR_SetDVRMessageCallBack_V50(0, fMessageCallBack::invoke, null);
            if (success) {
                LOGGER.info("回调设置成功");
            } else {
                LOGGER.error("回调设置失败");
            }
            LOGGER.info("SDK 执行完毕");
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
