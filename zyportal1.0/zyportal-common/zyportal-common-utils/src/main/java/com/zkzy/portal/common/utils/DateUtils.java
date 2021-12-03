package com.zkzy.portal.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by wangzl on 2018/3/1.
 */
public class DateUtils {
    /**
     * 定义常量
     **/
    public static final String DATE_JFP_STR = "yyyyMM";
    public static final String DATE_JFP_YEAR = "yyyy";
    public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_SMALL_STR = "yyyy-MM-dd";
    public static final String DATE_SMALL_STRHH = "yyyy-MM-dd HH";
    public static final String DATE_SSMALL_STR = "yyyy-MM";
    public static final String DATE_KEY_STR = "yyMMddHHmmss";
    public static final String DATE_FULL = "yyyyMMddHHmmss";
    public static final String DATE_FULL_TO_MILLISECOND = "yyyyMMddHHmmssSSS";
    public static String dateToUnixTimestamp;


    //获取当天

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getNowDay() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_SMALL_STR);
        return df.format(new Date());
    }

    //获取昨天
    public static String getLastDay() {
        Date as = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        String time = matter1.format(as);
        return time;
    }


    //获取当前月
    public static String getMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String monthstr = null;
        if (month < 10) {
            monthstr = "0" + month;
        }
        return year + "-" + monthstr;
    }

    //获取上个月
    public static String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String monthstr = null;
        if (month == 1) {
            year--;
            monthstr = "12";
        } else {
            month--;
            if (month < 10) {
                monthstr = "0" + month;
            }
        }
        return year + "-" + monthstr;
    }

    /**
     * @param date 当前时间
     * @return
     * @flag 0 返回yyyy-MM-dd 00:00:00日期<br>
     * 1 返回yyyy-MM-dd 23:59:59日期
     */
    public static String weeHours(Date date, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        if (flag == 0) {
            return df.format(cal.getTime());
        } else if (flag == 1) {
            //凌晨23:59:59
            cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
        }

        return df.format(cal.getTime());

    }


    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return
     */
    public static Date parse(String strDate) {
        return parse(strDate, DATE_FULL_STR);
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 两个时间比较
     *
     * @param date1
     * @return
     */
    public static int compareDateWithNow(Date date1) {
        Date date2 = new Date();
        int rnum = date1.compareTo(date2);
        return rnum;
    }

    /**
     * 两个时间比较(时间戳比较)
     *
     * @param date1
     * @return
     */
    public static int compareDateWithNow(long date1) {
        long date2 = dateToUnixTimestamp();
        if (date1 > date2) {
            return 1;
        } else if (date1 < date2) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        return df.format(new Date());
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static String getNowTime(String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        return df.format(new Date());
    }

    /**
     * 获取系统当前计费期
     *
     * @return
     */
    public static String getJFPTime() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_JFP_STR);
        return df.format(new Date());
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date)
                    .getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将指定的日期转换成Unix时间戳
     *
     * @param date 需要转换的日期 yyyy-MM-dd
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将当前日期转换成Unix时间戳
     *
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp() {
        ZoneOffset zoneOffset = ZoneOffset.ofHours(16);
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.toEpochSecond(zoneOffset);
    }

    /**
     * 时间戳有效判断
     */

    public static boolean sureTimestamp(Long date) {
        long timestamp =DateUtils.dateToUnixTimestamp();
        long a = (timestamp - date) / 60;
        if (a < 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 将Unix时间戳转换成日期
     *
     * @param timestamp 时间戳
     * @return String 日期字符串
     */
    public static String unixTimestampToDate(long timestamp) {
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
        sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sd.format(new Date(timestamp));
    }

    /**
     * @param date 当前日期
     * @return String 日期字符串
     * @Title: getyyyyMMddHH
     * @Description: 返回日期字符串格式：getyyyyMMddHH
     * @author yangl
     */
    public static String getyyyyMMddHH(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_SMALL_STRHH);
        return df.format(date);
    }

    /**
     * @param date 当前日期
     * @return String 日期字符串
     * @Title: getyyyy
     * @Description: 返回日期字符串格式：getyyyy
     * @author yangl
     */
    public static String getyyyy(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_JFP_YEAR);
        return df.format(date);
    }

    /**
     * @param date 当前日期
     * @return String 日期字符串
     * @Title: getyyyyMMddHHmmss
     * @Description: 返回日期字符串格式：yyyyMMddHHmmss
     * @author yangl
     */
    public static String getyyyyMMddHHmmss(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL);
        return df.format(new Date());
    }

    /**
     * @param date 当前日期
     * @return String 日期字符串
     * @Title: getyyyyMMddHHmmss
     * @Description: 返回日期字符串格式：yyyyMMddHHmmss
     * @author yangl
     */
    public static String getyyyyMMddHHmmssSSS(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_TO_MILLISECOND);
        return df.format(new Date());
    }

    /**
     * 获取日期字符串，格式：yyyy-MM-dd
     *
     * @Title: getYYYYMMDDDayStr
     * @author yangl
     */
    public static String getYYYYMMDDDayStr(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_SMALL_STR);
        return df.format(date);
    }

    /**
     * 获取日期字符串，格式：yyyy-MM-dd HH:mm:ss
     *
     * @Title: getYYYYMMDDHHMMSSDayStr
     * @author yangl
     */
    public static String getYYYYMMDDHHMMSSDayStr(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        return df.format(new Date());
    }

    /**
     * 获取日期字符串，格式：yyyy-MM
     *
     * @Title: getYYYYMMDayStr
     * @author yangl
     */
    public static String getYYYYMMDayStr(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_SSMALL_STR);
        return df.format(new Date());
    }

    /**
     * 获得前day天的日期
     *
     * @param date
     * @param day  前几天
     * @return Date 日期
     * @Title: getPreDay
     * @author yangl
     */
    public static Date getPreDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 返回去年的今天 格式:2017-01-01 00:11:22
     *
     * @return
     */
    public static String getLastYearDate() {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
        GregorianCalendar aGregorianCalendar = new GregorianCalendar();
        aGregorianCalendar.set(Calendar.YEAR, aGregorianCalendar
                .get(Calendar.YEAR) - 1);
        return df.format(aGregorianCalendar.getTime());
    }


    public static String getPreDateByStr(String value) {
        if (null != value && !value.equals("")) {
            return getYYYYMMDDDayStr(getPreDay(getDateByStr(value), -1));
        } else {
            return "";
        }
    }

    /**
     * 获取当前年的第一天
     *
     * @param year
     * @return
     */
    public static String getYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return getYYYYMMDDDayStr(currYearFirst);
    }

    /**
     * 获取当前年
     *
     * @return int
     * @Title: getCurrentYear
     * @author yangl
     */
    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    public static Date getDateByStr(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 得到UTC时间，类型为字符串，格式为"yyyy-MM-dd HH:mm:ss"<br />
     * 如果获取失败，返回null
     *
     * @return
     */
    public static Long getUTCTimeStr(String indate) {
        Date date = null;

        try {
            DateFormat format = new SimpleDateFormat(DATE_FULL_STR);
            date = format.parse(indate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());//TimeZone.getDefault()获取主机的默认

        cal.setTime(date);

        return date.getTime() + cal.getTimeZone().getRawOffset();
    }

    public static Long getUTCTimeStr(Date date) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());//TimeZone.getDefault()获取主机的默认
        cal.setTime(date);
        return date.getTime() + cal.getTimeZone().getRawOffset();
    }

    public void DateToUTC() {
        SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar gc = GregorianCalendar.getInstance();
        System.out.println("foo:" + foo.format(new Date()) + "    毫秒：" + gc.getTimeInMillis());

        //1、取得本地时间：
        java.util.Calendar cal = java.util.Calendar.getInstance();

        //2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);

        //3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);

        //4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

        //之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
        System.out.println("UTC:" + foo.format(new Date(cal.getTimeInMillis())) + "---------------" + cal.getTimeInMillis());

    }

    /**
     * 获得当前日的前一个月
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getLastMonth(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        DateFormat format = new SimpleDateFormat(DATE_SMALL_STR);
        try {
            date = format.parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        c.add(Calendar.MONTH, -1);// before 8 hour
        return format.format(c.getTime());
    }


    /**
     * 获得指定日期的前n小时
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedHourBefore(String specifiedDay, int d) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        DateFormat format = new SimpleDateFormat(DATE_FULL_STR);
        try {
            date = format.parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, -d);// before 8 hour
        return format.format(c.getTime());
    }

    /**
     * 取当前时间的前N个小时
     *
     * @return
     * @author yangl
     * @version 2016年3月28日
     * @see 1.0
     */
    public static String beforeOneHourToNowDate(Integer hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
//    	System.out.println("一个小时前的时间："+ df.format(calendar.getTime()));
//    	System.out.println("当前的时间："+ df.format(new Date()));
        return df.format(calendar.getTime());
    }

    public static String beforeOneHourToNowDate(Integer hour, String datefotmet) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        SimpleDateFormat df = new SimpleDateFormat(datefotmet);
//    	System.out.println("一个小时前的时间："+ df.format(calendar.getTime()));
//    	System.out.println("当前的时间："+ df.format(new Date()));
        return df.format(calendar.getTime());
    }

    public static String getTimeFromLong(String d) {
        Date dat = new Date(Long.parseLong(d));
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        DateFormat format = new SimpleDateFormat(DATE_FULL_STR);
        return format.format(gc.getTime());
    }

    /**
     * 计算两个时间相差的小时数
     *
     * @param startTime 开始时间字符串   yyyy-MM-dd HH:mm:ss
     * @param endTime   结束时间字符串   yyyy-MM-dd HH:mm:ss
     * @return 小时数
     */
    public static long getTwoTimeHour(String startTime, String endTime) {
        long hour = 0;
        if (DATE_FULL_STR != null && DATE_FULL_STR != null) {
            long start = dateToUnixTimestamp(startTime, DATE_FULL_STR);
            long end = dateToUnixTimestamp(endTime, DATE_FULL_STR);
            long time = end - start;
            hour = time / 3600000;
        }
        return hour;
    }

    /**
     * 当前月第一天
     *
     * @return
     */
    public static String getMonthFirstDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(date);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
//      StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
        return day_first;
    }

    /**
     * 当前月最后第一天
     *
     * @return
     */
    public static String getMonthLastDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        return last;
    }

    /**
     * 获取指定日期的周一日期
     *
     * @param date
     * @return
     * @throws ParseException
     * @see
     */
    public static String getWeekDay(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); //获取本周日的日期
        String dateStr = df.format(cal.getTime());
        String nowstr = df.format(date);
        if (dateStr.equals(nowstr)) {
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
            cal.add(Calendar.WEEK_OF_YEAR, -1);
        } else {
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
        }
        dateStr = df.format(cal.getTime());
        return dateStr;
    }


    public static boolean compareTime(String time1, String time2) {
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
            Date oldDate1 = format1.parse(time1);
            Date oldDate2 = format2.parse(time2);
            int a = oldDate1.compareTo(oldDate2);
            if (a >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void main(String[] args) {
//        Long time= DateUtils.dateToUnixTimestamp();
//        System.out.println(time);
        Long time = new Long((long) 1555558678);
        DateUtils.sureTimestamp(time);
    }
}
