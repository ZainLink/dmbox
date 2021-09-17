package com.zkzy.portal.common.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkzy.portal.common.utils.StringHelper;
import org.apache.commons.collections.map.HashedMap;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/28 0028.
 * 获取ip及纯真ip
 */

public class IpUtils {
    public static final String RESULTCODE= "resultcode";//返回结果码
    public static final String ERROR_CODE="error_code" ;//返回错误码
    public static final String REASON="reason";//返回说明
    public static final String RESULT="result";//返回结果
    public static final String AREA="area";//地区
    public static final String LOCATION="location";//位置

    public static final String SUCCESSCODE="200";//返回正确的code


    /**
     * 获取请求的ip
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringHelper.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringHelper.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }

    public static String getIpAddressOld(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 解析获取的ip数据
     * @param ip
     * @return
     */
    public static Map<String,String> getJuheData(String ip){
        Map<String,String> data=new HashedMap();
        data.put(IpUtils.RESULTCODE,null);
        data.put(IpUtils.ERROR_CODE,null);
        data.put(IpUtils.REASON,null);
        data.put(IpUtils.RESULT,null);
        data.put(IpUtils.AREA,null);
        data.put(IpUtils.LOCATION,null);

        String jsonData=getIpArea(ip);
        if(jsonData!=null){
            JSONObject jsonObject = JSON.parseObject(jsonData);
            String resultcode=jsonObject.get(IpUtils.RESULTCODE)==null?null:jsonObject.get(IpUtils.RESULTCODE).toString();
            String error_code=jsonObject.get(IpUtils.ERROR_CODE)==null?null:jsonObject.get(IpUtils.ERROR_CODE).toString();
            String reason=jsonObject.get(IpUtils.REASON)==null?null:jsonObject.get(IpUtils.REASON).toString();
            data.put(IpUtils.RESULTCODE,resultcode);
            data.put(IpUtils.ERROR_CODE,error_code);
            data.put(IpUtils.REASON,reason);
            if(IpUtils.SUCCESSCODE.equals(resultcode)){
                String resultStr=jsonObject.get(IpUtils.RESULT)==null?null:jsonObject.get(IpUtils.RESULT).toString();
                data.put(IpUtils.RESULT,resultStr);
                if(resultStr!=null){
                    JSONObject resultObj=JSONObject.parseObject(resultStr);
                    String area=resultObj.get(IpUtils.AREA)==null?null:resultObj.get(IpUtils.AREA).toString();
                    String location=resultObj.get(IpUtils.LOCATION)==null?null:resultObj.get(IpUtils.LOCATION).toString();
                    data.put(IpUtils.AREA,area);
                    data.put(IpUtils.LOCATION,location);
                }
            }
            return data;
        }else {
            return null;
        }

    }
    public static Map<String,String> getJuheData(HttpServletRequest request){
        return getJuheData(getIpAddress(request));
    }

    /**
     * 获取ip信息
     * @param ip
     * @return
     */
    public static String getIpArea(String ip){
        String url = "http://apis.juhe.cn/ip/ip2addr?ip="+ip+"&key=1ff0d5f489435bc0884fd25debef2adc";
        StringBuffer sbf = null;
        BufferedReader br=null;
        try {
            URL oracle = new URL(url);
            URLConnection conn = oracle.openConnection();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine = null;
            sbf=new StringBuffer();
            while((inputLine = br.readLine()) != null){
                sbf.append(inputLine);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally{
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

}

