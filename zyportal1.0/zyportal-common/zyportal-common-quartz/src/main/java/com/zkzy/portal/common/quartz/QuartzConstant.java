package com.zkzy.portal.common.quartz;

/**
 * Created by Administrator on 2017/6/29 0029.
 * 常量类
 */
public class QuartzConstant {
    public static final String BASE_JOB_ID="basejobid";


    public static String QUARTZ_NORMAL="NORMAL";
    public static String QUARTZ_PAUSED="PAUSED";
    public static String QUARTZ_NONE="NONE";
    public static String QUARTZ_COMPLETE="COMPLETE";
    public static String QUARTZ_ERROR="ERROR";
    public static String QUARTZ_BLOCKED="BLOCKED";


    public static final int STATE_BLOCKED =4;//阻塞
    public static final int STATE_COMPLETE =2;//完成
    public static final int STATE_ERROR =3;//错误
    public static final int STATE_NONE =-1;//不存在
    public static final int STATE_NORMAL =0;//正常
    public static final int STATE_PAUSED =1;//暂停



}
