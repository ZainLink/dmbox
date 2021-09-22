package com.zkzy.portal.base.admin.api.util;


import java.io.UnsupportedEncodingException;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import com.zkzy.portal.common.utils.Http;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;
import net.sf.json.JSONObject;


/**
 * Created by Thinkpad on 2018/11/19.
 */


public class MiniprogramUtil {
    private static Http http = new Http();

    // 加密信息解密
    public static JSONObject getInfoByEncryptedData(String appid,String appSecret ,String code, String encryptedData, String iv){
        String sessionkey = getSessionKey(appid,appSecret,code);
        JSONObject userInfo =getUserInfo(encryptedData, sessionkey, iv);
        return userInfo;
    }
    //获取sessionkey
    public static String getSessionKey(String appid, String appSecret, String code){
        //拼接地址
        String requestUrl="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appSecret+"&js_code="+code+"&grant_type=authorization_code";
        // 获取网页授权凭证
        String sessionKey="";
        JSONObject jsonObject =MiniprogramUtil.get(requestUrl);
        if (null != jsonObject) {
            try {
                sessionKey=jsonObject.getString("session_key");
            } catch (Exception e) {
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
            }

        }
        return sessionKey;
    }

    //解密获得用户信息
    public static JSONObject getUserInfo(String encryptedData,String sessionkey,String iv){
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionkey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);

        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSONObject.fromObject(result);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidParameterSpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 向指定接口（url）发送post请求，请求参数为json
    public static JSONObject post(com.alibaba.fastjson.JSONObject json, String url) {
        String result = http.postRes(url,json);
        return  JSONObject.fromObject(result);
    }

    //向指定接口（url）发送get请求
    public static JSONObject get(String url) {
        String result = http.getRes(url);
        return JSONObject.fromObject(result);
    }


}
