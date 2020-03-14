package com.lan._1utils._2collectionmap.listmap;

import com.lan._1utils._2collectionmap.ListUtils;
import com.lan._1utils._2collectionmap.Uppercase4FirstLetter;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

// 得到element需要的结构
public class _3getToElementUtils {

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
     *  参看：https://www.cnblogs.com/1955/p/9844694.html
     * @param datas 源数据
     * @param res   结果数据
     * @param keys 判断的 ["id", "parent_id" ]
     */
    public static void getElementTree(List<Map<String, Object>> datas, List<Map<String, Object>> res, String [] keys) {
        for (Map<String, Object> data :datas) {
            // parent_id 为空的是一级节点
            Object parentId = data.get(keys[1]);
            if(parentId == null || "".equalsIgnoreCase(parentId.toString())) {
                res.add(data);
            }
        }
        data2treeDG(datas, res, keys);
    }

    private static void data2treeDG(List<Map<String, Object>> datas, List<Map<String, Object>> res, String [] keys) {
        for (Map<String, Object> re : res) {
            List<Map<String, Object>> children = new ArrayList<>();
            for (Map<String, Object> data : datas) {
                if(re.get(keys[0]).equals(data.get(keys[1]))) {
                    children.add(data);
                }
            }
            // 如果有儿子，存在递归儿子节点
            if(children.size() > 0) {
//                re.put("hasChildren", true);
                re.put("children", children);
                data2treeDG(datas, children, keys);
            }
        }
    }

    /**
     * 根根据 id 只获得儿子孙子
     * @param id 条件 id
     * @param datas  源数据
     * @param res  结果数据
     * @param keys 判断的 ["id", "parent_id" ]
     */
    public static void getElementTreeByIdExtend(String id, List<Map<String, Object>> datas, List<Map<String, Object>> res, String [] keys) {
        List<Map<String, Object>> idInfo = new ArrayList<>();
        // 根据 id 获得此 id 信息
        for (Map<String, Object> data : datas) {
            if(data.get(keys[0]).equals(id)) {
                idInfo.add(data);
            }
        }
        // 递归获得 id 后代信息
        data2treeDGById(idInfo,  datas, res, keys);
    }

    /**
     * 根根据 id 获得本身，及其儿子孙子
     * @param id 条件 id
     * @param datas  源数据
     * @param res  结果数据
     * @param keys 判断的 ["id", "parent_id" ]
     */
    public static void getElementTreeById(String id, List<Map<String, Object>> datas, List<Map<String, Object>> res, String [] keys) {
        List<Map<String, Object>> idInfo = new ArrayList<>();
        // 根据 id 获得此 id 信息
        for (Map<String, Object> data : datas) {
            if(data.get(keys[0]).equals(id)) {
                idInfo.add(data);
                res.add(data);
            }
        }
        // 递归获得 id 后代信息
        data2treeDGById(idInfo,  datas, res, keys);
    }

    private static void data2treeDGById(List<Map<String, Object>> idChildren,List<Map<String, Object>> datas, List<Map<String, Object>> res, String [] keys) {
        for (Map<String, Object> re : idChildren) {
            List<Map<String, Object>> children = new ArrayList<>();
            for (Map<String, Object> data : datas) {
                // parent_id 为空的是一级节点
                Object parentId = data.get(keys[1]);
                if(!(parentId != null || "".equalsIgnoreCase(parentId.toString()))) {
                    if(re.get(keys[0]).equals(data.get(keys[1]))) {
                        res.add(data);
                        children.add(data);
                    }
                }
            }
            if(children.size() > 0) {
                data2treeDGById(children, datas, res, keys);
            }
        }
    }

}
