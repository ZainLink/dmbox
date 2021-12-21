package com.zkzy.portal.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * description: GeoHash
 *
 * @author SZQ
 * @date 2020/7/20 9:44
 */
public class GeoHashHelper {
    public final static double Max_Lat = 90;
    public final static double Min_Lat = -90;
    public final static double Max_Lng = 180;
    public final static double Min_Lng = -180;
    private final static int length = 20;
    private final static double latUnit = (Max_Lat - Min_Lat) / (1 << 20);
    private final static double lngUnit = (Max_Lng - Min_Lng) / (1 << 20);
    private final static String[] base32Lookup =
            {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "b", "c", "d", "e", "f", "g", "h",
                    "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private static void convert(double min, double max, double value, List<Character> list) {
        if (list.size() > (length - 1)) {
            return;
        }
        double mid = (max + min) / 2;
        if (value < mid) {
            list.add('0');
            convert(min, mid, value, list);
        } else {
            list.add('1');
            convert(mid, max, value, list);
        }
    }

    private static String base32Encode(final String str) {
        String unit = "";
        StringBuilder sb = new StringBuilder();
        for (int start = 0; start < str.length(); start = start + 5) {
            unit = str.substring(start, start + 5);
            sb.append(base32Lookup[convertToIndex(unit)]);
        }
        return sb.toString();
    }

    private static int convertToIndex(String str) {
        int length = str.length();
        int result = 0;
        for (int index = 0; index < length; index++) {
            result += str.charAt(index) == '0' ? 0 : 1 << (length - 1 - index);
        }
        return result;
    }

    public static String encode(double lat, double lng) {
        try {
            List<Character> latList = new ArrayList<Character>();
            List<Character> lngList = new ArrayList<Character>();
            convert(Min_Lat, Max_Lat, lat, latList);
            convert(Min_Lng, Max_Lng, lng, lngList);
            StringBuilder sb = new StringBuilder();
            for (int index = 0; index < latList.size(); index++) {
                sb.append(lngList.get(index)).append(latList.get(index));
            }
            return base32Encode(sb.toString());
        }catch (Exception e){
            return null;
        }
    }

    public static List<String> around(double lat, double lng) {
        List<String> list = new ArrayList<String>();
        list.add(encode(lat, lng));
        list.add(encode(lat + latUnit, lng));
        list.add(encode(lat - latUnit, lng));
        list.add(encode(lat, lng + lngUnit));
        list.add(encode(lat, lng - lngUnit));
        list.add(encode(lat + latUnit, lng + lngUnit));
        list.add(encode(lat + latUnit, lng - lngUnit));
        list.add(encode(lat - latUnit, lng + lngUnit));
        list.add(encode(lat - latUnit, lng - lngUnit));
        return list;
    }
}
