package com.zkzy.portal.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by wangzl on 2018/6/7.
 */
public class GeoJson {

    public ArrayList<Object> getAreaPolygonDataArray() {
        ArrayList<Object> areaPolygonArrayList = new ArrayList<Object>();

        String filePath = this.getClass().getClassLoader().getResource("area-polygon.json").getPath().substring(1);
        try{
            filePath = java.net.URLDecoder.decode(filePath, "utf-8");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        String JsonContext  = new GeoJson().ReadFile(filePath);
        System.out.println(JsonContext);

        // 创建json对象
        JSONObject dataJson = JSON.parseObject(JsonContext);
        // 找到features的json数组
        JSONArray features = dataJson.getJSONArray("features");
        for (int i = 0; i < features.size(); i++) {
            ArrayList<Double[]> xyArrayList = new ArrayList<Double[]>();
            // 获取features数组的第i个json对象
            JSONObject info = features.getJSONObject(i);
            // 找到geometry的json对象
            JSONObject geometry = info.getJSONObject("geometry");
            // 读取geometry对象里的coordinates信息
            JSONArray coordinates = geometry.getJSONArray("coordinates");
            for(int j = 0; j< coordinates.size(); j++){
                JSONArray coordinate = coordinates.getJSONArray(j);
                for(int k = 0; k< coordinate.size(); k++){
                    JSONArray coord = coordinate.getJSONArray(k);
                    for(int n = 0; n< coord.size(); n++){
                        JSONArray xyArray = coord.getJSONArray(n);
                        double x = Double.parseDouble(xyArray.get(0).toString());
                        double y = Double.parseDouble(xyArray.get(1).toString());
                        //System.out.println(x + ":::" + y);
                        xyArrayList.add(new Double[]{x,y});
                    }
                }
            }
            areaPolygonArrayList.add(xyArrayList);
        }
        return areaPolygonArrayList;
    }

    //读取文件
    public String ReadFile(String Path) {
        BufferedReader reader = null;
        String laststr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                laststr += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }

    public static void main(String[] args) {
        ArrayList<Object> areaPolygonArrayList = new GeoJson().getAreaPolygonDataArray();
        System.out.println(areaPolygonArrayList.size());
    }
}