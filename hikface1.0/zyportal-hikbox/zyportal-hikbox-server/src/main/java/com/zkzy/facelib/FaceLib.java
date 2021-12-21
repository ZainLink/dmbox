package com.zkzy.facelib;

import com.zkzy.init.SdkInit;
import com.zkzy.sdk.HCNetSDK;
import com.zkzy.task.DmTask;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

import java.util.Iterator;

/**
 * Created by Thinkpad-W530 on 2021/12/6.
 */
public class FaceLib {

    private static final Logger LOGGER = LoggerFactory.getLogger(FaceLib.class);
    /**
     * 创建人脸库（如果已创建 就不会再次创建）
     * @param faceid
     * @return
     */

    public static boolean CreateFDLib(int faceid,int lUserID) {
        HCNetSDK.NET_DVR_XML_CONFIG_INPUT struInput = new HCNetSDK.NET_DVR_XML_CONFIG_INPUT();
        struInput.dwSize = struInput.size();
        String str = "POST /ISAPI/Intelligent/FDLib";
        HCNetSDK.BYTE_ARRAY ptrUrl = new HCNetSDK.BYTE_ARRAY(HCNetSDK.BYTE_ARRAY_LEN);
        System.arraycopy(str.getBytes(), 0, ptrUrl.byValue, 0, str.length());
        ptrUrl.write();
        struInput.lpRequestUrl = ptrUrl.getPointer();
        struInput.dwRequestUrlLen = str.getBytes().length;
        String strInBuffer = "<CreateFDLibList version=\"2.0\" xmlns=\"http://www.isapi.org/ver20/XMLSchema\">" +
                "<CreateFDLib>" +
                "<id>1</id>" +
                "<name>白名单</name>" +
                "<thresholdValue>50</thresholdValue>" +
                "<customInfo>站点白名单</customInfo>" +
                "<customFaceLibID>FDB3D1B24D3D470E9F72AEF99E9E839C</customFaceLibID>" +
                "</CreateFDLib>" +
                "</CreateFDLibList>";
        HCNetSDK.BYTE_ARRAY ptrByte = new HCNetSDK.BYTE_ARRAY(10 * HCNetSDK.BYTE_ARRAY_LEN);
        ptrByte.byValue = strInBuffer.getBytes();
        ptrByte.write();
        struInput.lpInBuffer = ptrByte.getPointer();
        struInput.dwInBufferSize = strInBuffer.getBytes().length;
        struInput.write();
        HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT struOutput = new HCNetSDK.NET_DVR_XML_CONFIG_OUTPUT();
        struOutput.dwSize = struOutput.size();
        HCNetSDK.BYTE_ARRAY ptrOutByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_DATA_LEN);
        struOutput.lpOutBuffer = ptrOutByte.getPointer();
        struOutput.dwOutBufferSize = HCNetSDK.ISAPI_DATA_LEN;
        HCNetSDK.BYTE_ARRAY ptrStatusByte = new HCNetSDK.BYTE_ARRAY(HCNetSDK.ISAPI_STATUS_LEN);
        struOutput.lpStatusBuffer = ptrStatusByte.getPointer();
        struOutput.dwStatusSize = HCNetSDK.ISAPI_STATUS_LEN;
        struOutput.write();
        if (SdkInit.hCNetSDK.NET_DVR_STDXMLConfig(lUserID, struInput, struOutput)) {
            String xmlStr = struOutput.lpOutBuffer.getString(0);
            // dom4j解析xml
            try {
                Document document;
                document = DocumentHelper.parseText(xmlStr);
                Element FDLibInfoList = document.getRootElement();
                // 同时迭代当前节点下面的所有子节点
                Iterator<Element> iterator = FDLibInfoList.elementIterator();
                Element FDLibInfo = iterator.next();
                Iterator<Element> iterator2 = FDLibInfo.elementIterator();
                while (iterator2.hasNext()) {
                    Element e = iterator2.next();
                    if (e.getName().equals("FDID")) {
                        String id = e.getText();
                        if("createFailed".equals(id)){
                            LOGGER.info("白名单已创建,不需要重复创建");
                        }else {
                            LOGGER.info("白名单创建成功,FDID为:"+id);
                        }
                    }
                }
            } catch (DocumentException e1) {
                e1.printStackTrace();
                return false;
            }
            return true;
            // 获取根节点元素对象
        } else {
            int code = SdkInit.hCNetSDK.NET_DVR_GetLastError();
            System.out.println("code" + code);
            return false;
        }
    }
}
