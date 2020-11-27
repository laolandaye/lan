package com.lan.javase._11反射.反射注解应用;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class CommonCreator {

    public static void main(String[] args) {

    }

    Map<String, Object> tableFields = new HashMap<>();

    Map<String, Map<String, String>> dimCodesMap = new HashMap<>();

    public String createSQL(Ather content) {
        if(tableFields.isEmpty()) {
            this.setTableFields(content);
        }
        String sql = this.saveModel(content, tableFields);
        return sql;
    }

    private void setTableFields(Object tosave) {
        Class<?> aClass = tosave.getClass();
        TableName annotation = aClass.getAnnotation(TableName.class);
        String table = annotation.value();
        String sy = Table2Sy.findSyByName(table);
        List<Field> fields = new ArrayList<>();
        while (aClass != null) {
            fields.addAll(Arrays.asList(aClass.getDeclaredFields()));
            aClass = aClass.getSuperclass();
        }
        tableFields.put(table, fields);
        tableFields.put("sy", sy);
    }

    private String saveModel(Object tosave, Map<String, Object> tableFields) {
        Iterator<String> iterator = tableFields.keySet().iterator();
        List<String> sy = new ArrayList<>();
        String table = "";
        while (iterator.hasNext()) {
            String key = iterator.next();
            if ("sy".equals(key)) {
                String s = tableFields.get(key).toString();
                sy = Arrays.asList(StringUtils.split(s, ","));
            } else {
                table = key;
            }
        }
        List<Field> fields = (List<Field>) tableFields.get(table);

        Boolean isSkip = false;   // 是否跳过，如果必要的直接跳过
        String[] syValues = new String[sy.size()]; // 索引值
        ArrayList<String> col = new ArrayList<>(); // 字段列表
        ArrayList<Object> values = new ArrayList<>(); // 值列表
        StringBuffer updateClause = new StringBuffer(); // 更新用
        try {
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                field.setAccessible(true); // 重要！设置权限，否则不能获取参数
                String val = field.get(tosave) + "";

                JsonField jsonField = field.getAnnotation(JsonField.class);
                if (jsonField == null)
                    continue;

                String fieldName = field.getName(); // 属性名
                Boolean fieldRequired = jsonField.required(); // 是否必填
                String fieldValue = jsonField.value(); // 属性值
                String tableField = jsonField.field(); // 字段名
                String fieldType = jsonField.type(); // 字段类型

                // 校验是否必填, 不通过直接跳过
                if(fieldRequired) {
                    if(null == val || "".equals(val)) {
                        isSkip = true;
                        break;
                    }
                }

                if (StringUtils.isBlank(val) || "null".equals(val)) { // 若值为空，则检测是否存在默认值，否则舍弃
                    if(StringUtils.isNotBlank(fieldValue)) {
                        val = fieldValue;
                    } else {
                        continue;
                    }
                }
                if (tableField != null) {
                    String[] tfields = tableField.split(",");
                    for (String tfield : tfields) {
                        String value = val;
                        if (sy.contains(tfield) && StringUtils.isNotBlank(value)) {
                            int index = sy.indexOf(tfield);
                            syValues[index] = value;
                        }
                        if (jsonField.convert()) {
                            String dimCode = jsonField.dimCode();
                            if(!dimCodesMap.containsKey(dimCode)) {
//                                Map<String, String> dimMap = StdDoMian.getDimNamesBypCode(dimCode);
                                Map<String, String> dimMap = new HashMap<>();
                                dimCodesMap.put(dimCode, dimMap);
                            }
//                            value = StdDoMian.getValueByKey(dimCodesMap.get(dimCode), value);
                        }
                        col.add(tfield);
                        if ("varchar".equals(fieldType)) {
                            // 日期字符串特殊处理
                            if(jsonField.isDateTime()) {
//                                value = DateFormatterUtil.getParseMain(value);
                            }
                            value = "'" + value + "'";
                        }
                        values.add(value);
                        if(tosave.getClass().getName().equals(field.getDeclaringClass().getName())) {
                            // Ather父类的属性不做更新
                            updateClause.append(tfield + "=" + value + ",");
                        }
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        //拼接sql语句
        String sql = "";
        if(!isSkip) {
            col.add("id");
            values.add("'" + "主键" + "'");

            col.add("sy");
            String syValue = StringUtils.join(syValues, "");
//            log.info("唯一索引：sy: {} syValue: {}", tableFields.get("sy"), syValue);
            values.add("'" + "md加密" + "'");

            sql = "INSERT INTO " + table + "(" + StringUtils.join(col, ",") + ") VALUES (" + StringUtils.join(values, ",") + ") "
                    + "ON DUPLICATE KEY UPDATE " + updateClause.toString() + "update_time = NOW();";
//            log.info("拼接sql: {}", sql);
        }
        return sql;
    }

}
