package com.zkzy.portal.common.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Thinkpad-W530 on 2021/2/24.
 */
public class OrderCodeFactory {

    /**
     * 生成订单单号编码
     *
     * @param
     */
    public static String getOrderCode() {
        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        String trandNo = getRandNum(17);
        return sdf+ trandNo;
    }


    public static String getRandNum(int charCount) {
        StringBuilder charValue = new StringBuilder();
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue.append(String.valueOf(c));
        }
        return charValue.toString();
    }

    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }

    public static void main(String[] args) {
        System.out.printf(OrderCodeFactory.getOrderCode());
    }

}
