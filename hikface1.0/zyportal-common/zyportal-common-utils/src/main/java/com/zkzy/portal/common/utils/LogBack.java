package com.zkzy.portal.common.utils;

/**
 * Created by Administrator on 2017/7/6.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBack {
    private static Logger log = LoggerFactory.getLogger(LoggerFactory.class);
    public static void print(String obj){
        log.info(obj.toLowerCase());
    }
    public static void printByLevel(Object obj,String method){
        try {
            log.getClass().getMethod(method,String.class).invoke(log,obj.toString());
        }catch(Exception e){
            e.printStackTrace();
            return;
        }

    }
}
