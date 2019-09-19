package com.lan._1utils._2collectionmap.listmap;

import com.lan._1utils._2collectionmap.ListUtils;
import com.lan._1utils._2collectionmap.Uppercase4FirstLetter;

import java.text.SimpleDateFormat;
import java.util.*;

public class _2createListMapUtils {

    /**
     * 返回24小时,30/31天，12月
     * @param dateType year，month，hour
     * @param index 统计初值0或1
     * @param keySets map对象，统计值在第一位
     * @param flag 是否根据时间判断 false:1.直接补0,true:2.否则补''
     * @return
     */
    public static List<Map<String, Object>> getListMapByDateTime(
            String dateType, int index, String [] keySets, boolean flag){
        Calendar c = Calendar.getInstance();
        // 默认从0开始，初始index判断从index传入
        int nowDateTime = 0;//当前月、日、时
        int indexLength = 0;//迭代次数
        if("year".equals(dateType)) {
            indexLength = index + 12;
            nowDateTime = index + c.get(Calendar.MONTH);/* 获得当月 */
        } else if("month".equals(dateType)) {
            indexLength = index + c.getActualMaximum(Calendar.DAY_OF_MONTH); /* 当月总天数index */
            nowDateTime = index + c.get(Calendar.DAY_OF_MONTH) - 1;/* 获得当天index */
        } else if("hour".equals(dateType)) {
            indexLength = index + 24;
            nowDateTime = index + c.get(Calendar.HOUR_OF_DAY);/* 获得当前小时index */
        }

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;
        for (int i = index; i < indexLength; i++) {
            map = new HashMap<>();
            // 第一个是 增量(时间)
            map.put(keySets[0], i);
            // keys循环重1开始
            for (int j = 1; j < keySets.length; j++) {
                // 是否使用当前时间false:1.直接补0,true:2.否则补''(再判断当前时间是否超过,超过补'')，
                if(flag && (i > nowDateTime)) {
                    map.put(keySets[j], "");
                } else {
                    map.put(keySets[j], 0);
                }
            }
            list.add(map);
        }
        return list;
    }

    /**
     * （去年到今年）返回24小时,30/31天，12月
     * @param dateType year，month，hour
     * @param keySets map对象，统计值在第一位
     * @param nullStr0 “0” 或者 “”
     * @return
     */
    public static List<Map<String, Object>> getListMapByDateTime(
            String dateType, String [] keySets, String nullStr0){
        Calendar c = Calendar.getInstance();
        // 默认从0开始，初始index判断从index传入
        int nowYear = 0;//当前月、日、时
        int month = 0;//当前月、日、时
        int indexLength = 0;//迭代次数
        if("year".equals(dateType)) {
            indexLength = 12;
        } else if("month".equals(dateType)) {
        } else if("hour".equals(dateType)) {
        }

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;
        String key = "";
        for (int i = 0; i < indexLength; i++) {
            map = new HashMap<>();
            // 第一个是 增量(时间)
            c.add(Calendar.MONTH, -(indexLength - 1 - i));// 月份减1
            Date resultDate = c.getTime(); // 结果
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            c.setTime(new Date());
            map.put(keySets[0], sdf.format(resultDate));
            // keys循环重1开始
            for (int j = 1; j < keySets.length; j++) {
                map.put(keySets[j], nullStr0);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 指定时间段 数据 (月)
     * @param begin yyyy-mm
     * @param end yyyy-mm
     * @param keySets map对象，统计值在第一位
     * @param nullStr0 “0” 或者 “”
     * @return
     */
    public static List<Map<String, Object>> getListMapOfMonth(
            String begin, String end, String [] keySets, String nullStr0){
        List<Map<String, Object>> result = new ArrayList<>();

        List<String> months = ListUtils.getMonthBetween(begin, end, "3");
        for (String month : months) {
            Map<String, Object> map = new HashMap<>();
            map.put(keySets[0], month);
            // keys循环重1开始
            for (int j = 1; j < keySets.length; j++) {
                map.put(keySets[j], nullStr0);
            }
            result.add(map);
        }
        return result;
    }

}
