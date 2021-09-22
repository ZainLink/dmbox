package com.zkzy.portal.common.utils;

import java.time.LocalDate;
import java.time.Month;

/**
/**
 *@Author: UGER
 *@Created: 2020/7/8 9:29
 *@Description: 时间处理类
 */
public class Time4UgerUtils {

    /**
     *@Author: UGER
     *@Created: 2020/7/6 16:17
     *@Description: 获取当前时间所在季度的第一天和最后一天, isFirst为true是第一天,否则最后一天
     *@Param: [today, isFirst]
     *@Return: java.lang.String
     */
    public static String getStartOrEndDayOfQuarter(LocalDate today, Boolean isFirst){
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }
        Month month = today.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), firstMonthOfQuarter, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(today.isLeapYear()));
        }
        return resDate.toString();
    }

    /**
     *@Author: UGER
     *@Created: 2020/7/8 9:27
     *@Description: 获取当前第几季度
     *@Param: [today]
     *@Return: int
     */
    public static int getQuarter(LocalDate today){
        if(today == null){
            today = LocalDate.now();
        }
        return (today.getMonth().firstMonthOfQuarter().getValue()/3 + 1);
    }

}
