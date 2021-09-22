package com.zkzy.portal.base.admin.api.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zkzy.portal.common.utils.Http;

import java.util.ArrayList;

/**
 * Created by Thinkpad on 2019/11/11.
 */
public class WeChatTagsUtil {

    private static Http http = new Http();

    //创建标签
    public static String addTagsUrl = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=";

    //公众号已创建的标签
    public static String allTagsUrl = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=";

    //编辑标签
    public static String updateTags = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=";

    //删除标签
    public static String delTags = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=";

    //获取标签下粉丝列表
    public static String TagsFans = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=";

    //批量打标签
    public static String TagsMore = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=";

    //批量取消
    public static String TagsCancel = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=";

    //获取用户身上的标签列表
    public static String UserTags = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=";

    //测试用token
    private static String token = "27_GVQxOPGSs-tNl5xpkhTBB1r-I-IVTqh8gxLLhUNMKcWPu9mNZk1WiyOppqKXfziKwTkPgHZT6kViAbjZF3cJiio4JGVffehxs0vpvIViwDe5AoOOjV_nK4I8TlQXRLiAGAJDX";


    /**
     * 添加标签
     *
     * @param access_token
     * @param tagName      标签名
     * @return json
     */
    public static JSONObject addTag(String access_token, String tagName) throws Exception {
        String url = addTagsUrl + access_token;
        JSONObject j = new JSONObject();
        JSONObject group = new JSONObject();
        j.put("name", tagName);
        group.put("tag", j);
        JSONObject result = WeChatUtil.post(group, url);
        return result;
    }

    /**
     * 获取所有创建的标签
     *
     * @param access_token
     * @return
     * @throws Exception
     */
    public static JSONObject getAllTags(String access_token) throws Exception {
        String url = allTagsUrl + access_token;
        JSONObject result = WeChatUtil.get(url);
        return result;
    }

    /**
     * 编辑标签
     *
     * @param access_token
     * @param tagId        标签id
     * @param tagName      标签名
     * @return
     * @throws Exception
     */
    public static JSONObject updateTag(String access_token, String tagId, String tagName) throws Exception {
        String url = updateTags + access_token;
        JSONObject j = new JSONObject();
        JSONObject group = new JSONObject();
        j.put("id", tagId);
        j.put("name", tagName);
        group.put("tag", j);
        JSONObject result = WeChatUtil.post(group, url);
        return result;
    }


    /**
     * 删除标签
     *
     * @param access_token
     * @param tagId        标签id
     * @return
     * @throws Exception
     */
    public static JSONObject deleteTag(String access_token, String tagId) throws Exception {
        String url = delTags + access_token;
        JSONObject j = new JSONObject();
        JSONObject group = new JSONObject();
        j.put("id", tagId);
        group.put("tag", j);
        JSONObject result = WeChatUtil.post(group, url);
        return result;
    }


    /**
     * 获取标签下粉丝列表
     *
     * @param access_token
     * @param tagId        标签id
     * @param nextOpenid   第一个拉取的OPENID，不填默认从头开始拉取
     * @return
     * @throws Exception
     */
    public static JSONObject getFansList(String access_token, String tagId, String nextOpenid) throws Exception {
        String url = TagsFans + access_token;
        JSONObject group = new JSONObject();
        group.put("tagid", tagId);
        group.put("next_openid", nextOpenid);
        JSONObject result = WeChatUtil.post(group, url);
        return result;
    }

    /**
     * @param access_token
     * @param tagId
     * @param list
     * @return
     * @throws Exception
     */
    public static JSONObject tagAllFans(String access_token, String tagId, ArrayList<String> list) throws Exception {
        String url = TagsMore + access_token;
        JSONObject group = new JSONObject();
        group.put("openid_list", list);
        group.put("tagid", tagId);
        JSONObject result = WeChatUtil.post(group, url);
        return result;
    }


    /**
     * 批量为用户取消标签
     *
     * @param access_token
     * @param tagId
     * @param list
     * @return
     * @throws Exception
     */
    public static JSONObject tagsCancel(String access_token, String tagId, ArrayList<String> list) throws Exception {
        String url = TagsCancel + access_token;
        JSONObject group = new JSONObject();
        group.put("openid_list", list);
        group.put("tagid", tagId);
        JSONObject result = WeChatUtil.post(group, url);
        return result;
    }


    /**
     * 查询用户标签
     *
     * @param access_token
     * @param openid
     * @return
     * @throws Exception
     */
    public static JSONObject fanTags(String access_token, String openid) throws Exception {
        String url = UserTags + access_token;
        JSONObject group = new JSONObject();
        group.put("openid", openid);
        JSONObject result = WeChatUtil.post(group, url);
        return result;
    }

    public static void main(String[] args) {

        try {
//            JSONObject result = WeChatTagsUtil.addTag(token, "测试1113");
            // JSONObject result=WeChatTagsUtil.updateTag(token, "102","修改测试");
            //JSONObject result = WeChatTagsUtil.getAllTags(token);
            // JSONObject result=WeChatTagsUtil.deleteTag(token,"103");


 /*           ArrayList<String> list = new ArrayList<String>();
            list.add("ovNFa6AeVbN2tOPDjHyvS9B5ZVUA");
            list.add("ovNFa6FDiXeFmAPDaR-xfaXhxq_w");
            list.add("ovNFa6OFwBzPfuDxyKNTpkIxRTrI");
            JSONObject result = WeChatTagsUtil.tagAllFans2(token, "105", list);*/
//            JSONObject result = WeChatTagsUtil.TagsCancel(token, "102", arrys);
/*            JSONObject result = WeChatTagsUtil.tagAllFans(token, "102", arrys);*
 */
//            JSONObject result =WeChatTagsUtil.getFansList(token,"102","");
//            JSONObject result =WeChatTagsUtil.fanTags(token,"ovNFa6ALeU7pONSwTv9cB5E75IkA");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
