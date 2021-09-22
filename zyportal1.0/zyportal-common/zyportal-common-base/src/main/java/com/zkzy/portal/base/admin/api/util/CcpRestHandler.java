package com.zkzy.portal.base.admin.api.util;

import com.alibaba.fastjson.JSONObject;
import com.zkzy.portal.common.utils.DateHelper;
import com.zkzy.portal.common.utils.LogBack;
import com.zkzy.portal.common.utils.SSLClient;
import com.zkzy.portal.base.admin.api.service.SocialService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public class CcpRestHandler {


    @Autowired
    private SocialService socialService;

    private static final String queryCallState = "ivr/call";
    private static final String callResult = "CallResult";
    private static final String mediaFileUpload = "Calls/MediaFileUpload";

    public static String SERVER_IP;
    public static String SERVER_PORT;
    public static String SOFTVERSION;
    public static String BASEURL;

    public static String ACCOUNT_SID;
    public static String AUTH_TOKEN;
    public static String APP_ID;
    public static String APP_TOKEN;


    private static String SUBACCOUNT_SID;
    private static String SUBACCOUNT_Token;

    private String Accept = "application/json";
    private String Content_Type = "application/json;charset=utf-8";
    private int Content_Length = 256;


    MediaType mediaType = MediaType.parse(Content_Type);




    public void initImServer(String serverIP, String serverPort, String softversion, String baseurl, String accountSid,
                          String authToken, String appId, String appToken) {
        if (isEmpty(serverIP) || isEmpty(serverPort) || isEmpty(softversion) || isEmpty(baseurl) || isEmpty(accountSid) ||
                isEmpty(authToken) || isEmpty(appId) || isEmpty(appToken)) {
            LogBack.printByLevel("init CcpRestHandler 获取参数错误，参数使用默认参数", "warning");
            throw new IllegalArgumentException("init CcpRestHandler 获取参数错误");
        } else {
            this.SERVER_IP = serverIP;
            this.SERVER_PORT = serverPort;
            this.SOFTVERSION = softversion;
            this.BASEURL = baseurl;
            this.ACCOUNT_SID = accountSid;
            this.AUTH_TOKEN = authToken;
            this.APP_ID = appId;
            this.APP_TOKEN = appToken;
        }
    }




    /**
     * 创建用户
     *
     * @param friendlyName
     * @return
     */
    public String createSubAccount(String friendlyName) {
        OkHttpClient okHttpClient = SSLClient.getUnsafeOkHttpClient();
        Request.Builder request = null;

        try {
            request = getHttpRequestBase("SubAccounts", AccountType.Accounts, true);
        } catch (Exception e) {
            LogBack.printByLevel("getHttpRequestBase:" + e.getMessage(), "warning");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId", APP_ID);
        jsonObject.put("friendlyName", friendlyName);
        String body = jsonObject.toString();
//        String body = "<?xml version='1.0' encoding='utf-8'?><SubAccount>"
//                + "<appId>" + APP_ID + "</appId>"
//                + "<friendlyName>" + friendlyName + "</friendlyName>" + "</SubAccount>";
        RequestBody requestBody = RequestBody.create(mediaType, body);
        Request r = request.post(requestBody).build();

        return okHttpClientExecute(okHttpClient, r);
    }

    /**
     * 关闭子账号
     *
     * @param subAccountSid
     * @return
     */
    public String closeSubAccount(String subAccountSid) {
        OkHttpClient okHttpClient = SSLClient.getUnsafeOkHttpClient();
        Request.Builder request = null;

        try {
            request = getHttpRequestBase("CloseSubAccount", AccountType.Accounts, true);
        } catch (Exception e) {
            LogBack.printByLevel("getHttpRequestBase:" + e.getMessage(), "warning");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("subAccountSid", subAccountSid);
        String body = jsonObject.toString();
        RequestBody requestBody = RequestBody.create(mediaType, body);
        Request r = request.post(requestBody).build();
        return okHttpClientExecute(okHttpClient, r);
    }

    //https://app.cloopen.com:8883/2013-12-26/Accounts/{accountSid}/IM/PushMsg?sig={SigParameter}
    public String subAccoutCheck(String subAccountId) {
//        try {
//            Request.Builder  request = getHttpRequestBase("",AccountType.SubAccounts);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }

        return null;
    }


    private boolean isEmpty(String str) {
        return (("".equals(str)) || (str == null));
    }


    public enum AccountType {
        Accounts, SubAccounts;
    }

    /**
     * 2. SigParameter是REST API 验证参数
     * 主帐号鉴权：
     * • URL后必须带有sig参数，例如：sig=AAABBBCCCDDDEEEFFFGGG。
     * • 使用MD5加密（主帐号Id + 主帐号授权令牌 +时间戳）。其中主帐号Id和主帐号授权令牌分别对应管理控制台中的ACCOUNT SID和AUTH TOKEN。
     * • 时间戳是当前系统时间，格式"yyyyMMddHHmmss"。时间戳有效时间为24小时，如：20140416142030
     * • SigParameter参数需要大写
     * 子帐号鉴权：
     * • URL后必须带有sig参数，例如：sig=AAABBBCCCDDDEEEFFFGGG。
     * • 使用MD5加密（子帐号Id + 子帐号授权令牌 +时间戳）。其中子帐号Id和子帐号授权令牌可通过创建子帐号接口得到。
     * • 时间戳是当前系统时间，格式"yyyyMMddHHmmss"。时间戳有效时间为24小时，如：20140416142030
     * • SigParameter参数需要大写
     *
     * @param action
     * @return
     */
    private Request.Builder getHttpRequestBase(String action, AccountType mAccountType, boolean needSIG) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String timestamp = DateHelper.dateToStr(new Date(), DateHelper.DATE_TIME_NO_SLASH);
        EncryptUtil eu = new EncryptUtil();
        String sig = "";
        String acountName = "";
        String acountType = "";
        if (mAccountType == AccountType.Accounts) {
            acountName = ACCOUNT_SID;
            sig = ACCOUNT_SID + AUTH_TOKEN + timestamp;
            acountType = "Accounts";
        } else {
            acountName = SUBACCOUNT_SID;
            sig = SUBACCOUNT_SID + SUBACCOUNT_Token + timestamp;
            acountType = "SubAccounts";
        }
        String signature = eu.md5Digest(sig);

//        String url = getBaseUrl().append("/" + acountType + "/")
//                .append(acountName).append("/" + action + "?sig=")
//                .append(signature).toString();
        String url = getUrl(acountType, acountName, action, needSIG, signature);
        HttpUrl getUrl = HttpUrl.parse(url).newBuilder().build();
        System.out.println(url);
        String src = acountName + ":" + timestamp;
        String auth = eu.base64Encoder(src);
        Map<String, String> map = new HashMap<>();
        map.put("Accept", Accept);
        map.put("Content-Type", Content_Type);
        map.put("Content-Length", "" + Content_Length);
        map.put("Authorization", auth);
        Headers headers = Headers.of(map);
        Request.Builder request = new Request.Builder().url(getUrl).headers(headers);
        return request;

    }

    private String okHttpClientExecute(OkHttpClient okHttpClient, Request request) {
        String resultJSON = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            resultJSON = response.body().string();
            System.out.println(resultJSON);
        } catch (IOException e) {
            LogBack.printByLevel("响应转换异常", "warning");
        }
        return resultJSON;
    }

    private String getUrl(String acountType, String acountSid, String action, boolean needSIG, String signature) {
        String url = null;
        if (needSIG) {
            url = getBaseUrl().append("/" + acountType + "/")
                    .append(acountSid).append("/" + action + "?sig=")
                    .append(signature).toString();
        } else {
            url = getBaseUrl().append("/" + acountType + "/")
                    .append(acountSid).append("/" + action).toString();
        }
        return url;
    }


    private Headers getHeader(Map<String, String> map) {
        Headers headers = Headers.of(map);
        return headers;
    }

    /**
     * 3. Authorization是包头验证信息
     • 使用Base64编码（账户Id + 冒号 + 时间戳）其中账户Id根据url的验证级别对应主账户或子账户
     • 冒号为英文冒号
     • 时间戳是当前系统时间，格式"yyyyMMddHHmmss"，需与SigParameter中时间戳相同。
     * @return
     */
    /**
     * Accept:application/xml;
     * Content-Type:application/xml;charset=utf-8;
     * Content-Length:256;
     * Authorization:XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
     */

    private StringBuffer getBaseUrl() {
        StringBuffer sb = new StringBuffer("https://");
        sb.append(SERVER_IP).append(":").append(SERVER_PORT);
        sb.append("/" + SOFTVERSION);
        return sb;
    }



}
