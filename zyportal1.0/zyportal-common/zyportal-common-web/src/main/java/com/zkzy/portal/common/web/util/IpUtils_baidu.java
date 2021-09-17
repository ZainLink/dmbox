package com.zkzy.portal.common.web.util;


import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class IpUtils_baidu {
    public static Map<String, String> getIpInfo(String ip){
        String url = "http://api.map.baidu.com/location/ip?ak=jM04WbMCuASzfLvQvkObLP0X2n7Ye5ZR&coor=bd09ll&ip="+ip+"&qq-pf-to=pcqq.group";
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
        JSONObject json= JSONObject.parseObject(sbf.toString());
        Map<String,String> map=new HashMap<>();
        String status=json.getString("status");
        if("0".equals(json.getString("status"))){
            JSONObject contentJ=json.getJSONObject("content");
            map.put("city",contentJ.getJSONObject("address_detail").getString("city"));
            JSONObject pointJ=contentJ.getJSONObject("point");
            map.put("lng",pointJ.getString("x"));
            map.put("lat",pointJ.getString("y"));
            return map;
        }else{
            return null;
        }
    }

}

