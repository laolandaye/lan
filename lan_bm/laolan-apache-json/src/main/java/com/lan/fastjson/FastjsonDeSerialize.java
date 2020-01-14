package com.lan.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lan.fastjson.User;

import java.util.*;

/**
 * 反序列化
 */
public class FastjsonDeSerialize {

    private static String mapJson;
    private static String listJson;
    private static String userJson;

    static {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "One");
        map.put("key2", "Two");
        mapJson = JSON.toJSONString(map);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("key1", "One");
        map1.put("key2", "Two");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("key1", "Three");
        map2.put("key2", "Four");
        list.add(map1);
        list.add(map2);
        listJson = JSON.toJSONString(list);

        User user = new User();
        user.setUserName("李四");
        user.setAge(24);
        userJson = JSON.toJSONString(user);
    }

    // 指定Class信息反序列化
    public static void 指定Class信息反序列化() {
        User user1 = JSON.parseObject(userJson, User.class);
        System.out.println(user1.getUserName());
    }

    // 集合反序列化
    public static void 集合反序列化() {
        List<Map> list1 = JSON.parseArray(listJson, Map.class);
        for(Map<String, Object> map : list1){
            System.out.println(map.get("key1"));
            System.out.println(map.get("key2"));
        }
    }

    // 泛型的反序列化（使用TypeReference传入类型信息）
    public static void 泛型的反序列化() {
        Map<String, Object> map1 = JSON.parseObject(mapJson, new TypeReference<Map<String, Object>>(){});
        System.out.println(map1.get("key1"));
        System.out.println(map1.get("key2"));
    }

    public static void main(String[] args) {
        指定Class信息反序列化();

        集合反序列化();

        泛型的反序列化();
    }
}
