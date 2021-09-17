package com.zkzy.portal.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 *
 * @author admin
 */
public class DateHelper extends DateUtils {

    public static final int DEFAULT = 0;
    public static final int YM = 1;
    public static final int YMR_SLASH = 11;
    public static final int NO_SLASH = 2;
    public static final int YM_NO_SLASH = 3;
    public static final int DATE_TIME = 4;
    public static final int DATE_TIME_NO_SLASH = 5;
    public static final int DATE_HM = 6;
    public static final int TIME = 7;
    public static final int HM = 8;
    public static final int LONG_TIME = 9;
    public static final int SHORT_TIME = 10;
    public static final int DATE_TIME_LINE = 12;

    /**
     * 日期格式
     */
    private static final String[] PARSE_PATTERNS = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"
    };


    public static boolean isValidDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2018-02-29会被接受，并转换成2018-03-01

            format.setLenient(false);
            Date date = format.parse(strDate);
            if (date != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * 日期格式
     */
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 日期时间格式
     */
    private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     *
     * @return the date
     */
    public static String getDate() {
        return getDate(DEFAULT_DATE_FORMAT);
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @param pattern the pattern
     * @return the date
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @param date    the date
     * @param pattern the pattern
     * @return the string
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, DEFAULT_DATE_FORMAT);
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     *
     * @param date the date
     * @return the string
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     *
     * @return the time
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     *
     * @return the date time
     */
    public static String getDateTime() {
        return formatDate(new Date(), DEFAULT_DATETIME_FORMAT);
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     *
     * @return the year
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     *
     * @return the month
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     *
     * @return the day
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     *
     * @return the week
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式
     * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
     * "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     *
     * @param str the str
     * @return the date
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), PARSE_PATTERNS);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date 对比日期
     * @return long long
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     *
     * @param date 对比日期
     * @return long long
     */
    public static long pastHour(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     *
     * @param date 对比日期
     * @return long long
     */
    public static long pastMinutes(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis 毫秒数
     * @return 天, 时:分:秒.毫秒
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = timeMillis / (60 * 60 * 1000) - day * 24;
        long min = timeMillis / (60 * 1000) - day * 24 * 60 - hour * 60;
        long s = timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
        long sss = timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000;
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param before 开始日期
     * @param after  结束日期
     * @return 天数 distance of two date
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (double) (1000 * 60 * 60 * 24);
    }

    public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    /**
     * 获取东八区当前时间
     *
     * @return Date est 8 date
     */
    public static Date getEst8Date() {
        TimeZone tz = TimeZone.getTimeZone("GMT+8:00");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        dateFormat.setTimeZone(tz);
        return parseDate(dateFormat.format(date));
    }

    //获取上一个月
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.MONTH, -1);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        String lastMonth = dft.format(cal.getTime());
        return lastMonth;
    }

    public static String getLastYear() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        //过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        String year = format.format(y);
        return year;
    }

    public static String dateToStr(Date date, String pattern) {
        if ((date == null) || (date.equals("")))
            return null;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static List<String> getWeekBetween(String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();

        //String maxDate="2018-11";
        int weeks = 52;
        int listLength = 53;
        String maxDateyear = maxDate.split("-")[0];
        String maxDateweek = maxDate.split("-")[1];
        int maxyear = Integer.parseInt(maxDateyear);
        int maxweek = Integer.parseInt(maxDateweek);
        if (maxweek < listLength) {
            Calendar cal = Calendar.getInstance();
            Date date;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(maxDateyear + "-01-01");
                cal.setTime(date);
            } catch (Exception e) {
                System.out.print(e);
            }
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w == 0) {
                weeks = 53;
            }
            for (int i = 0; i < listLength; i++) {
                if (maxweek + i + 1 <= listLength) {
                    int num = weeks - listLength + maxweek + i + 1;
                    if (num < 10) {
                        result.add((maxyear - 1) + "-0" + (weeks - listLength + maxweek + i + 1));
                    } else {
                        result.add((maxyear - 1) + "-" + (weeks - listLength + maxweek + i + 1));
                    }
                } else {
                    int num = i - (listLength - maxweek) + 1;
                    if (num < 10) {
                        result.add(maxyear + "-0" + (i - (listLength - maxweek) + 1));
                    } else {
                        result.add(maxyear + "-" + (i - (listLength - maxweek) + 1));
                    }
                }
            }
        } else {
            for (int i = (maxweek - listLength + 1); i <= maxweek; i++) {
                if (i < 10) {
                    result.add(maxyear + "-0" + i);
                } else {
                    result.add(maxyear + "-" + i);
                }

            }
        }

        /*if(Integer.parseInt(maxDateweek)<listLength){
            Calendar cal = Calendar.getInstance();
            Date date;
            try{
                date=new SimpleDateFormat("yyyy-MM-dd").parse(maxDateyear+"-01-01");
                cal.setTime(date);
            }catch(Exception e){
                System.out.print(e);
            }
            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if(w==0){
                weeks=53;
            }
            for(int i=0;i<listLength;i++){
                if(maxweek+i+1<=listLength){
                    result.add((maxyear-1)+"-"+(weeks-listLength+maxweek+i+1));
                }else{
                    result.add("2018-0"+(i-(listLength-maxweek)+1));
                }
            }
        }else{
            for(int i=(maxweek-listLength+1);i<=maxweek;i++){
                if(i<10){
                    result.add("2018-0"+i);
                }else{
                    result.add("2018-"+i);
                }

            }
        }*/
        return result;
    }


    public static long getBetweenDate(String passDate) {
        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOne = dateFormat.parse(passDate);
            long days = Math.abs(((dateOne.getTime() - date.getTime()) / (1000 * 60 * 60 * 24)));
            return days;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String dateToStr(Date date) {
        return dateToStr(date, "yyyy/MM/dd");
    }


    public static String dateToStr(Date date, int type) {
        switch (type) {
            case 0:
                return dateToStr(date);
            case 1:
                return dateToStr(date, "yyyy/MM");
            case 2:
                return dateToStr(date, "yyyyMMdd");
            case 11:
                return dateToStr(date, "yyyy-MM-dd");
            case 3:
                return dateToStr(date, "yyyyMM");
            case 4:
                return dateToStr(date, "yyyy/MM/dd HH:mm:ss");
            case 5:
                return dateToStr(date, "yyyyMMddHHmmss");
            case 6:
                return dateToStr(date, "yyyy/MM/dd HH:mm");
            case 7:
                return dateToStr(date, "HH:mm:ss");
            case 8:
                return dateToStr(date, "HH:mm");
            case 9:
                return dateToStr(date, "HHmmss");
            case 10:
                return dateToStr(date, "HHmm");
            case 12:
                return dateToStr(date, "yyyy-MM-dd HH:mm:ss");
            case 13:
                return dateToStr(date, "yyyy-MM-dd HH:mm");
            case 14:
                return dateToStr(date, "yyyy-MM");
        }
        throw new IllegalArgumentException("未定义的type : " + type + "》》》》 请使用0-12");
    }

    /**
     * 获取两个时间的持续时间,单位小时,保留2位
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getDurationHour(Date startDate, Date endDate) {
        double duration = (endDate.getTime() - startDate.getTime()) / 3600000.0;
//		System.out.println(new DecimalFormat("##0.00").format(duration));
        return DoubleUtil.formatDouble2Str(duration);
    }

    public static Date formatyyyyMMddHHmmss(String time) {
        if (StringUtils.isEmpty(time)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String get31DayTime() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        now = now.minus(31, ChronoUnit.DAYS);
        return df.format(now);
    }

    public static String getYesterday(String formaterStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date time = calendar.getTime();
        return new SimpleDateFormat(formaterStr).format(time);
    }

    public static void main(String[] args) {
        System.out.println(DateHelper.isValidDate("2021-06-01 11:09:23"));
    }
}
