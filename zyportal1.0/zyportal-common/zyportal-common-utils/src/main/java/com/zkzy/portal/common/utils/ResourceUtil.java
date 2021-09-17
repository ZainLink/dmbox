package com.zkzy.portal.common.utils;

import java.util.ResourceBundle;
public class ResourceUtil {

	private static final ResourceBundle bundle = ResourceBundle.getBundle("config");
	
	/**
	* @Title: readPropertiesValue
	* @Description: 根据key读取properties属性值
	* @author yangl  
	* @param filename 属性名
	* @param key properties的key
	* @return String 返回properties的值
	 */
	public static String  readPropertiesValue(String filename,String key){
		   ResourceBundle bundle = ResourceBundle.getBundle(filename);
	       try {
	            return bundle.getString(key);
	       } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	       }
	}

	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}

	/**
	 * 获得上传表单域的名称
	 * 
	 * @return
	 */
	public static final String getUploadFieldName() {
		return bundle.getString("uploadFieldName");
	}

	/**
	 * 获得上传文件的最大大小限制
	 * 
	 * @return
	 */
	public static final long getUploadFileMaxSize() {
		return Long.valueOf(bundle.getString("uploadFileMaxSize"));
	}

	/**
	 * 获得允许上传文件的扩展名
	 * 
	 * @return
	 */
	public static final String getUploadFileExts() {
		return bundle.getString("uploadFileExts");
	}

	/**
	 * 获得上传文件要放到那个目录
	 * 
	 * @return
	 */
	public static final String getUploadDirectory() {
		return bundle.getString("uploadDirectory");
	}

}
