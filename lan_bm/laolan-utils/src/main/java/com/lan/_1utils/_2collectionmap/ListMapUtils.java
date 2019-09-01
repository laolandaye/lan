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


    /**
     * 返回element需要生成的树(生成自带id)
     * @param ls
     * @return
     */
    public static List<Map<String, Object>> getElementTree(List<Map<String, Object>> ls, String [] keys) {
        /**
         * first 第一次循环 ； one第一层 ；label ；children
         * labelOnes 第一层label集合
         * mapFirst 第一次循环map
         * firstLabelOne 第一次循环的第一层label
         * mapOne 结果第一层
         * childrenOnes 第一层children集合
         * labelTwos 第二层label集合
         * mapTwos 第二次循环map
         */
        List<Map<String, Object>> results = new ArrayList<>();//结果集合
        List<String> labelOnes = new ArrayList<>();  //第一级数据结果集合

        // 双层循环得到结果
        //一.第一次循环 ls
        int indexId = 1;
        for (int i = 0; i < ls.size(); i++) {
            // 判断第一层 label 是否在存在labelOnes
            Map<String, Object> mapFirst = ls.get(i);
            String firstLabelOne = mapFirst.get(keys[0]).toString();
            //如果一级目录label已经存在，直接跳过，否则添加到一级目录集合
            if (labelOnes.contains(firstLabelOne)) {
                continue;
            } else {
                labelOnes.add(firstLabelOne);

                Map<String, Object> mapOne = new HashMap<>();//一级目录对象｛label, children｝
                // 3.1 添加一级目录label
                mapOne.put("id", ++indexId);
                mapOne.put("label", firstLabelOne);
                // 3.2 添加一级目录 children
                List<Map<String, Object>> childrenOnes = new ArrayList<>();

                List<String> labelTwos = new ArrayList<>();// 第二级数据label集合
                //二.第二次循环 ls No2：添加一级目录 children
                for (int j = 0; j < ls.size(); j++) {
                    Map<String, Object> mapTwos = ls.get(j);//
                    String firstLabelTwo = mapTwos.get(keys[0]).toString();
                    //第二次循环和第一次循环，比较
                    if (firstLabelOne.equals(firstLabelTwo)) {
                        Integer index = null;
                        Map<String, Object> mapLevel2 = new HashMap<>();
                        String labelNo2Level2 = (String) mapTwos.get(keys[1]);

                        List<Map<String, Object>> mapLevel3 = new ArrayList<Map<String, Object>>();
                        if (labelTwos.contains(labelNo2Level2)) {
                            for (int k = 0; k < childrenOnes.size(); k++) {
                                Map<String, Object> childrenLevel1 = childrenOnes.get(k);
                                String dbname1 = (String) childrenLevel1.get("label");
                                if (dbname1.equals(labelNo2Level2)) {
                                    mapLevel2 = childrenLevel1;
                                    mapLevel3 = (List<Map<String, Object>>) mapLevel2.get("children");
                                    index = k;
                                    break;
                                }
                            }
                        } else {
                            mapLevel2.put("id",  ++indexId);
                            mapLevel2.put("label", labelNo2Level2);
                        }
                        Map<String, Object> userM = new HashMap<String, Object>();
                        userM.put("id",  ++indexId);
                        userM.put("label", mapTwos.get(keys[2]).toString());
                        for(int x = 2; x < keys.length; x++) {
                            userM.put(keys[x],  mapTwos.get(keys[x]).toString());
                        }
                        mapLevel3.add(userM);
                        mapLevel2.put("children", mapLevel3);
                        if (index == null) {
                            childrenOnes.add(mapLevel2);
                        } else {
                            childrenOnes.set(index, mapLevel2);
                        }

                        labelTwos.add(labelNo2Level2);//添加到二级目录集合
                    }
                }
                mapOne.put("children", childrenOnes);

                results.add(mapOne);// 添加到结果集合
            }
        }
        //将ls，keys清空，释放内存
        ls = null;
        keys = null;
        return results;
    }

    /**
     *  用于将改成驼峰式
     * @param listMap
     * @param keys  {"collect_month", "totalnum"};
     * @param keysResult    {"collectMonth", "totalNum"};  一一对应
     * @return
     */
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
