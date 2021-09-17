package com.zkzy.portal.common.utils;

/**
 * description: 经纬度测距
 *
 * @author SZQ
 * @date 2020/7/20 9:50
 */
public class DistanceHepler {
    private final static double EARTH_RADIUS = 6378.137f;

    public static double distance(double lat1, double lng1, double lat2, double lng2) {
        double x1 = Math.cos(lat1) * Math.cos(lng1);
        double y1 = Math.cos(lat1) * Math.sin(lng1);
        double z1 = Math.sin(lat1);

        double x2 = Math.cos(lat2) * Math.cos(lng2);
        double y2 = Math.cos(lat2) * Math.sin(lng2);
        double z2 = Math.sin(lat2);

        double lineDistance =
                Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
        return EARTH_RADIUS * Math.PI * 2 * Math.asin(0.5 * lineDistance) / 180;
    }
}
