package com.lan.fastjson.util.驼峰式;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Uppercase4FirstLetter {


    public static void main(String[] args) {
        Uppercase4FirstLetter u = new Uppercase4FirstLetter();

        //testing for data base column-key to java field
        String[] dbKeys = {"id", "user_age", "user_addr"};
        String[] aaa  = convertToJava(dbKeys);
        System.out.println("-----------------------------------");
        //testing for data base column-key to java field
        String dbKey = "USER_ADDR", dbKey2 = "USER", dbKey3 = "USER_ADDR_HOME";
        System.out.println(convertToJava(dbKey) + "  " + convertToJava(dbKey2) + "  "  + convertToJava(dbKey3));
        System.out.println("-----------------------------------");
        //testing for Java field to data base column-key
        String javaFieldNames[] = {"id","userAge","userHomeAddr"};
        u.getDBKey(javaFieldNames);
    }


    /*
     * Java field to data base column-key
     */
    private void getDBKey(String... javaFieldNames){
        if(javaFieldNames != null && javaFieldNames.length > 0){
            for(String name : javaFieldNames){
                StringBuffer buffer = new StringBuffer();
                char[] array = name.toCharArray();
                List<Integer> insertIndexes = new ArrayList<>();
                for(int i=0;i<array.length;i++){
                    Character c = array[i];
                    if(i != 0 && Character.isUpperCase(c)){
                        insertIndexes.add(i);
                    }
                }
                if(insertIndexes.size() > 0){
                    int flag = 0;
                    for(int j=0;j<insertIndexes.size();j++){
                        String word = toLowercase4FirstLetter(name.substring(flag, insertIndexes.get(j)));
                        buffer.append(word).append("_");
                        flag = insertIndexes.get(j);
                    }
                    String last = toLowercase4FirstLetter(name.substring(flag));
                    buffer.append(last);
                    System.out.println(buffer.toString());
                } else {
                    System.out.println(name);
                }
            }
        }
    }

    private String toLowercase4FirstLetter(String word){
        if(word != null && word.length() > 0){
            String firstLetter = word.substring(0,1);
            String others = word.substring(1);
            return firstLetter.toLowerCase() + others;
        }else{
            return "";
        }
    }

    /*
     * 一行字段 grid_code  =》 id， gridCode
     */
    public static String convertToJava(String dbKey) {
        String result = "";
        if(StringUtils.isNotBlank(dbKey)) {
            String[] words = dbKey.split("_");
            result = toUppercase4FirstLetter(words);
        }
        return result;
    }

    /*
     * 一行字段 [id ， grid_code]  =》 { id， gridCode}
     */
    public static String [] convertToJava(String... dbKeys) {
        String [] results = {};
        if(dbKeys != null && dbKeys.length > 0){
            results = new String[dbKeys.length];
            for (int i = 0; i < dbKeys.length; i++) {
                String[] words = dbKeys[i].split("_");
                results[i] = toUppercase4FirstLetter(words);
            }
        }
        return  results;
    }

    /*
     * 一个字段的String  转换成 驼峰式 key_ass => [key, ass] =>  keyAss
     */
    public static String toUppercase4FirstLetter(String key) {
        if(key != null && "".equals(key)) {
            return "";
        }
        String[] words = key.split("_");
        return toUppercase4FirstLetter(words);
    }

    // 一个字段的String [] 转换成 驼峰式 [key, ass] =>  keyAss
    private static String toUppercase4FirstLetter(String... words){
        StringBuffer buffer = new StringBuffer();
        if(words != null && words.length > 0){
            for(int i=0;i<words.length;i++){
                String word = words[i].toLowerCase();
                String firstLetter = word.substring(0, 1);
                String others = word.substring(1);
                String upperLetter = null;
                if(i != 0){
                    upperLetter = firstLetter.toUpperCase();
                } else {
                    upperLetter = firstLetter;
                }
                buffer.append(upperLetter).append(others);
            }
            return buffer.toString();
        }
        return "";
    }


}
