package com.zkzy.service.impl;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.zkzy.init.SdkInit;
import com.zkzy.sdk.HCNetSDK;
import com.zkzy.service.FaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.zkzy.portal.common.utils.RandomHelper.uuid;

/**
 * Created by Thinkpad-W530 on 2021/12/13.
 */
@Service
public class FaceServiceImpl implements FaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaceServiceImpl.class);

    @Override
    public void addFace() {
        this.uploadSend(0);
    }


    /**
     * 建立长连接
     *
     * @param fdid
     * @return
     */
    public int uploadFile(String fdid, int lUserID) {
        try {
            HCNetSDK.NET_DVR_FACELIB_COND struInput = new HCNetSDK.NET_DVR_FACELIB_COND();
            struInput.dwSize = struInput.size();
            struInput.szFDID = String.valueOf(fdid).getBytes();
            struInput.byConcurrent = 0;
            struInput.byCover = 1;
            struInput.byCustomFaceLibID = 0;
            struInput.dwSize = struInput.size();
            struInput.write();
            Pointer lpInput = struInput.getPointer();
            int ret = SdkInit.hCNetSDK.NET_DVR_UploadFile_V40(lUserID, HCNetSDK.IMPORT_DATA_TO_FACELIB, lpInput, struInput.size(), null, null, 0);
            if(ret==-1){
                LOGGER.error("错误:"+  SdkInit.hCNetSDK.NET_DVR_GetLastError());
            }

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * 上传人脸数据
     */
    public void uploadSend(int lUserID) {
        FileInputStream picfile = null;
        //获取白名单
        int m_lUploadHandle = this.uploadFile("486023E8F2134CAEBA34440C73F3D5BD", lUserID);

        try {
            //人脸录入
            String xmlBuffer = "<FaceAppendData>" +
                    "<name>邹柯柯</name>" +
                    "<customHumanID>" + uuid() + "</customHumanID>" +
                    "</FaceAppendData>";

            HCNetSDK.BYTE_ARRAY xmlByte = new HCNetSDK.BYTE_ARRAY(10 * HCNetSDK.BYTE_ARRAY_LEN);
            xmlByte.byValue = xmlBuffer.getBytes();
            xmlByte.write();
            int picdataLength = 0;
            picfile = new FileInputStream(new File("C:\\Users\\Thinkpad-W530\\Desktop\\源\\盛智强.jpg"));
            picdataLength = picfile.available();
            if (picdataLength < 0 || xmlByte.size() < 0) {
                return;
            }
            HCNetSDK.BYTE_ARRAY ptrpicByte = new HCNetSDK.BYTE_ARRAY(picdataLength);
            picfile.read(ptrpicByte.byValue);
            ptrpicByte.write();
            HCNetSDK.NET_DVR_SEND_PARAM_IN struSendParam = new HCNetSDK.NET_DVR_SEND_PARAM_IN();
            struSendParam.pSendData = ptrpicByte.getPointer();
            struSendParam.dwSendDataLen = picdataLength;
            struSendParam.pSendAppendData = xmlByte.getPointer();
            struSendParam.dwSendAppendDataLen = xmlBuffer.getBytes().length;
            if (struSendParam.pSendData == null || struSendParam.pSendAppendData == null ||
                    struSendParam.dwSendDataLen == 0 || struSendParam.dwSendAppendDataLen == 0) {
                return;
            }
            struSendParam.byPicType = 1;
            struSendParam.dwPicMangeNo = 0;
            struSendParam.write();

            int iRet = SdkInit.hCNetSDK.NET_DVR_UploadSend(m_lUploadHandle, struSendParam.getPointer(), null);
            Thread.sleep(1000);
            if (iRet == -1) {
                LOGGER.info("错误" + SdkInit.hCNetSDK.NET_DVR_GetLastError());
            }
            int a;
            while (true) {
                int uploadState = this.getUploadState(m_lUploadHandle);
                if (uploadState == 0) {
                    LOGGER.info("错误" + SdkInit.hCNetSDK.NET_DVR_GetLastError());
                }
                if (uploadState == 1) {
                    LOGGER.info("上传成功");
                    // NET_DVR_GetUploadResult(m_lUploadHandle, HCNetSDK.IMPORT_DATA_TO_FACELIB, 12);
                    break;
                } else if (uploadState == 2) {
                    LOGGER.info("正在上传");
                } else if (uploadState == 29) {
                    LOGGER.info("图片未识别到目标");
                    break;
                } else {
                    LOGGER.info("其他错误：" + uploadState);
                    SdkInit.hCNetSDK.NET_DVR_UploadClose(m_lUploadHandle);
                    break;
                }
            }
            try {
                picfile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            SdkInit.hCNetSDK.NET_DVR_UploadClose(m_lUploadHandle);
        }
    }

    public int getUploadState(int m_lUploadHandle) throws Exception {
        IntByReference pInt = new IntByReference(0);
        int m_UploadStatus = SdkInit.hCNetSDK.NET_DVR_GetUploadState(m_lUploadHandle, pInt);
        if (m_UploadStatus == -1) {
            System.out.println("NET_DVR_GetUploadState fail,error=" + SdkInit.hCNetSDK.NET_DVR_GetLastError());
        } else if (m_UploadStatus == 2) {
            System.out.println("is uploading!!!!  progress = " + pInt.getValue());
        } else if (m_UploadStatus == 1) {
            System.out.println("progress = " + pInt.getValue());
            System.out.println("Uploading Succ!!!!!");
        } else {
            LOGGER.info("错误" + SdkInit.hCNetSDK.NET_DVR_GetLastError());
        }
        Thread.sleep(1000);
        return m_UploadStatus;
    }


}
