package com.zkzy.portal.common.utils;

import java.util.ArrayList;

/**
 * Created by wangzl on 2018/6/7.
 */
public class GeoUtil {

    /**
     * 判断目标点是否在多边形内(由多个点组成)<br/>
     *
     * @param px
     *            目标点的经度坐标
     * @param py
     *            目标点的纬度坐标
     * @return
     */
    public static boolean judgePointInPolygons(double px, double py) {
        boolean flag = false;
        ArrayList<Object> areaPolygonArrayList = new GeoJson().getAreaPolygonDataArray();
        if(null!=areaPolygonArrayList){
            for(int i = 0; i < areaPolygonArrayList.size(); i++){
                ArrayList<Double[]> xyArrayList = (ArrayList<Double[]>) areaPolygonArrayList.get(i);
                flag = isPointInPolygon(px, py, xyArrayList);
                if(flag){
                    break;
                }
            }
        }
        return  flag;
    }

    /**
     * 判断目标点是否在多边形内(由多个点组成)<br/>
     *
     * @param px
     *            目标点的经度坐标
     * @param py
     *            目标点的纬度坐标
     * @param xyArrayList
     *            多边形点集合
     * @return
     */
    public static boolean isPointInPolygon(double px, double py, ArrayList<Double[]> xyArrayList) {
        boolean isInside = false;
        double ESP = 1e-9;
        int count = 0;
        double linePoint1x = px;
        double linePoint1y = py;
        double linePoint2x = 180;
        double linePoint2y = py;

        for (int i = 0; i < xyArrayList.size() - 1; i++) {
            Double[] borderPoint = xyArrayList.get(i);
            Double[] borderNextPoint = xyArrayList.get(i+1);
            double cx1 = borderPoint[0];
            double cy1 = borderPoint[1];
            double cx2 = borderNextPoint[0];
            double cy2 = borderNextPoint[1];
            // 如果目标点在任何一条线上
            if (isPointOnLine(px, py, cx1, cy1, cx2, cy2)) {
                return true;
            }
            // 如果线段的长度无限小(趋于零)那么这两点实际是重合的，不足以构成一条线段
            if (Math.abs(cy2 - cy1) < ESP) {
                continue;
            }
            // 第一个点是否在以目标点为基础衍生的平行纬度线
            if (isPointOnLine(cx1, cy1, linePoint1x, linePoint1y, linePoint2x,
                    linePoint2y)) {
                // 第二个点在第一个的下方,靠近赤道纬度为零(最小纬度)
                if (cy1 > cy2)
                    count++;
            }
            // 第二个点是否在以目标点为基础衍生的平行纬度线
            else if (isPointOnLine(cx2, cy2, linePoint1x, linePoint1y,
                    linePoint2x, linePoint2y)) {
                // 第二个点在第一个的上方,靠近极点(南极或北极)纬度为90(最大纬度)
                if (cy2 > cy1)
                    count++;
            }
            // 由两点组成的线段是否和以目标点为基础衍生的平行纬度线相交
            else if (isIntersect(cx1, cy1, cx2, cy2, linePoint1x, linePoint1y,
                    linePoint2x, linePoint2y)) {
                count++;
            }
        }
        if (count % 2 == 1) {
            isInside = true;
        }

        return isInside;
    }

    /**
     * 目标点是否在目标边上边上<br/>
     *
     * @param px0
     *            目标点的经度坐标
     * @param py0
     *            目标点的纬度坐标
     * @param px1
     *            目标线的起点(终点)经度坐标
     * @param py1
     *            目标线的起点(终点)纬度坐标
     * @param px2
     *            目标线的终点(起点)经度坐标
     * @param py2
     *            目标线的终点(起点)纬度坐标
     * @return
     */
    public static boolean isPointOnLine(double px0, double py0, double px1,
                                 double py1, double px2, double py2) {
        boolean flag = false;
        double ESP = 1e-9;// 无限小的正数
        if ((Math.abs(multiply(px0, py0, px1, py1, px2, py2)) < ESP)
                && ((px0 - px1) * (px0 - px2) <= 0)
                && ((py0 - py1) * (py0 - py2) <= 0)) {
            flag = true;
        }
        return flag;
    }

    public static double multiply(double px0, double py0, double px1, double py1,
                           double px2, double py2) {
        return ((px1 - px0) * (py2 - py0) - (px2 - px0) * (py1 - py0));
    }

    /**
     * 是否有 横断<br/> 参数为四个点的坐标
     *
     * @param px1
     * @param py1
     * @param px2
     * @param py2
     * @param px3
     * @param py3
     * @param px4
     * @param py4
     * @return
     */
    public static boolean isIntersect(double px1, double py1, double px2, double py2,
                                double px3, double py3, double px4, double py4) {
        boolean flag = false;
        double d = (px2 - px1) * (py4 - py3) - (py2 - py1) * (px4 - px3);
        if (d != 0) {
            double r = ((py1 - py3) * (px4 - px3) - (px1 - px3) * (py4 - py3))
                    / d;
            double s = ((py1 - py3) * (px2 - px1) - (px1 - px3) * (py2 - py1))
                    / d;
            if ((r >= 0) && (r <= 1) && (s >= 0) && (s <= 1)) {
                flag = true;
            }
        }
        return flag;
    }

    public static void main(String[] args){
        boolean isPointInPolygon = GeoUtil.judgePointInPolygons(121.121,31.555);
        System.out.println(isPointInPolygon);
    }

}
