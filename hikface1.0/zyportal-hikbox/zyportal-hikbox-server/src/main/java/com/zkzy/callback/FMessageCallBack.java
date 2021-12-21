package com.zkzy.callback;

import com.alibaba.fastjson.JSON;
import com.sun.jna.Pointer;
import com.zkzy.portal.common.redis.RedisUtil;
import com.zkzy.portal.common.utils.Base64Util;
import com.zkzy.portal.common.utils.DateHelper;
import com.zkzy.portal.common.utils.DateUtils;
import com.zkzy.sdk.HCNetSDK;
import com.zkzy.task.DmTask;
import com.zkzy.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import sun.misc.BASE64Decoder;
import util.HttpClientUtil;
import util.dm.CaptureInfoRequest;
import util.dm.CaptureInfoResponse;
import util.dm.HeartbeatResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.zkzy.portal.common.utils.RandomHelper.uuid;
import static com.zkzy.sdk.HCNetSDK.COMM_UPLOAD_FACESNAP_RESULT;

/**
 * Created by Thinkpad-W530 on 2021/11/30.
 */
public class FMessageCallBack implements HCNetSDK.MSGCallBack {

    private static final Logger LOGGER = LoggerFactory.getLogger(FMessageCallBack.class);

    private static final String captureInfoRequestStr = "captureInfoRequest";//抓怕请求

    public static final String REDIS_CAPTURE = "hk-";


    @Override
    public boolean invoke(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
        this.AlarmDataHandle(lCommand, pAlarmer, pAlarmInfo, dwBufLen, pUser);
        return true;
    }

    public void AlarmDataHandle(int lCommand, HCNetSDK.NET_DVR_ALARMER pAlarmer, Pointer pAlarmInfo, int dwBufLen, Pointer pUser) {
        switch (lCommand) {
            case HCNetSDK.COMM_SNAP_MATCH_ALARM: //人脸比对
                HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM struFaceMatchAlarm = new HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM();
                struFaceMatchAlarm.write();
                Pointer pFaceMatchAlarm = struFaceMatchAlarm.getPointer();
                pFaceMatchAlarm.write(0, pAlarmInfo.getByteArray(0, struFaceMatchAlarm.size()), 0, struFaceMatchAlarm.size());
                struFaceMatchAlarm.read();
                //相似度
                float a = struFaceMatchAlarm.fSimilarity;
                Boolean danger = false;
                if (a <= 0) {
                    danger = true;
                }
                //度目平台实体类
                CaptureInfoRequest captureInfoRequest = null;
                if (struFaceMatchAlarm.struSnapInfo.dwSnapFacePicLen > 0
                        && struFaceMatchAlarm.struSnapInfo.pBuffer1 != null
                        && struFaceMatchAlarm.dwSnapPicLen > 0
                        && struFaceMatchAlarm.pSnapPicBuffer != null) {
                    try {
                        captureInfoRequest = this.getCaptureInfoRequest(struFaceMatchAlarm, danger);
                        //拉取人脸图片
                        long offset = 0;
                        ByteBuffer buffers = struFaceMatchAlarm.struSnapInfo.pBuffer1.getByteBuffer(offset, struFaceMatchAlarm.struSnapInfo.dwSnapFacePicLen);
                        byte[] bytes = new byte[struFaceMatchAlarm.struSnapInfo.dwSnapFacePicLen];
                        buffers.rewind();
                        buffers.get(bytes);
                        String url = new String(bytes);
                        String pic = Base64Util.image2Base64(url);
                        captureInfoRequest.getData().getCaptureInfo().setFacePicture(pic);
                        //拉取背景图片
                        long boffset = 0;
                        ByteBuffer bbuffers = struFaceMatchAlarm.pSnapPicBuffer.getByteBuffer(boffset, struFaceMatchAlarm.dwSnapPicLen);
                        byte[] bbytes = new byte[struFaceMatchAlarm.dwSnapPicLen];
                        bbuffers.rewind();
                        bbuffers.get(bbytes);
                        String burl = new String(bbytes);
                        String bpic = Base64Util.image2Base64(burl);
                        captureInfoRequest.getData().getCaptureInfo().setBackgroundPicture(bpic);
                    } catch (Exception e) {
                        LOGGER.error("实时回调发生异常");
                    }
                }
                this.postToServer(captureInfoRequest);
                break;
        }

    }


    public void postToServer(CaptureInfoRequest captureInfoRequest) {
        //请求至服务端
        if (captureInfoRequest != null) {
            String result = "";
            String jsonstring = JSON.toJSONString(captureInfoRequest);
            try {
                result = HttpClientUtil.postJson(jsonstring, "http://192.168.1.233:8085/server/capture");
            } catch (Exception e) {
                LOGGER.error("网络异常");
                DmTask.register = false;
                DmTask.session = "";
            }
            if (StringUtils.isNoneEmpty(result)) {
                CaptureInfoResponse response = JSON.parseObject(result, CaptureInfoResponse.class);
                if (response.getCode() != 1) {
                    DmTask.register = false;
                    DmTask.session = "";
                } else {
                    LOGGER.info(captureInfoRequest.getData().getCompareInfo().getPersonType() == 0 ? "陌生人抓拍完成" : "白名单抓拍完成");
                }
            }
        }
    }

    public CaptureInfoRequest getCaptureInfoRequest(HCNetSDK.NET_VCA_FACESNAP_MATCH_ALARM struFaceMatchAlarm, Boolean danger) {
        try {
            String time = DateHelper.getDateTime();
            CaptureInfoRequest captureInfoRequest = new CaptureInfoRequest();
            if (danger) {
                captureInfoRequest.getData().getCompareInfo().setPersonType(0); //陌生人
            } else {
                captureInfoRequest.getData().getCompareInfo().setPersonType(2); //白名单
            }
            captureInfoRequest.getData().getDeviceInfo().setDeviceUUID(DmTask.uuid);
            captureInfoRequest.setSession(DmTask.session);
            captureInfoRequest.setName(captureInfoRequestStr);
            captureInfoRequest.setTimeStamp(DateUtils.dateToUnixTimestamp());
            captureInfoRequest.getData().getDeviceInfo().setChannelNo((int) struFaceMatchAlarm.struSnapInfo.struDevInfo.byChannel - 1);
            captureInfoRequest.getData().getFaceInfo().setFaceId(struFaceMatchAlarm.struSnapInfo.dwSnapFacePicID);
            captureInfoRequest.getData().getCaptureInfo().setSendTime(time);
            captureInfoRequest.getData().getCaptureInfo().setCaptureTime(time);
            return captureInfoRequest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}



