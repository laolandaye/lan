package com.lan._1utils._2collectionmap.listmap;

import java.util.*;

/**
 * 实际是left jion
 */
public class _1mergeListMapUtil {

    // 目前只是支持一对一，多对一的映射关系

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

}
