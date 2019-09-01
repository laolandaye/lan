package com.lan.bmwai.bm.jmrh;

import net.sf.json.JSONArray;
import test.bm.htjd.utils.GPSUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JmrhUtils {


    // List<Map 数组中取取随机值
    public static Object getRandomList(List<Map<String, Object>> listMap, String key) {
        List<Object> list = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            list.add(map.get(key));
        }
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);

    }

    //获得多边形中心 经纬
    public static String getCenterLnglat(String paths) {
        //处理坐标
        Object [][] c = (Object [][]) JSONArray.toArray(JSONArray.fromObject(paths));
        List<Double> lngs = new ArrayList<>();
        List<Double> lats = new ArrayList<>();
        for (Object[] objects : c) {
            lngs.add(Double.valueOf(objects[0].toString()));
            lats.add(Double.valueOf(objects[1].toString()));
        }
        return "[" + (Collections.min(lngs) + Collections.max(lngs))/2 +
                "," +
                (Collections.min(lats) + Collections.max(lats))/2 + "]";
    }

    // GPs 转高德
    public static List<double []> getGaodeLnglat2(String paths) {
        List<double []> result = new ArrayList<>();
        //处理坐标
        Object [][] c = (Object [][]) JSONArray.toArray(JSONArray.fromObject(paths));
        for (Object[] objects : c) {
            result.add(GPSUtil.gps84_To_Gcj02(Double.valueOf(objects[1].toString()), Double.valueOf(objects[0].toString())));
        }

        return result;
    }

    /**
     * 4.产生数据整数 含头含尾
     */
    public static int getIntRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }

    /**
     * 生产填报时间2
     */
    public static List<Map<String, Object>> createFillDate(int indexLength) {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = null;
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < indexLength; i++) {
            map = new HashMap<>();
            // 第一个是 增量(时间)
            c.add(Calendar.MONTH, -(indexLength - 1 - i));// 月份减1
            Date resultDate = c.getTime(); // 结果
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            c.setTime(new Date());
            map.put("fill_date", sdf.format(resultDate));

            list.add(map);
        }
        for(Map emp:list){
            System.out.println(emp);
        }
        return list;
    }

    /**
     * 生产填报时间2
     */
    public static List<Map<String, Object>> createFillDate2(String end,int indexLength) {
        List<Map<String, Object>> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Map<String, Object> map = null;
        Calendar c = Calendar.getInstance();

        for (int i = 0; i < indexLength; i++) {
            map = new HashMap<>();
            try {
                c.setTime(sdf.parse(end));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 第一个是 增量(时间)
            c.add(Calendar.MONTH, -(indexLength - i));// 月份减1
            Date resultDate = c.getTime(); // 结果

            c.setTime(new Date());
            map.put("fill_date", sdf.format(resultDate));

            list.add(map);
        }
        for(Map emp:list){
            System.out.println(emp);
        }
        return list;
    }






    public static void main(String[] args) {
    }
}
