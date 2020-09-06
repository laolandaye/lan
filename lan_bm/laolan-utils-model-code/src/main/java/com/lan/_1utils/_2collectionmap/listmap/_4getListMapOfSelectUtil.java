package com.lan._1utils._2collectionmap.listmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 类似数据库的查询列，可多，可少
public class _4getListMapOfSelectUtil {

    /**
     * 抽取listmap需要的字段
     * @return
     */
    public static List<Map<String, Object>>  splitMayByKeys(List<Map<String, Object>> listMap, String[] keys) {
        List<Map<String, Object>> listMapResult = new ArrayList<>();
        String str = "";
        for (Map<String, Object> map : listMap) {
            Map<String, Object> mapResult = new HashMap<>();
            for (int i = 0; i < keys.length; i++) {
                mapResult.put(keys[i], map.get(keys[i]));
            }
            listMapResult.add(mapResult);
        }
        return listMapResult;
    }

    /**
     * 给listmap排序,前提，数据库某一字段已经排序
     */
    public static List<Map<String, Object>>  groupOrderMap(List<Map<String, Object>> listMap, String key) {
        int j = 1;
        for (int i = 0; i < listMap.size(); i++) {
            listMap.get(i).put("pm", i + 1);
            if(i > 0) {
                if(listMap.get(i).get(key).equals(listMap.get(i-1).get(key))) {
                    listMap.get(i).put("pm2", j);
                } else {
                    listMap.get(i).put("pm2", ++j);
                }
            } else {
                listMap.get(i).put("pm2", j);
            }
        }
        return listMap;
    }

}
