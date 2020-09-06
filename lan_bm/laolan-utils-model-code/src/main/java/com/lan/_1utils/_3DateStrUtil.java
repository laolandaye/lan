package com.lan._1utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * 存储常用日期操作
 */
public class _3DateStrUtil {

    /**
     * 获得当前时间
     */
    public static Date getNowDate() {
        // 方式一
        Date now = new Date();
        // 方式二
        // Calendar calendar = Calendar.getInstance();
        // Date now = calendar.getTime();
        return now;
    }

    public static String getNowDateStr() {
        LocalDate date = LocalDate.now(); // get the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    /**
     * 日期 转换
     */
    public static Date getParseDate(String str) {
        //str表示yyyy年MM月dd HH:mm:ss格式字符串
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
            return date;
        } catch (ParseException e) {
            System.out.println("不能转换时间");
            e.printStackTrace();
            return date;
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *   https://www.iteye.com/blog/takeme-1678179
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static Long daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Long.parseLong(String.valueOf(between_days));
    }

    public static Long daysBetween(String smdate, String bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(smdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time1 = cal.getTimeInMillis();
        try {
            cal.setTime(sdf.parse(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Long.parseLong(String.valueOf(between_days));
    }

    /**
     *  根据类型获取到，返回当前时间，到 当小时，当天，当周，当月，当季度，当年 集合的秒数
     * @param dateType
     * @return 秒数
     */
    public static Long getNumByType(String dateType) {
        if(StringUtils.isBlank(dateType)) return 0l;
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime endTime = null;
        switch (dateType) {
            case "year":
                // 获得当年结束时间
                endTime = nowTime.with(TemporalAdjusters.lastDayOfYear());
                break;
            case "month":
                // 获得当月结束时间
                endTime = nowTime.with(TemporalAdjusters.lastDayOfMonth());
                break;
            case "day":
                // 获得当天结束时间
                endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
                break;
            default:
                break;
        }
        Duration duration = Duration.between(nowTime, endTime);
        return duration.getSeconds();
    }

    /**
     * 功能描述: 根据日期获取周几
     * https://blog.csdn.net/qq_37511501/article/details/80193097
     */
    public static String getWeekOfDate(String Str) {
        return getWeekOfDate(getParseDate(Str));
    }

    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}
