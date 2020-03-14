package com.lan._1utils._2collectionmap;

import com.lan._1utils._3date.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ListMapUtils {

    /**
     * （补充字段进入）将两个小map根据相同属性（eg:id）合并成一个大map; 将数据库的left join 用java处理
     * @param listMapOut 类似left join的左边，也是得到结果的地方（原理：引用类型）
     * @param listMapIn
     * @param id 相同属性
     * @param mapInExId 不匹配时，其他属性的初值（mapIn的空value）
     */
    public static void mergeListMapLeft(
            List<Map<String, Object>> listMapOut,
            List<Map<String, Object>> listMapIn,
            String id,
            Map<String, Object> mapInExId
    ) {
        String idOut = "";
        String idIn = "";
        lableB:	for (Map<String, Object> mapOut : listMapOut) {
            idOut = mapOut.get(id).toString();
            for (Map<String, Object> mapIn : listMapIn) {
                idIn = mapIn.get(id).toString();
                //内外唯一id比较
                if (idOut.equals(idIn)) {
                    mapOut.putAll(mapIn);
                    continue lableB;//满足条件后跳出双层循环进入下一次
                }
            }
            //所有都不满足，赋值为，外层进入下一次
            // 不能包含所比较字段id
            mapOut.putAll(mapInExId);
        }
    }

    /**
     * （不需要补充字段进入的）
     * @param listMapOut 类似left join的左边，也是得到结果的地方（原理：引用类型）
     * @param listMapIn
     * @param id 相同属性
     */
    public static void mergeListMapLeft(
            List<Map<String, Object>> listMapOut,
            List<Map<String, Object>> listMapIn,
            String id
    ) {
        String idOut = "";
        String idIn = "";
        lableB:	for (Map<String, Object> mapOut : listMapOut) {
            idOut = mapOut.get(id).toString();
            for (Map<String, Object> mapIn : listMapIn) {
                idIn = mapIn.get(id).toString();
                //内外唯一id比较
                if (idOut.equals(idIn)) {
                    mapOut.putAll(mapIn);
                    continue lableB;//满足条件后跳出双层循环进入下一次
                }
            }
        }
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
     * wm_concat 结合使用，一个（对象集合） =》 listMap
     * @param str 数据库查询结果
     * @param keys  需要的键
     * @param regex 对象的分割符号 “~”
     * @return
     */
    public static List<Map<String, Object>> changeSplitMapKeys(String str, String[] keys, String regex) {
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
    public static List<Map<String, Object>> changeSplitListMapKeys(List<Map<String, Object>> listMap, String[] keys, String regex, String[] splitKeys) {
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
    public static List<Map<String, Object>> changeSplitListMapKeys(List<Map<String, Object>> listMap, String[] keys, String [] keysResult, String regex, String[] splitKeys) {
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

    public static void main(String[] args) {

    }
}
