package com.zkzy.portal.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * Created by Thinkpad-W530 on 2021/1/25.
 */
public class SeInfo {


    public static String tel(String tel) {
        if (StringUtils.isNoneEmpty(tel)) {
            return tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } else {
            return null;
        }
    }


    public static String id(String id) {
        if (StringUtils.isNoneEmpty(id)) {
            return id.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
       System.out.println(SeInfo.id("32092219760908478X"));

    }
}
