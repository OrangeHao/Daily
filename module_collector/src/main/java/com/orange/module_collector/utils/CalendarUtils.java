package com.orange.module_collector.utils;

import android.text.TextUtils;
import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CalendarUtils {
    // 当天
    public static String getThisToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //时间的格式化
        Calendar cal = Calendar.getInstance();     //类的实例化
        //当天的开始时间，这里之所以三个分开设置，是因为Calendar已经存在日期
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        //第一个时间格式化
        String start = sdf.format(cal.getTime());
        //当天的结束时间
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        //第二个时间格式化
        String end = sdf.format(cal.getTime());
        return start + "|" + end;
    }


    // 本周
    public static String getThisWeekDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar ca = Calendar.getInstance();
        //设定每周第一天是星期一（也可以是星期日）
        ca.setFirstDayOfWeek(Calendar.MONDAY);
        //获取星期日的日期，具体时间为当前时间
        ca.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        //设置周日结束时间（此处也可如上面那样设置时分秒即可）
        ca.set(ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), ca.get(Calendar.DATE), 23, 59, 59);
        String end = sdf.format(ca.getTime());
        //获取周一的日期
        ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        String start = sdf.format(ca.getTime());
        return start + "|" + end;
    }


    /**
     * 回去当天00:00:00
     *
     * @return
     */
    public static String getTodayStart() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去七天
        //当天的开始时间，这里之所以三个分开设置，是因为Calendar已经存在日期
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        //第一个时间格式化
        String start = format.format(c.getTime());
        return start;
    }

    public static String getTodayNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }
    public static String getTodayEnd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }
    public static String getYesterDayEnd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    public static String getDayAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去1天
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.DATE, -1);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    /**
     * 获取当月开始日期 00:00:00
     * @return
     */
    public static String getMonthStart() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date d = calendar.getTime();
        String day = format.format(d);
        return day;
    }
    /**
     * 获取当月最后一天日期 23:59:59
     * @return
     */
    public static String getMonthEnd() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        //当天的结束时间
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date d = cal.getTime();
        String day = format.format(d);
        return day;
    }

    public static String getWeekAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();

        //过去七天
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.DATE, -6);
        Date d = c.getTime();
        String day = format.format(d);
        return day;
    }

    public static String getMonthAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去一月
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon;
    }

    public static String getHalfYearAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去6个月
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.MONTH, -6);
        Date m3 = c.getTime();
        String mon3 = format.format(m3);
        return mon3;
    }

    public static String getYearAgo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去一年
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        String year = format.format(y);
        return year;
    }

    public static String getYearFirst() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去一年
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_YEAR, 1);// 设置为1号,当前日期既为本月第一天
        Date y = c.getTime();
        String year = format.format(y);
        return year;
    }

    public static String getSelectedDate(int year, int month, int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        //过去一年
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        Date date = c.getTime();
        String dateString = format.format(date);
        return dateString;
    }

    /**
     * 获取当前年月
     *
     * @return
     */
    public static String getYeatMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        return format.format(new Date());
    }

    /**
     *获取一个月的开始日期
     * @param yearMonth 201912
     * @return
     * @throws ParseException
     */
    public static String getMonthBeginDate(String yearMonth) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM dd");//格式化为年月
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(yearMonth+" 10"));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+" 00:00:00";
    }


    /**
     * 获取一个月的结束日期
     * @param yearMonth
     * @return
     * @throws ParseException
     */
    public static String getMonthEndDate(String yearMonth) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM dd");//格式化为年月
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(yearMonth+" 10"));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+" 23:59:59";
    }

    public static Calendar getYearMonthDay(String yearMonth) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化为年月
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(yearMonth));
        return calendar;
    }

    private static final String[] weekday = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    /**
     * 获取日期和星期
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getDateAndDayOfWeek(long date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        int index=calendar.get(Calendar.DAY_OF_WEEK);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+" "+weekday[index-1];
    }


    public static boolean isOneDay(long firstDay, long secondDay){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(new Date(firstDay));
        cal2.setTime(new Date(secondDay));
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}
