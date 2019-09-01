package com.lan._1utils._1str;

import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 返回结果都是字符串的 ”,"
 */
public class _1OrclUtils {

    // 1. 转换成 oral in,并去掉空格
    /**
     * 1.1 数组 [1,2,3]  --> "'1','2','3'"
     * @param arr
     * @return
     */
    public static String arrToOrclInStr(Object [] arr) {
        if(arr != null && arr.length > 0) {
            StringBuffer sbFormat = new StringBuffer();
            for(int i = 0; i < arr.length; i++) {
                sbFormat.append("'");
                sbFormat.append(arr[i].toString().trim());
                sbFormat.append("'");
                //只有一个元素不走这里
                if(i < (arr.length - 1)) {
                    sbFormat.append(",");
                }
            }
            return sbFormat.toString();
        } else {
            return "";
        }
    }

    /**
     * 1.2.集合
     * @param list
     * @return
     */
    public static String listToOrclInStr(List<String> list) {
        return arrToOrclInStr(list.toArray());
    }

    /**
     * 1.3 字符串 "1,2,3"  --> "'1','2','3'"
     * @param str
     * @return
     */
    public static String strToOrclInStr(String str) {
        String [] stringArr  = str.split("\\,");
        if(stringArr != null && stringArr.length > 0) {
            return arrToOrclInStr(stringArr);
        } else {
            return "";
        }
    }

    /**
     * 1.4.集合listMap
     * @param mapList
     * @param key
     * @return
     */
    public static String listMapToOrclInStr(List<Map<String, Object>> mapList, String key) {
        StringBuffer customStr = new StringBuffer();
        if (mapList != null && mapList.size() > 0) {
            for (int i = 0; i < mapList.size(); i++) {
                customStr.append("'");
                customStr.append(mapList.get(i).get(key).toString().trim());
                customStr.append("'");
                //只有一个元素不走这里
                if(i < (mapList.size() - 1)) {
                    customStr.append(",");
                }
            }
            return customStr.toString();
        } else {
            return "";
        }
    }

    /**
     * 1.5.listObj没必要封装 ， 使用jdk 1.8 加上listToOrclInStr
     * @param mapList
     * @param key
     * @return
     */

    /**
     * json数据格式 JSON.stringify(self.searchForm.qkId)
     */
    public static String jsonArrToOrclInStr(String str) {
        if(StringUtils.isNotBlank(str)) {
            JSONArray jsonArray = JSONArray.fromObject(str);
            List<String> result = new ArrayList<>();
            if(jsonArray.size() > 0) {
                for (Object o : jsonArray) {
                    result.add((String) o);
                }
                return listToOrclInStr(result);
            } else {
                return "''";
            }
        } else {
            return "";
        }

    }

}

