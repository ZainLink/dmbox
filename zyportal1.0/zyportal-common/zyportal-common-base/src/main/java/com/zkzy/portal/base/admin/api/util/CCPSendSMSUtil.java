package com.zkzy.portal.base.admin.api.util;

import java.util.Set;
import java.util.HashMap;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CCPSendSMSUtil {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(CCPSendSMSUtil.class);

    //初始化静态变量
	public static CCPRestSmsSDK restAPI;

	/**
	 * 短信发送方法
	 *
	 * @param serverIP 通讯记录信息
	 * @param serverPort 通讯记录信息
	 * @param accountSid 通讯记录信息
	 * @param accountToken 通讯记录信息
	 * @param appId 通讯记录信息
	 * @param templateId 通讯记录信息
	 */
	public static void initSMS(String serverIP,String serverPort,
							   String accountSid,String accountToken,
							   String appId,String templateId){
		//初始化SDK
		restAPI = new CCPRestSmsSDK();

		//******************************注释*********************************************
		//*初始化服务器地址和端口                                                       *
		//*沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");*
		//*生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");       *
		//*******************************************************************************
		restAPI.init(serverIP, serverPort);

		//******************************注释*********************************************
		//*初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN     *
		//*ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
		//*参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。                   *
		//*******************************************************************************
		restAPI.setAccount(accountSid, accountToken);

		//******************************注释*********************************************
		//*初始化应用ID                                                                 *
		//*测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID     *
		//*应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
		//*******************************************************************************
		restAPI.setAppId(appId);
	}

	/**
	 * 短信发送方法
	 *
	 * @param telephones 通讯记录信息
	 * @param templateId 模板ID
	 * @param contents 通讯记录信息
	 */
	public static String sendSMS(String telephones,String templateId,String[] contents){

		HashMap<String, Object> result = null;
				
		//******************************注释****************************************************************
		//*调用发送模板短信的接口发送短信                                                                  *
		//*参数顺序说明：                                                                                  *
		//*第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号                          *
		//*第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。    *
		//*系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
		//*第三个参数是要替换的内容数组。																														       *
		//**************************************************************************************************
		
		//**************************************举例说明***********************************************************************
		//*假设您用测试Demo的APP ID，则需使用默认模板ID 1，发送手机号是13800000000，传入参数为6532和5，则调用方式为           *
		//*result = restAPI.sendTemplateSMS("13800000000","1" ,new String[]{"6532","5"});																		  *
		//*则13800000000手机号收到的短信内容是：【云通讯】您使用的是云通讯短信模板，您的验证码是6532，请于5分钟内正确输入     *
		//*********************************************************************************************************************
		result = restAPI.sendTemplateSMS(telephones,templateId ,contents);

		if("000000".equals(result.get("statusCode"))){
			//正常返回输出data包体信息（map）
			HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for(String key:keySet){
				Object object = data.get(key);
				LOGGER.debug(key +" = "+object);
			}
		}else{
			//异常返回输出错误码和错误信息
			LOGGER.debug("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
		}
		return result.get("statusCode").toString();
	}

	public static HashMap<String, Object> sendMsg (String telephones,String templateId,String[] contents){
		HashMap<String, Object> result = null;
		result = restAPI.sendTemplateSMS(telephones,templateId ,contents);
		return result;
	}
}
