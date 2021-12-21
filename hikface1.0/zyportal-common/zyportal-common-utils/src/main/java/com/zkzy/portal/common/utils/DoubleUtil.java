package com.zkzy.portal.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/8/30 0030.
 */
public class DoubleUtil {
    private static Logger logger = LoggerFactory.getLogger(DoubleUtil.class);
    /**
     * double 四舍五入 保留2位,返回字符串
     * @param d
     * @return
     */
    public static String formatDouble2Str(double d){
        return formatDoubleStr(d,2);
    }

    /**
     * double 四舍五入 保留 decimals 位,返回字符串
     * @param d
     * @param decimals
     * @return
     */
    public static String formatDoubleStr(double d, int decimals){
        BigDecimal b = new BigDecimal(d);
//        System.out.println(b.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        return b.setScale(decimals,BigDecimal.ROUND_HALF_UP).toString();
    }

}
