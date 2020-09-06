package com.lan._1utils._2collectionmap.listmap;

import com.lan._1utils._2collectionmap.Uppercase4FirstLetter;

import java.util.*;

// 驼峰式
public class _4getListMapOfCamelUtil {

    /**
     *  用于将改成驼峰式（手动转换）
     * @param listMap
     * @param keys  {"collect_month", "totalnum"};
     * @param keysResult    {"collectMonth", "totalNum"};  一一对应
     * @return
     */
    @Deprecated
    public static List<Map<String, Object>> changeMapKeys(List<Map<String, Object>> listMap, String [] keys, String [] keysResult ) {
        for (Map<String, Object> map : listMap) {
            for (int i = 0; i < map.size(); i++) {
                map.put(keysResult[i], map.get(keys[i]));//添加新key
                map.remove(keys[i]);//删除老key
            }
        }
        return listMap;
    }

    /**
     *  用于将改成驼峰式2
     * @param listMap
     * @return
     */
    public static List<Map<String, Object>> changeMapKeys(List<Map<String, Object>> listMap) {
        if(listMap.size() == 0) {
            return new ArrayList<>();
        }
        String [] keys = new String[listMap.get(0).keySet().size()];
        String [] keysResult = new String[listMap.get(0).keySet().size()];
        List<String> list = new ArrayList<>(listMap.get(0).keySet());
        for (int i = 0; i < list.size(); i++) {
            keys[i] = list.get(i);
        }
        keysResult = Uppercase4FirstLetter.convertToJava(keys);

        List<Map<String, Object>> listMapResult = new ArrayList<>();
        Map<String, Object> mapResult = null;
        for (Map<String, Object> map : listMap) {
            mapResult = new HashMap<>();
            for (int i = 0; i < map.size(); i++) {
                mapResult.put(keysResult[i], map.get(keys[i]));//添加新key
            }
            listMapResult.add(mapResult);
        }
        listMap = null;
        return listMapResult;
    }

    /**
     *  用于将改成驼峰式< 无返回值，用引用地址 这里只在内部修改，不做返回值
     * @param listMap
     */
    public static void getListMapOfUpper(List<Map<String, Object>> listMap) {
        if(listMap.size() == 0) {
            return;
        }

        // 映射
        Map<String, Object> keyMapping = new HashMap<>();
        // 只去循环一次，获得key值
        List<String> list = new ArrayList<>(listMap.get(0).keySet());
        for (int i = 0; i < list.size(); i++) {
            keyMapping.put(list.get(i), Uppercase4FirstLetter.toUppercase4FirstLetter(list.get(i)));
        }

        // 第一次 添加驼峰key
        for (Map<String, Object> map : listMap) {
            Iterator<Map.Entry<String, Object>> iter = map.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry<String, Object> entry = iter.next();
                String key = entry.getKey();
                // 如果映射是一样的key，就不必删除
                if(key.equals(keyMapping.get(key).toString())) {
                } else {
                    map.put(keyMapping.get(key).toString(), entry.getValue());
                }
            }
        }

        // 第二次循环删除 数据库key
        for (Map<String, Object> map : listMap) {
            Iterator<Map.Entry<String, Object>> iter = map.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry<String, Object> entry = iter.next();
                String key = entry.getKey();
                if(keyMapping.containsKey(key)) {
                    // 如果映射是一样的key，就不必删除
                    if (key.equals(keyMapping.get(key).toString())) {
                    } else {
                        iter.remove();
                    }
                }
            }
        }
    }



}
