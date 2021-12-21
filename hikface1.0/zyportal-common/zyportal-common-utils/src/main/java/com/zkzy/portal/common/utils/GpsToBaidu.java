package com.zkzy.portal.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thinkpad-W530 on 2020/6/28.
 */
public class GpsToBaidu {
    /**
     * 地球坐标系 (WGS-84) 相互转火星坐标系 (GCJ-02) 的转换算法
     *
     * @author jxh
     * @time 2013-5-16
     */
    private static double pi = 3.14159265358979324D;// 圆周率
    private static double a = 6378245.0D;// WGS 长轴半径
    private static double ee = 0.00669342162296594323D;// WGS 偏心率的平方
    private static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    /**
     * 中国坐标内
     *
     * @param lat
     * @param lon
     * @return
     */
    public static boolean outofChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }

    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }


    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }

    // 84->gcj02
    public static Map<String, Double> transform(double lon, double lat) {
        HashMap<String, Double> localHashMap = new HashMap<String, Double>();
        if (outofChina(lat, lon)) {
            localHashMap.put("lon", Double.valueOf(lon));
            localHashMap.put("lat", Double.valueOf(lat));
            return localHashMap;
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        localHashMap.put("lon", mgLon);
        localHashMap.put("lat", mgLat);
        return localHashMap;
    }

    // gcj02-84
    public static Map<String, Double> gcj2wgs(double lon, double lat) {
        Map<String, Double> localHashMap = new HashMap<String, Double>();
        double lontitude = lon
                - (((Double) transform(lon, lat).get("lon")).doubleValue() - lon);
        double latitude = (lat - (((Double) (transform(lon, lat)).get("lat"))
                .doubleValue() - lat));
        localHashMap.put("lon", lontitude);
        localHashMap.put("lat", latitude);
        return localHashMap;
    }

    /**
     * 火星坐标转换为百度坐标
     *
     * @param gg_lat
     * @param gg_lon
     */
    public static Map<String, Double> bd_encrypt(double gg_lat, double gg_lon) {
        Map<String, Double> bdlocalHashMap = new HashMap<String, Double>();
        double x = gg_lon, y = gg_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;
        bdlocalHashMap.put("lon", bd_lon);
        bdlocalHashMap.put("lat", bd_lat);
        return bdlocalHashMap;
    }

    /**
     * 84转百度
     *
     * @param latitude  纬度
     * @param longitude 经度
     * @return
     */
    public static Map<String, Double> wgs84_bd09(double latitude, double longitude) {
        //1,将84转国测局gcj-02
        Map<String, Double> mapForGCJ = transform(longitude, latitude);
        Double lonGCJ = mapForGCJ.get("lon");
        Double latGCJ = mapForGCJ.get("lat");
        //2.将gcj-02转百度
        Map<String, Double> mapForBD09 = bd_encrypt(latGCJ, lonGCJ);
        return mapForBD09;
    }

    /**
     * 百度转gcj02
     */
    public static Map<String, Double> bd09togcj02(double bd_lon, double bd_lat) {
        Map<String, Double> bdtogcmap = new HashMap<>();
        double x = bd_lon - 0.0065;
        double y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lng = z * Math.cos(theta);
        double gg_lat = z * Math.sin(theta);
        bdtogcmap.put("lon", gg_lng);
        bdtogcmap.put("lat", gg_lat);
        return bdtogcmap;
    }

    /**
     * 百度转84
     */
    public static Map<String, Double> bd09to84(double bd_lon, double bd_lat) {
        Map<String, Double> bdtogcmap = bd09togcj02(bd_lon, bd_lat);
        Double lon = bdtogcmap.get("lon");
        Double lat = bdtogcmap.get("lat");

        Map<String, Double> gcto84map = gcj2wgs(lon, lat);
        return gcto84map;
    }


    public static void main(String[] args) {
        Map<String, Double> tt = GpsToBaidu.wgs84_bd09(31.793585, 120.449181);
        System.out.println(tt);
    }
}
