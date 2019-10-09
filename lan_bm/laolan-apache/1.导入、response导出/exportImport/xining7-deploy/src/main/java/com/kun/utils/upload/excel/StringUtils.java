package com.kun.utils.upload.excel;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
 
 
public class StringUtils {
 
     
    public static String replaceInfo(String info, Map<String, String> map) {
        for (Entry<String, String> entity : map.entrySet()) {
            info = info.replace(entity.getKey(), entity.getValue());
        }
        return info;
    }
    /**
     *
     * @return 获取一个唯一的uuid字符串
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
    public static String getFristUpper(String str){
        return str.substring(0,1).toUpperCase()+str.substring(1);
    }
     
    public static String format(String str, Object... args) {
        if (str == null || args == null || args.length <= 0)
            return null;
        for (int i = 0; i < args.length; i++) {
            str=str.replace(String.format("{%s}", i), args[i].toString());
        }
        return str;
    }
 
 
 
 
    /**
     * @param str
     * @return
     */
    public static String getMarkString(String str) {
        return String.format("'%s'", str);
    }
    public static String getMarkString(String str,String mark){
        return mark+str+mark;
    }
     
     
    /**
     *  替换   '   为   ''
     * @param str
     * @return
     */
    public static String replaceMark(String str) {
        return str.replaceAll("'", "''");
    }
 
    /**
     * 是否为 int
     * @param str
     * @return
     */
    public static boolean isInt(String str) {
        if (isNullOrEmpty(str))
            return false;
        String regex = "^\\d+$";
        return str.matches(regex);
    }
    /**
     * 是否为
     * @param Double
     * @return
     */
    public static boolean isDouble(String str) {
        if (isNullOrEmpty(str))
            return false;
        String regex = "\\d{1,}\\.\\d{1,}";
        return str.matches(regex);
    }
    /**
     * 是否为  null 获取 ""
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
 
    /**
     * 字符串转int 字符不是int 返回 0
     * @param
     */
    public static Integer getInt(String str) {
        if (isInt(str))
            return Integer.valueOf(str);
        else
            return 0;
    }
    /**
     * 字符串转Double 字符不是Double 返回 null
     * @param
     */
    public static Double getDouble(String str) {
        if (isDouble(str))
            return Double.valueOf(str);
        else
            return null;
    }
 
    /**
     *
     * @param MD5
     * @return
     * 
     */
    public static String getMD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
    //将id字符串拆分成List<Integer>
    public static List<Integer> string2List(String str,String splitType){
        if(str==null&&"".equals(str)){
            return null;
        }
        List<Integer> list=new ArrayList<Integer>();
        String [] ids=str.split(splitType);
        for(String temp:ids){
            list.add(Integer.parseInt(temp));
        }
        return list;
    }
 
}