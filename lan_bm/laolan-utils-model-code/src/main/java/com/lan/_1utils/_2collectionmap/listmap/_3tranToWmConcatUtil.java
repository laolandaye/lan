package com.lan._1utils._2collectionmap.listmap;

import java.util.*;

public class _3tranToWmConcatUtil {

    /**
     * wm_concat 结合使用，一个（对象集合） =》 listMap
     * @param str 数据库查询结果
     * @param keys  需要的键
     * @param regex 对象的分割符号 “~”
     * @return
     */
    public static List<Map<String, Object>> tranToWmConcat(String str, String[] keys, String regex) {
        String [] strArray = str.split(",");
        List<Map<String, Object>> listMap = new ArrayList<>();
        String [] strArray2 = null;
        Map<String, Object> map = null;
        for (int i = 0; i < strArray.length; i++) {
            strArray2 = strArray[i].split(regex);
            map = new HashMap<>();
            for (int j = 0; j < strArray2.length; j++) {
                map.put(keys[j], strArray2[j]);
            }
            listMap.add(map);
        }
        return listMap;
    }

    /**
     * wm_concat 结合使用，多个（对象集合，其中一个属性是） =》 listMap
     * @param listMap
     * @param keys {"company", "type", "car"};  最后一个是要分割属性
     * @param regex wm_concat要分割的对象分隔符 "~"
     * @param splitKeys wm_concat要分割的对象keys {"car", "carNo"};
     * @return
     */
    public static List<Map<String, Object>> tranToMulWmConcat(List<Map<String, Object>> listMap, String[] keys, String regex, String[] splitKeys) {
        List<Map<String, Object>> listMapResult = new ArrayList<>();
        String str = "";
        String [] strArray = null;
        String [] strArray2 = null;
        Map<String, Object> mapResult = null;
        for (Map<String, Object> map : listMap) {
            str = map.get(keys[keys.length - 1]).toString();
            strArray = str.split(",");
            for (int i = 0; i < strArray.length; i++) {
                // 添加split 分离的map
                strArray2 = strArray[i].split(regex);
                mapResult = new HashMap<>();
                for (int j = 0; j < strArray2.length; j++) {
                    mapResult.put(splitKeys[j], strArray2[j]);
                }
                // 添加外部map,不取最后一个
                for(int k = 0; k < (keys.length - 1); k++) {
                    mapResult.put(keys[k], map.get(keys[k]).toString());
                }
                listMapResult.add(mapResult);
            }
        }
        return listMapResult;
    }

    /**
     * wm_concat 结合使用，多个（对象集合，其中一个属性是） =》 listMap  结果根据需求驼峰式
     * @param listMap
     * @param keys {"company", "type", "car"};  最后一个是要分割属性
     * @param keysResult {"company", "type", "car"};  最后一个是要分割属性 : 用于换成驼峰式
     * @param regex wm_concat要分割的对象分隔符 "~"
     * @param splitKeys wm_concat要分割的对象keys {"car", "carNo"};
     * @return
     */
    public static List<Map<String, Object>> tranToMulWmConcat(List<Map<String, Object>> listMap, String[] keys, String [] keysResult, String regex, String[] splitKeys) {
        List<Map<String, Object>> listMapResult = new ArrayList<>();
        String str = "";
        String [] strArray = null;
        String [] strArray2 = null;
        Map<String, Object> mapResult = null;
        for (Map<String, Object> map : listMap) {
            str = map.get(keys[keys.length - 1]).toString();
            strArray = str.split(",");
            for (int i = 0; i < strArray.length; i++) {
                // 添加split 分离的map
                strArray2 = strArray[i].split(regex);
                mapResult = new HashMap<>();
                for (int j = 0; j < strArray2.length; j++) {
                    mapResult.put(splitKeys[j], strArray2[j]);
                }
                // 添加外部map,不取最后一个
                for(int k = 0; k < (keys.length - 1); k++) {
                    mapResult.put(keysResult[k], map.get(keys[k]).toString());
                }
                listMapResult.add(mapResult);
            }
        }
        return listMapResult;
    }

}
