package com.zkzy.zyportal.system.api.service.set;

import com.zkzy.zyportal.system.api.entity.set.FaceCompareManage;
import com.zkzy.zyportal.system.api.entity.set.FaceParametersRequest;
import com.zkzy.zyportal.system.api.entity.set.HTTPParametersRequest;

/**
 * Created by Thinkpad-W530 on 2021/4/13.
 */
public interface BoxSetService {
    String getFaceParametersRequest(String deviceuuid, Integer channelNo);

    String setFaceParametersRequest(FaceParametersRequest faceParametersRequest);

    String getFaceCompareManage(String deviceuuid, Integer channelNo);

    String setFaceCompareManage(FaceCompareManage faceCompareManage);

    String getHTTPParametersRequest(String deviceuuid);

    String setHTTPParametersRequest(HTTPParametersRequest httpParametersRequest);

    String restartBox(String deviceuuid);

    String getSubDevicesConnectStatus(String deviceuuid);

    String searchSubDevices(String deviceuuid);

    String addSubDevice(String deviceuuid, int ChannelNo, String ip, int Port, String name, String password, String protocol);

    String editSubDevice(String deviceuuid, int ChannelNo, String ip, int Port, String name, String password, String protocol);

    String deleteSubDevice(String deviceuuid, int ChannelNo);
}
