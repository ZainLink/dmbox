package com.zkzy.portal.common.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Thinkpad on 2018/3/20.
 */
public class CommonUtils {
    public static Map<String,Object> mapConvert(Map map) {
        Map<String,Object> dataMap= new HashMap<String, Object>(0);
        if(map!=null){
            Iterator it=map.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry entry=(Map.Entry)it.next();
                Object ok=entry.getKey();
                Object ov=entry.getValue()==null?"":entry.getValue();
                String key=ok.toString();
                String keyval="";
                String[] value=new String[1];
                if(ov instanceof String[]){
                    value=(String[])ov;
                }else{
                    value[0]=ov.toString();
                }
                keyval+=value[0];
                for(int k=1;k<value.length;k++){
                    keyval+=","+value[k];
                }
                dataMap.put(key, keyval);
            }
        }
        return dataMap;
    }

    /**
     * 单个集合的值转为double
     * @author yangl
     * @version 2016年3月28日
     * @param list
     * @return
     * @see 1.0
     */
    public static Double getDoubleByList(List list){
        Double d = null;
        if(list != null && list.size() > 0){
            Object o = list.get(0);
            if(o != null){
                d = Double.parseDouble(o.toString());
            }
        }
        return d;
    }

    /**
     * 根据时间获取时段
     * 比如 2016-01-28 09:45:00 返回 09-10
     * @author yangl
     * @version 2016年3月29日
     * @param tm 时间
     * @return 时段
     * @see 1.0
     */
    public static String handleTm(String tm){
        String sd = null;
        if(null != tm){
            int l = tm.indexOf(" ");
            String s = tm.substring(l+1, l+3);
            int v = Integer.parseInt(s);
            int minus = v+1;
            sd = judeTen(v)+"-"+judeTen(minus);
        }
        return sd;
    }

    public static String judeTen(Integer v){
        String s = v+"时";
//    	if(v<10){
//			s="0"+v;
//		}else{
//			s=v+"";
//		}
        return s;
    }

    /**
     * 截取字符串
     * @author yangl
     * @version 2016年3月30日
     * @param str 目标字符串
     * @param startLen 开始长度
     * @param endLen 结束长度
     * @return
     * @see 1.0
     */
    public static String subStr(String str, int startLen, int endLen){
        String nstr = null;
        if(null != str){
            nstr = str.substring(startLen, endLen);
        }
        return nstr;
    }

    public static Double judgOneDouble(Double d){
        Double f1 = null;
        if(d!=null){
            BigDecimal bg = new BigDecimal(d);
            f1= bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return f1;
    }

    public static Double judgTwoDouble(Double d){
        Double f1 = null;
        if(d!=null){
            BigDecimal bg = new BigDecimal(d);
            f1= bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return f1;
    }

    public static void main(String[] args) {
        System.out.println(handleTm("2016-01-28 10:45:00"));
    }
}
