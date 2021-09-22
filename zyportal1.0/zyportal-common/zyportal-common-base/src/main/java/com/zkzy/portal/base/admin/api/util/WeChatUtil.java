package com.zkzy.portal.base.admin.api.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.zkzy.portal.common.utils.DateHelper;
import com.zkzy.portal.common.utils.Http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkzy.portal.base.admin.api.entity.WeChatMessage;
import com.zkzy.portal.base.admin.api.entity.WeChatMessageList;
import com.zkzy.portal.base.admin.api.entity.WeChatTag;
import com.zkzy.portal.base.admin.api.entity.WeChatUser;
import it.sauronsoftware.jave.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zkzy.portal.common.utils.RandomHelper.uuid;

public class WeChatUtil {

	private static String access_token = null;
	private static Http http = new Http();


	// 获取access_token
	public static void getAccess_token(String appid,String appsecret) {
		if(access_token == null){
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + appsecret;
			JSONObject result = get(url);
			System.out.println("获取access_token返回结果："+result);
			access_token = result.getString("access_token");
			if(access_token == null){
				System.out.println(result);
				throw new RuntimeException("获取access_token出错");
			}
		}
	}

	public static boolean isAccessTokenError(int errcode){
		if(errcode == 40014 || errcode == 40001 || errcode == 42001){
			access_token = null;
			return true;
		}else{
			return false;
		}
	}

	//群发消息
	//1.根据标签群发单条消息
	public static boolean sendMessageByTag(WeChatMessage weChatMessage,String appid,String appsecret) {
		try{
			getAccess_token(appid,appsecret);
			String msgType = weChatMessage.getMsgtype();
			String tagid = weChatMessage.getTagid();
			JSONObject message = new JSONObject();

			//按消息类型生成message消息
			switch (msgType) {
				case "mpnews":
					String picurl = weChatMessage.getFileUrl();
					String title = weChatMessage.getTitle();
					String author = weChatMessage.getAuthor();
					String desc = weChatMessage.getDescription();
					String content = weChatMessage.getContent();
					String media_id = WeChatUtil.getMpNewsId(picurl, author, title, desc, content);
					message.put("media_id", media_id);
					break;
				case "text":
					String text = weChatMessage.getContent();
					message.put("content", text);
					break;
				case "image":
					String imageurl = weChatMessage.getFileUrl();
					media_id = WeChatUtil.getMedia_id(imageurl, msgType);
					message.put("media_id", media_id);
					break;
				case "mpvideo":
					title = weChatMessage.getTitle();
					desc = weChatMessage.getDescription();
					String videourl = weChatMessage.getFileUrl();
					media_id = WeChatUtil.getVedioId(videourl,title,desc);
					message.put("media_id",media_id);
					break;
				case "voice":
					String voiceurl = weChatMessage.getFileUrl();
					media_id = WeChatUtil.getMedia_id(voiceurl, msgType);
					message.put("media_id", media_id);
					break;
			}
			String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="+ access_token;
			JSONObject filter = new JSONObject();
			JSONObject json = new JSONObject();
			if(tagid!=null && tagid!=""){
				filter.put("is_to_all", false);
				filter.put("tag_id", tagid);
			}else{
				filter.put("is_to_all", true);
			}
			json.put("filter", filter);
			json.put(msgType, message);
			json.put("msgtype", msgType);
			if("mpnews".equals(msgType)){
				// 判定为转载是是否继续群发 1为继续群发 0停止群发
				json.put("send_ignore_reprint", 1);
			}
			JSONObject result = post(json, url);
			int errcode = result.getIntValue("errcode");
			if(isAccessTokenError(errcode)){
				return sendMessageByTag(weChatMessage,appid,appsecret);
			}
			System.out.println("根据标签群发返回结果："+result);
			if (errcode == 0) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	//2.根据标签群发多条消息
	public static boolean sendMessageByTagList(WeChatMessageList weChatMessages, String appid, String appsecret) {
		try{
			getAccess_token(appid,appsecret);
			String msgType = weChatMessages.getMsgType();
			String tagid = weChatMessages.getWeChatMessages().get(0).getTagid();
			JSONObject message = new JSONObject();

			//按消息类型生成message消息
			String media_id = WeChatUtil.getMpNewsListId(weChatMessages);
			message.put("media_id", media_id);
			String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="+ access_token;
			JSONObject filter = new JSONObject();
			JSONObject json = new JSONObject();
			if(tagid == null || tagid == ""){
				filter.put("is_to_all", true);
			}else{
				filter.put("is_to_all", false);
				filter.put("tag_id", tagid);
			}
			json.put("filter", filter);
			json.put(msgType, message);
			json.put("msgtype", msgType);
			if("mpnews".equals(msgType)){
				// 判定为转载是是否继续群发 1为继续群发 0停止群发
				json.put("send_ignore_reprint", 1);
			}
			JSONObject result = post(json, url);
			int errcode = result.getIntValue("errcode");
			if(isAccessTokenError(errcode)){
				return sendMessageByTagList(weChatMessages,appid,appsecret);
			}
			System.out.println("根据标签群发返回结果："+result);
			if (errcode == 0) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	//3.发送给所有人
	public static boolean sendMessageToAll(WeChatMessage weChatMessage,String appid,String appsecret) {
		try{
			getAccess_token(appid,appsecret);
			String msgType = weChatMessage.getMsgtype();
			JSONObject message = new JSONObject();

			//按消息类型生成message消息
			switch (msgType) {
				case "mpnews":
					String picurl = weChatMessage.getFileUrl();
					String title = weChatMessage.getTitle();
					String author = weChatMessage.getAuthor();
					String desc = weChatMessage.getDescription();
					String content = weChatMessage.getContent();
					String media_id = WeChatUtil.getMpNewsId(picurl, author, title, desc, content);
					message.put("media_id", media_id);
					break;
				case "text":
					String text = weChatMessage.getContent();
					message.put("content", text);
					break;
				case "image":
					String imageurl = weChatMessage.getFileUrl();
					media_id = WeChatUtil.getMedia_id(imageurl, msgType);
					message.put("media_id", media_id);
					break;
				case "mpvideo":
					title = weChatMessage.getTitle();
					desc = weChatMessage.getDescription();
					String videourl = weChatMessage.getFileUrl();
					media_id = WeChatUtil.getVedioId(videourl,title,desc);
					message.put("media_id",media_id);
					break;
				case "voice":
					String voiceurl = weChatMessage.getFileUrl();
					media_id = WeChatUtil.getMedia_id(voiceurl, msgType);
					message.put("media_id", media_id);
					break;
			}
			String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="
					+ access_token;
			JSONObject filter = new JSONObject();
			JSONObject json = new JSONObject();
			filter.put("is_to_all", true);
			json.put("filter", filter);
			json.put(msgType, message);
			json.put("msgtype", msgType);
			if("mpnews".equals(msgType)){
				// 判定为转载是是否继续群发 1为继续群发 0停止群发
				json.put("send_ignore_reprint", 1);
			}
			JSONObject result = post(json, url);
			int errcode = result.getIntValue("errcode");
			if(isAccessTokenError(errcode)){
				return sendMessageToAll(weChatMessage,appid,appsecret);
			}
			System.out.println("群发给所有人返回结果："+result);
			if (errcode == 0) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;

	}

	//客服消息
	public static boolean sendServiceMessage(WeChatMessage weChatMessage,String appid,String appsecret) {
		try{
			getAccess_token(appid,appsecret);
			String msgType = weChatMessage.getMsgtype();
			JSONObject message = new JSONObject();
//            if(type == 0){  //客服消息
			String openid = weChatMessage.getTouser();
			//按消息类型生成message消息
			switch (msgType){
				case "mpnews":
					String picurl = weChatMessage.getFileUrl();
					String title = weChatMessage.getTitle();
					String author = weChatMessage.getAuthor();
					String desc = weChatMessage.getDescription();
					String content = weChatMessage.getContent();
					String media_id = WeChatUtil.getMpNewsId(picurl, author, title, desc, content);
					message.put("media_id",media_id);
					break;
				case "text":
					content = weChatMessage.getContent();
					message.put("content",content);
					break;
				case "image":
					String imageurl = weChatMessage.getFileUrl();
					media_id = WeChatUtil.getMedia_id(imageurl,msgType);
					message.put("media_id",media_id);
					break;
				case "video":
					title = weChatMessage.getTitle();
					desc = weChatMessage.getDescription();
					String videourl = weChatMessage.getFileUrl();
					media_id = WeChatUtil.getMedia_id(videourl,msgType);
					message.put("title",title);
					message.put("description",desc);
					message.put("media_id",media_id);
					break;
				case "voice":
					String voiceurl = weChatMessage.getFileUrl();
					media_id = WeChatUtil.getMedia_id(voiceurl,msgType);
					message.put("media_id",media_id);
					break;
				case "music":
					title = weChatMessage.getTitle();
					desc = weChatMessage.getDescription();
					String musicurl = weChatMessage.getMusicurl();
					imageurl = weChatMessage.getFileUrl();
					String thumb_media_id = WeChatUtil.getMedia_id(imageurl,"image");

					message.put("title",title);
					message.put("description",desc);
					message.put("musicurl",musicurl);
					message.put("thumb_media_id",thumb_media_id);
					break;
			}
			String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + access_token;
			JSONObject json = new JSONObject();
			json.put("touser", openid);
			json.put("msgtype", msgType);
			json.put(msgType, message);
			JSONObject result = WeChatUtil.post(json, url);
			int errcode = result.getIntValue("errcode");
			if(isAccessTokenError(errcode)){
				return sendServiceMessage(weChatMessage,appid,appsecret);
			}
			System.out.println("客服消息发送返回结果："+result);
			if (errcode == 0) {
				return true;
			} else {
				return false;
			}
//            }else{
//                return null;
//            }
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	//获取保存消息内容（消息内容处理）
	public static WeChatMessage getSaveMessage(WeChatMessage weChatMessage,String prefix){
		//获取消息发送时间，取当前时间
		Date date = new Date();
		String datestr = DateHelper.formatDateTime(date);
		weChatMessage.setSendtime(datestr);
		if(weChatMessage !=null) {
			weChatMessage.setId(uuid());
			//数据处理操作（openids,tagid,msgtype）
			if (weChatMessage.getMsgtype() != null) {
				String msgtype = weChatMessage.getMsgtype();
				if ("text".equals(msgtype)) {
					weChatMessage.setMsgtype("文本消息");
				} else if ("image".equals(msgtype)) {
					weChatMessage.setMsgtype("图片消息");
				} else if ("video".equals(msgtype) || "mpvideo".equals(msgtype)) {
					weChatMessage.setMsgtype("视频消息");
				} else if ("voice".equals(msgtype)) {
					weChatMessage.setMsgtype("语音消息");
					String fileUrl = weChatMessage.getFileUrl();
					String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
					String amrPath = prefix + "/" + fileName;
					String mp3Path = amrPath.replace(".amr", ".mp3");
					WeChatUtil.changeToMp3(amrPath, mp3Path);
					//message.setFileUrl(fileUrl.replace(".amr",".mp3"));
				} else if ("mpnews".equals(msgtype)) {
					weChatMessage.setMsgtype("图文消息");
					//content处理
					String content = weChatMessage.getContent();
					byte[] contents = content.getBytes();
					weChatMessage.setContents(contents);
					weChatMessage.setContent(null);
				} else if ("music".equals(msgtype)) {
					weChatMessage.setMsgtype("音乐消息");
				} else {
					weChatMessage.setMsgtype("");
				}
			}
		}
		return weChatMessage;
	}

	//模板消息
	public static boolean sendTemplateMessage(String openid, String templateId, String data, String appid,String appsecret) {
		getAccess_token(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+access_token;
		data = data.replace("&lt;","<").replace("&gt;",">").replace("&quot;","'").replace("&amp;","&");
		JSONObject d = (JSONObject) JSON.parse(data);
		JSONObject json = new JSONObject();
		json.put("touser", openid);
		json.put("template_id",templateId);
		json.put("data", d);
		JSONObject result = WeChatUtil.post(json,url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)) {
			return sendTemplateMessage(openid, templateId, data, appid,appsecret);
		}
		System.out.println("模板消息发送返回结果："+result);
		if (errcode == 0) {
			return true;
		} else {
			return false;
		}
	}

	//添加标签
	public static WeChatTag addTag(String name, String weChatId,String appid,String appsecret){
		getAccess_token(appid, appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token="+access_token;
		JSONObject tag = new JSONObject();
		JSONObject json = new JSONObject();
		tag.put("name", name);
		json.put("tag", tag);
		JSONObject result = WeChatUtil.post(json, url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)) {
			return addTag(name, weChatId,appid,appsecret);
		}
		System.out.println("添加标签返回结果："+result);
		if(errcode==0){
//			WeChatTag t = new WeChatTag();
			WeChatTag t = jsonToTag(result.getJSONObject("tag"));
			t.setId(uuid());
//			t.setTagid(result.getJSONObject("tag").getIntValue("id"));
//			t.setTagname(result.getJSONObject("tag").getString("name"));
			t.setWechatid(weChatId);
			return t;
		}else{
			return null;
		}
	}

	//根据标签id修改标签
	public static boolean updateTag(int tagId,String name,String appid,String appsecret) {
		getAccess_token(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token="+access_token;
		JSONObject json = new JSONObject();
		JSONObject tag = new JSONObject();
		tag.put("id", tagId);
		tag.put("name", name);
		json.put("tag", tag);
		JSONObject result = WeChatUtil.post(json, url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)) {
			return updateTag(tagId,name,appid,appsecret);
		}
		System.out.println("修改标签返回结果："+result);
		if(errcode==0){
			return true;
		}else{
			return false;
		}
	}

	//根据标签id删除标签
	public static boolean deleteTag(int tagid, String appid,String appsecret) {
		getAccess_token(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token="+access_token;
		JSONObject json = new JSONObject();
		JSONObject tag = new JSONObject();
		tag.put("id", tagid);
		json.put("tag", tag);
		JSONObject result = WeChatUtil.post(json, url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)) {
			return deleteTag(tagid,appid,appsecret);
		}
		System.out.println("删除标签返回结果："+result);
		if(errcode==0){
			return true;
		}else{
			return false;
		}
	}

	//获取公众号标签信息（同步标签）
	public static JSONArray getTags(String appid,String appsecret){
		getAccess_token(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token="+access_token;
		JSONObject result = WeChatUtil.get(url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)) {
			return getTags(appid,appsecret);
		}
		System.out.println("获取公众号标签信息（同步标签）返回结果："+result);
		if(errcode == 0){
			JSONArray jsonarr = result.getJSONArray("tags");
			return jsonarr;
		}else{
			return null;
		}
	}

	////获取标签下用户openid列表
	public static String[] getOpenidsByTag(int tagId, String appid, String appsecret) {
		getAccess_token(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token="+access_token;
		JSONObject json = new JSONObject();
		json.put("tagid", tagId);
		JSONObject result = WeChatUtil.post(json, url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)) {
			return getOpenidsByTag(tagId,appid,appsecret);
		}
		System.out.println("获取标签下用户（openid）列表返回结果："+result);
		if(errcode==0){
			String openid = result.getJSONObject("data").getString("openid");
			openid = openid.substring(2, openid.length()-2);
			String[] openids = openid.split("\",\"");
			return openids;
		}else{
			return null;
		}
	}

	//获取用户openid列表
	public static String[] getOpenids(String appid,String appsecret){
		getAccess_token(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+access_token+"&next_openid=";
		JSONObject result = WeChatUtil.get(url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)) {
			return getOpenids(appid, appsecret);
		}
		System.out.println("获取公众号用户列表返回结果："+result);
		if(errcode == 0){
			String openid = result.getJSONObject("data").getString("openid");
			openid = openid.substring(2, openid.length()-2);
			String[] openids = openid.split("\",\"");
			return openids;
		}else{
			return null;
		}
	}

	//根据openid单个获取用户基本信息列表
	public static WeChatUser getUserInfoByOpenid(String openid, String appid,String appsecret) {
		getAccess_token(appid, appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid;
		JSONObject result = WeChatUtil.get(url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)){
			return getUserInfoByOpenid(openid,appid,appsecret);
		}
		System.out.println("根据openid获取用户基本信息返回结果："+result);
		if(errcode == 0){
//			WeChatUser weChatUser = JSON.parseObject(result.toJSONString(), new TypeReference<WeChatUser>() {});
//			String list = result.getString("tagid_list");
//			list = list.substring(1, list.length()-1);
//			weChatUser.setTagidList(list);
			WeChatUser weChatUser = jsonToUser(result);
			return weChatUser;
		}else{
			return null;
		}
	}

	//根据openid给用户设置备注名
	public static boolean setRemarkByOpenid(String openid, String remark, String appid,String appsecret) {
		getAccess_token(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token="+access_token;
		JSONObject json = new JSONObject();
		json.put("openid", openid);
		json.put("remark", remark);
		JSONObject result = WeChatUtil.post(json, url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)) {
			return setRemarkByOpenid(openid,remark,appid,appsecret);
		}
		System.out.println("根据openid给用户设置备注名返回结果："+result);
		if(errcode==0){
			return true;
		}else{
			return false;
		}
	}

	//给用户打标签
	public static boolean addTagToUser(int tagid, String openid, String appid,String appsecret){
		getAccess_token(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token="+access_token;
		JSONObject json = new JSONObject();
		String[] openids = {openid};
		json.put("openid_list", openids);
		json.put("tagid", tagid);
		JSONObject result = WeChatUtil.post(json, url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)) {
			return addTagToUser(tagid, openid, appid,appsecret);
		}
		System.out.println("给用户打标签返回结果："+result);
		if(errcode==0){
			return true;
		}else{
			return false;
		}
	}

	//删除用户标签
	public static boolean deleteTagFromUser(int tagid, String openid, String appid,String appsecret){
		getAccess_token(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token="+access_token;
		JSONObject json = new JSONObject();
		String[] openids = {openid};
		json.put("openid_list", openids);
		json.put("tagid", tagid);
		JSONObject result = WeChatUtil.post(json, url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)) {
			return deleteTagFromUser(tagid,openid,appid,appsecret);
		}
		System.out.println("删除用户标签："+result);
		if(errcode==0){
			return true;
		}else{
			return false;
		}
	}

	//获取公众号用户信息
	public static List<WeChatUser> getUserInfos(String appid,String appsecret){
		getAccess_token(appid, appsecret);
		String[] openids = getOpenids(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="+access_token;
		List<WeChatUser> weChatUsers = new ArrayList<WeChatUser>();
		int times = openids.length/100+1;
		for(int i = 0; i<times; i++){
			JSONObject json = new JSONObject();
			JSONArray userlist = new JSONArray();
			//获取用户列表次数（一次最多100个）
			int num = 100;
			if((i+1)*100>openids.length){
				num = openids.length-i*100;
			}
			for(int j = 0; j<num; j++){
				String openid = openids[i*100+j];
				JSONObject user = new JSONObject();
				user.put("openid",openid);
				user.put("lang","zh_CN");
				userlist.add(user);
			}
			json.put("user_list",userlist);
			JSONObject result = WeChatUtil.post(json, url);
			int errcode = result.getIntValue("roocode");
			if(isAccessTokenError(errcode)){
				return getUserInfos(appid,appsecret);
			}
			System.out.println("获取公众号用户信息返回结果："+result);
			if(errcode == 0){
				JSONArray users = result.getJSONArray("user_info_list");
				for(Object weChatUser : users){
//					WeChatUser user = JSON.parseObject(((JSONObject) weChatUser).toJSONString(), new TypeReference<WeChatUser>() {});
//					String list = ((JSONObject) weChatUser).getString("tagid_list");
//					list = list.substring(1, list.length()-1);
//					user.setTagidList(list);
					WeChatUser user = jsonToUser((JSONObject) weChatUser);
					weChatUsers.add(user);
				}
			}else{
				return null;
			}
		}
		return weChatUsers;
	}

	//获取用户标签id列表
	public static String[] queryTagsByOpenid(String openid,String appid,String appsecret){
		getAccess_token(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token="+access_token;
		JSONObject json = new JSONObject();
		json.put("openid", openid);
		JSONObject result = WeChatUtil.post(json, url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)) {
			return queryTagsByOpenid(openid,appid,appsecret);
		}
		System.out.println("获取用户标签id列表返回结果："+result);
		if(errcode == 0){
			String tagid_list = result.getString("tagid_list");
			tagid_list = tagid_list.substring(1, tagid_list.length()-1);
			String[] strtags = tagid_list.split(",");
			return strtags;
		}else{
			return null;
		}
	}

/*	//根据openid获取多个用户基本信息列表（接口最多一次性获取100条信息）
	public static JSONObject getUserInfosByOpenids(String[] openids, String access_token){
		JSONObject json = new JSONObject();
		String url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="+access_token;
		JSONObject[] arr = new JSONObject[openids.length];
		for (int i=0;i<openids.length;i++) {
			JSONObject jsonobj = new JSONObject();
			jsonobj.put("openid", openids[i]);
			arr[i] = jsonobj;
		}
		json.put("user_list", arr);
		JSONObject result = WeChatUtil.post(json, url);
		return result;
	}*/


	//用户基本信息json转换为user
	public static WeChatUser jsonToUser(JSONObject json){
		WeChatUser weChatUser = JSON.parseObject(json.toJSONString(), new TypeReference<WeChatUser>() {});
		String list = json.getString("tagid_list");
		list = list.substring(1, list.length()-1);
		weChatUser.setTagidList(list);
		return weChatUser;
	}

	//标签信息json转换为tag
	public static WeChatTag jsonToTag(JSONObject json){
//		WeChatTag weChatTag = JSON.parseObject(json.toJSONString(), new TypeReference<WeChatTag>() {});
		WeChatTag weChatTag = new WeChatTag();
		weChatTag.setTagid(json.getIntValue("id"));
		weChatTag.setTagname(json.getString("name"));
		return weChatTag;
	}

	// 向指定接口（url）发送post请求，请求参数为json
	public static JSONObject post(JSONObject json, String url) {
		String result = http.postRes(url,json);
		return (JSONObject) JSON.parse(result);
	}

	//向指定接口（url）发送get请求
	public static JSONObject get(String url) {
		String result = http.getRes(url);
		return (JSONObject) JSON.parse(result);
	}


	//1、群发消息工具接口
	//1.1上传多媒体文件获取id， 文件路径为网络路径
	public static String getMedia_id(String filePath, String type)
			throws Exception {
		//new一个URL对象
		URL url = new URL(filePath);
		//打开链接
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		//设置请求方式为"GET"
		conn.setRequestMethod("GET");
		//超时响应时间为5秒
		conn.setConnectTimeout(5 * 1000);
		//通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		DataInputStream in = new DataInputStream(inStream);

		String fileName = uuid()+filePath.substring(filePath.lastIndexOf("/")+1,filePath.length());
		/*if(!fileName.endsWith(".jpg")&&!fileName.endsWith(".png")&&!fileName.endsWith(".gif")){
			fileName = fileName + ".jpg";
		}*/
		URL urlObj = new URL(
				"http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="
						+ access_token + "&type="+type);
		JSONObject result = postFile(urlObj,in,fileName);
		System.out.println("上传多媒体文件获取媒体id返回结果："+result);
		return result.getString("media_id");
	}

	//1.2
	//1.2.1上传单条图文消息获取图文消息id（用于群发图文消息）
	public static String getMpNewsId(String picurl, String author, String title, String desc, String content){
		//处理内容转义字符
		content = content.replace("&lt;","<").replace("&gt;",">").replace("&quot;","'").replace("&amp;","&");
		//图文消息content处理
		Set<String> pics = getImgStr(content);
		try{
			for(String str : pics){
				String imgUrl = getImgUrl(str);
				content = content.replace(str,imgUrl);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//获取缩略图id
		String media_id = "";
		try {
			media_id = getMedia_id(picurl, "image");
		} catch (Exception e) {
			System.out.println("获取缩略图id失败");
			e.printStackTrace();
		}
		System.out.println(media_id);
		//获取图文消息id
		JSONObject article = new JSONObject();
		JSONObject json = new JSONObject();
		if (media_id != "" && media_id != null) {
			article.put("thumb_media_id", media_id);
		}
		article.put("author", author);
		article.put("title", title);
		article.put("digest", desc);
		article.put("content", content);
		article.put("content_source_url", "");
		article.put("show_cover_pic", 1);
		JSONObject[] articles = { article };
		json.put("articles", articles);
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="
				+ access_token;
		JSONObject result = WeChatUtil.post(json, url);
		System.out.println("上传图文消息获取消息id返回结果："+result);
		return result.getString("media_id");
	}

	//1.2.2上传图文消息list获取图文消息id（用于群发图文消息）
	public static String getMpNewsListId(WeChatMessageList weChatMessages){
		//图文消息content处理
		List<JSONObject> articles = new ArrayList<>();
		for (WeChatMessage weChatMessage:weChatMessages.getWeChatMessages()) {
			String content=weChatMessage.getContent();
			String author=weChatMessage.getAuthor();
			String title=weChatMessage.getTitle();
			String desc=weChatMessage.getDescription();
			Set<String> pics = getImgStr(content);
			try{
				for(String str : pics){
					String imgUrl = getImgUrl(str);
					content = content.replace(str,imgUrl);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			//获取缩略图id
			String media_id = "";
			try {
				media_id = getMedia_id(weChatMessage.getFileUrl(), "image");
			} catch (Exception e) {
				System.out.println("获取缩略图id失败");
				e.printStackTrace();
			}
			System.out.println(media_id);
			//获取图文消息id
			JSONObject article = new JSONObject();
			if (media_id != "" && media_id != null) {
				article.put("thumb_media_id", media_id);
			}
			article.put("author", author);
			article.put("title", title);
			article.put("digest", desc);
			article.put("content", content);
			article.put("content_source_url", "");
			article.put("show_cover_pic", 1);
			articles.add(article);
		}
		JSONObject json = new JSONObject();
		json.put("articles", articles);
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="
				+ access_token;
		JSONObject result = WeChatUtil.post(json, url);
		System.out.println("上传图文消息获取消息id返回结果："+result);
		return result.getString("media_id");
	}


	//1.3获取视频信息的media_id
	public static String getVedioId(String filePath,String title,String description){
		String media_id = null;
		try {
			media_id = getMedia_id(filePath,"video");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token="+access_token;
		JSONObject json = new JSONObject();
		json.put("media_id", media_id);
		json.put("title", title);
		json.put("description", description);
		JSONObject result = WeChatUtil.post(json, url);
		System.out.println("获取视频信息媒体id返回结果："+result);
		return result.getString("media_id");
	}

	//amr文件转换为mp3
	public static void changeToMp3(String sourcePath, String targetPath) {
		File source = new File(sourcePath);
		File target = new File(targetPath);
		AudioAttributes audio = new AudioAttributes();
		Encoder encoder = new Encoder();

		audio.setCodec("libmp3lame");
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);

		try {
			encoder.encode(source, target, attrs);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InputFormatException e) {
			e.printStackTrace();
		} catch (EncoderException e) {
			e.printStackTrace();
		}
	}

	//网络图片地址转换为微信地址
	public static String getImgUrl(String src) throws Exception {
		// String access_token = WeChatUtil.getAccess_token(appid, secret);
		//new一个URL对象
		URL url = new URL(src);
		//打开链接
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		//设置请求方式为"GET"
		conn.setRequestMethod("GET");
		//超时响应时间为5秒
		conn.setConnectTimeout(5 * 1000);
		//通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		DataInputStream in = new DataInputStream(inStream);

		String fileName = uuid()+src.substring(src.lastIndexOf("/")+1,src.length());
		if(!fileName.endsWith(".jpg")&&!fileName.endsWith(".png")&&!fileName.endsWith(".gif")){
			fileName = fileName + ".jpg";
		}
		URL urlObj = new URL(
				"https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token="
						+ access_token);

		JSONObject json = postFile(urlObj,in,fileName);
		return json.getString("url");
	}

	//获取图文消息中img src路径集合
	public static Set<String> getImgStr(String htmlStr) {
		Set<String> pics = new HashSet<>();
		String img = "";
		Pattern p_image;
		Matcher m_image;
		//     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
		String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
		p_image = Pattern.compile
				(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			// 得到<img />数据
			img = m_image.group();
			// 匹配<img>中的src数据
			Matcher m = Pattern.compile("src\\s*=\\s*'?(.*?)('|>|\\s+)").matcher(img);
			while (m.find()) {
				pics.add(m.group(1));
			}
		}
		return pics;
	}

	//推送文件至指定url
	public static JSONObject postFile(URL urlObj,DataInputStream in,String fileName) throws Exception {
		/**
		 * 第一部分
		 */
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);
		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
				+ fileName + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);
		// 文件正文部分
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		JSONObject json = (JSONObject) JSON.parse(result);
		return json;
	}


/*
	//为多个用户添加标签
	public boolean addTagToUsers(int tagId, String[] openids, String appid, String appsecret) {
		getAccess_token(appid, appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token="+access_token;
		JSONObject json = new JSONObject();
		json.put("openid_list", openids);
		json.put("tagid", tagId);
		JSONObject result = WeChatUtil.post(json, url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)){
			return addTagToUsers(tagId,openids,appid,appsecret);
		}
		if(errcode==0){
			return true;
		}else{
			return false;
		}
	}

	//为多个用户取消标签
	public boolean deleteTagFromUsers(int tagId, String[] openids, String appid, String appsecret) {
		getAccess_token(appid, appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token="+access_token;
		JSONObject json = new JSONObject();
		String[] arr = new String[openids.length];
		for(int i = 0;i<openids.length;i++){
			arr[i] = openids[i];
		}
		json.put("openid_list", arr);
		json.put("tagid", tagId);
		JSONObject result = WeChatUtil.post(json, url);
		int errcode = result.getIntValue("errcode");
		if(isAccessTokenError(errcode)){
			return deleteTagFromUsers(tagId,openids,appid,appsecret);
		}
		if(errcode==0){
			return true;
		}else{
			return false;
		}
	}*/

	/*
	//3.根据openid群发
	public static boolean sendMessageByOpenids(String openids, String msgType, JSONObject message,String appid,String appsecret) {
		getAccess_token(appid,appsecret);
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="
				+ access_token;
		JSONObject json = new JSONObject();
		openids = openids.substring(1,openids.length()-1);
		String[] arr = openids.split(",");
		json.put("touser", arr);
		json.put(msgType, message);
		json.put("msgtype", msgType);
		if("mpnews".equals(msgType)){
			// 判定为转载是是否继续群发 1为继续群发 0停止群发
			json.put("send_ignore_reprint", 1);
		}
		JSONObject result = WeChatUtil.post(json, url);
		int errcode = result.getIntValue("errcode");
		if(errcode == 40014 || errcode == 40001 || errcode == 42001){
			return sendMessageByOpenids(openids,msgtype,message);
		}
		if (errcode==0) {
			return true;
		} else {
			return false;
		}
	}
*/

}
