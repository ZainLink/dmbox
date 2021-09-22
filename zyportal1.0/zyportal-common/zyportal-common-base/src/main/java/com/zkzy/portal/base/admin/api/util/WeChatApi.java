package com.zkzy.portal.base.admin.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sun.star.ucb.ResultSetException;
import com.zkzy.portal.common.model.WeiXinTemplateMsg;
import com.zkzy.portal.common.utils.Http;
import com.zkzy.portal.base.admin.api.exception.ExecuteResultException;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by WH on 2018/11/19.
 */
public class WeChatApi {

    private static Logger logger = LoggerFactory.getLogger(WeChatApi.class);

    // 获取accessToken
    public static String weiXinAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 发送模板消息
    public static String weiXinTemplateUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    //获取unionID 机制下的所有用户信息
    public static String weiXinUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //批量拉取用户信息
    public static String batchWeiXinUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
    //拉取用户列表
    public static String getUserList = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

    /**
     * 获取access_token 公众号调用各接口时都需使用access_token
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public static Map<String, Object> getAccessToken(String appId, String appSecret) throws ExecuteResultException {

        Http http = new Http();
        Map<String, Object> result = new HashMap<String, Object>();

        String requestUrl = weiXinAccessTokenUrl.replace("APPID", appId).replace("APPSECRET", appSecret);

        JSONObject jsonObject = JSONObject.parseObject(http.getRes(requestUrl));

        String access_token = jsonObject.getString("access_token");

        if (access_token == null) {
            throw new ExecuteResultException(jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"), "");
        } else {
            result.put("access_token", access_token);
            result.put("expires_in", jsonObject.getInteger("expires_in"));
        }
        return result;
    }

    /**
     * 获取用户基本信息(UnionID机制)
     *
     * @param access_token
     * @param openId
     * @return
     */
    public static Map<String, String> getUnionId(String access_token, String openId) throws ExecuteResultException {

        Http http = new Http();

        Map<String, String> userInfo = new HashMap<String, String>();

        logger.info("access_token:" + access_token + "  openid:" + openId);

        String requestUrl = weiXinUserInfo.replace("ACCESS_TOKEN", access_token).replace("OPENID", openId);

        JSONObject jsonObject = JSONObject.parseObject(http.getRes(requestUrl));

        try {
            int subscribe = jsonObject.getInteger("subscribe");

            logger.info("subscribe :" + subscribe);

            userInfo.put("subscribe", String.valueOf(subscribe));

            if (subscribe == 1) {

                userInfo.put("openid", jsonObject.getString("openid"));

                userInfo.put("nickname", jsonObject.getString("nickname"));

                userInfo.put("sex", jsonObject.getString("sex"));

                userInfo.put("country", jsonObject.getString("country"));

                userInfo.put("city", jsonObject.getString("city"));

                userInfo.put("province", jsonObject.getString("province"));

                userInfo.put("language", jsonObject.getString("language"));

                userInfo.put("headimgurl", jsonObject.getString("headimgurl"));

                userInfo.put("subscribe_time", jsonObject.getString("subscribe_time"));

                userInfo.put("unionid", jsonObject.getString("unionid"));
                //公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
                userInfo.put("remark", jsonObject.getString("remark"));
                //用户所在的分组ID（兼容旧的用户分组接口）
                userInfo.put("groupid", jsonObject.getString("groupid"));
                //用户被打上的标签ID列表
                userInfo.put("tagid_list", jsonObject.getString("tagid_list"));
                //返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
                userInfo.put("subscribe_scene", jsonObject.getString("subscribe_scene"));
            }
        } catch (Exception e) {
            // TODO: handle exception
            //{"errcode":40013,"errmsg":"invalid appid"}
            System.out.println(">>>>>>>>getUnionId:" + e.toString());
            throw new ExecuteResultException(jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"), e.getMessage());
        }

        return userInfo;
    }

    /**
     * 发送模板消息
     *
     * @param accessToken accesstoken
     * @param touser      发给谁的openId
     * @param templateId  模板Id
     * @param url         跳转页面的url，没有不传
     * @param appid       跳转小程序的appid，没有不传
     * @param pagepath    跳转小程序的页面，没有不传
     * @param message     要发送的内容体
     * @return
     */
    public static JSONObject sendTemplateMessage(String accessToken,
                                                 String touser, String templateId, String url, String appid,
                                                 String pagepath, TreeMap<String, TreeMap<String, String>> message) {

        // "miniprogram":{
        // "appid":"xiaochengxuappid12345",
        // "pagepath":"index?foo=bar"
        // },
        WeiXinTemplateMsg weiXinTemplateMsg = new WeiXinTemplateMsg();
        // 发给谁
        weiXinTemplateMsg.setTouser(touser);
        // 模板id
        weiXinTemplateMsg.setTemplate_id(templateId);

        if (url != null) {
            weiXinTemplateMsg.setUrl(url);
        }
        if (appid != null) {
            TreeMap<String, String> miniprogram = new TreeMap<>();
            miniprogram.put("appid", appid);
            miniprogram.put("pagepath", pagepath);
            weiXinTemplateMsg.setMiniprogram(miniprogram);
        }
        weiXinTemplateMsg.setData(message);

        String requestUrl = weiXinTemplateUrl.replace("ACCESS_TOKEN", accessToken);

        Http http = new Http();

        JSONObject jsonObject = JSONObject.parseObject(http.postRes(requestUrl, (JSONObject) JSON.toJSON(weiXinTemplateMsg)));

        return jsonObject;
    }

    public static Map<String, Object> getUserList(String access_token, String nextOpenId) throws ExecuteResultException {

        Http http = new Http();

        String requestUrl = getUserList.replace("ACCESS_TOKEN", access_token).replace("NEXT_OPENID", nextOpenId);
        JSONObject jsonObject = JSONObject.parseObject(http.getRes(requestUrl));

        Map<String, Object> result = new HashMap<>();

//        {"errcode":40013,"errmsg":"invalid appid"}
        try {
            long total = jsonObject.getLong("total");
            int count = jsonObject.getInteger("count");
            result.put("total", total);
            result.put("count", count);
            JSONObject data = jsonObject.getJSONObject("data");
            result.put("openid", data.getJSONArray("openid").toArray());
            String next_openid = "";
            if (count == 10000) {
                next_openid = data.getString("next_openid");
                result.put("next_openid", next_openid);
            }
        } catch (Exception e) {
            // TODO: handle exception

            int errcode = jsonObject.getInteger("errcode");
            String errmsg = jsonObject.getString("errmsg");
            throw new ExecuteResultException(errcode, errmsg, "同步微信用户列表失败");
        }

        return result;
    }

    public static List<Map<String, String>> batchGetUnionId(String access_token, String[] openids) throws ExecuteResultException {

        List<Map<String, String>> res = null;
        //?access_token=ACCESS_TOKEN
        String requestUrl = batchWeiXinUserInfo.replace("ACCESS_TOKEN", access_token);

        TreeMap<String, List<TreeMap<String, String>>> data = new TreeMap<>();

        List<TreeMap<String, String>> user_list = new ArrayList<>();

        for (int i = 0; i < openids.length; i++) {
            TreeMap<String, String> openid = new TreeMap<>();
            openid.put("openid", openids[i]);
            user_list.add(openid);
        }
        data.put("user_list", user_list);

        Http http = new Http();

        JSONObject jsonObject = JSONObject.parseObject(http.postRes(requestUrl, (JSONObject) JSON.toJSON(data)));

        try {
            JSONArray jsonArray =  jsonObject.getJSONArray("user_info_list");
            if( jsonArray.size() > 0 ){

                res = new ArrayList<>();

                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);

                    Map<String, String> userInfo = new HashMap<String, String>();

                    int subscribe = obj.getInteger("subscribe");
                    userInfo.put("subscribe", String.valueOf(subscribe));
                    userInfo.put("openid", obj.getString("openid"));

                    if(subscribe == 1){

                        userInfo.put("nickname", obj.getString("nickname"));

                        userInfo.put("sex", obj.getString("sex"));

                        userInfo.put("country", obj.getString("country"));

                        userInfo.put("city", obj.getString("city"));

                        userInfo.put("province", obj.getString("province"));

                        userInfo.put("headimgurl", obj.getString("headimgurl"));

                        userInfo.put("subscribe_time", obj.getString("subscribe_time"));

                        userInfo.put("unionid", obj.getString("unionid"));
                        //公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
                        userInfo.put("remark", obj.getString("remark"));
                        //用户所在的分组ID（兼容旧的用户分组接口）
                        userInfo.put("groupid", obj.getString("groupid"));
                        //用户被打上的标签ID列表
                        userInfo.put("tagid_list", obj.getString("tagid_list"));
                        //返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
                        userInfo.put("subscribe_scene", obj.getString("subscribe_scene"));

                    }

                    res.add(userInfo);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            //{"errcode":40013,"errmsg":"invalid appid"}
            throw new ExecuteResultException(jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"),"");
        }
        return res;
    }
}
