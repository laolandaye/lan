package com.lan.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

/**
 * 序列化
 */
public class FastjsonSerialize {

    private static Map<String, Object> map;
    private static List<Map<String, Object>> list;
    private static User user;

    static {
        map = new HashMap<String, Object>();
        map.put("key1", "One");
        map.put("key2", "Two");

        list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("key1", "One");
        map1.put("key2", "Two");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("key1", "Three");
        map2.put("key2", "Four");
        list.add(map1);
        list.add(map2);

        user = new User();
        user.setUserName("李四");
        user.setAge(24);
    }

    // 基本序列化
    public static void 基本序列化() {
        // 例1：将Map转成JSON
        String mapJson = JSON.toJSONString(map);

        // 例2：将List<Map>转成JSON。
        String listJson = JSON.toJSONString(list);

        // 例3：自定义JavaBean User转成JSON。
        String userJson = JSON.toJSONString(user);
    }

    // JSON格式化
    public static void JSON格式化booelean() {
        // 例8：以例2为例。
        String listJson = JSON.toJSONString(list, true);
        System.out.println(listJson);
    }

    // 日期格式化
    public static void 日期格式化() {
        // 例5：FastJSON将java.util.Date转成long。
        String dateJson = JSON.toJSONString(new Date());
        System.out.println(dateJson);

        // 例6：使用SerializerFeature特性格式化日期。
        String dateJson2 = JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat);
        System.out.println(dateJson2);

        // 例7：指定输出日期格式。
        String dateJson3 = JSON.toJSONStringWithDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(dateJson3);
    }

    // 使用单引号
    public static void 使用单引号() {
        // 例8：以例2为例。
        String listJson = JSON.toJSONString(list, SerializerFeature.UseSingleQuotes);
        System.out.println(listJson);
    }

    // JSON格式化
    public static void JSON格式化() {
        // 例8：以例2为例。
        String listJson = JSON.toJSONString(list, SerializerFeature.PrettyFormat);
        System.out.println(listJson);
    }

    // 输出Null字段
    public static void 输出Null字段() {
        // 例8：以例2为例。
        Map<String, Object> map = new HashMap<String,Object>();

        String b = null;
        Integer i = 1;

        map.put("a", b);
        map.put("b", i);

        String listJson = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    // 序列化是写入类型信息
    public static void 序列化是写入类型信息() {
        User user = new User();

        user.setAge(18);
        user.setUserName("李四");

        String listJson = JSON.toJSONString(user, SerializerFeature.WriteClassName);
    }

    public static void main(String[] args) {
//        基本序列化();

//        JSON格式化booelean();

//        日期格式化();
//        使用单引号();
//        JSON格式化();
//        输出Null字段();
        序列化是写入类型信息();
    }
}
