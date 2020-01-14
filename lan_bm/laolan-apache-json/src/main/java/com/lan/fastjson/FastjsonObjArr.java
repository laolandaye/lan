package com.lan.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反序列化
 */
public class FastjsonObjArr {

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

    public static void 将Map转成JSONObject() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "One");
        map.put("key2", "Two");

        JSONObject j = new JSONObject(map);

        j.put("key3", "Three");

        System.out.println(j.get("key1"));
        System.out.println(j.get("key2"));
        System.out.println(j.get("key3"));
    }

    public static void 将List对象转成JSONArray() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "One");
        map.put("key2", "Two");

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("key1", "Three");
        map2.put("key2", "Four");

        list.add(map);
        list.add(map2);

        JSONArray j = JSONArray.parseArray(JSON.toJSONString(list));

        for(int i=0; i<j.size(); i++){
            System.out.println(j.get(i));
        }
    }

    public static void main(String[] args) {
        将Map转成JSONObject();
        将List对象转成JSONArray();

    }
}
