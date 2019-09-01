package com.lan.bmwai.waibao;

import com.lan.bmwai.CommonJunitTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopQueryData extends CommonJunitTest {

    public static List<Object> SPFL = new ArrayList<>();


    public static List<Map<String, Object>> queryAll(String table) {
        String sql = "  SELECT * FROM  " + table;
        List<Map<String, Object>> list = baseDao.queryForList(sql);

        for(Map emp:list){
            System.out.println(emp);
        }
        return list;
    }

    /**
     * 1.查看县一级维度id
     */
    public static List<Map<String, Object>> queryFwDimBy(String str) {
        String sql = "  SELECT * FROM fw_dim WHERE pid = (  SELECT dim_id FROM fw_dim WHERE dim_code = ? ) ";
        List<Map<String, Object>> list = baseDao.queryForList(sql, new Object[]{str});

        for(Map emp:list){
            if("SPFL".equals(str)) {
                SPFL.add(emp.get("dim_code"));
            } else if("JJYX_SSHY_DM".equals(str)) {
            } else if("JJYX_QYLX_DM".equals(str)) {
            } else if("TZ_HYLX_DM".equals(str)) {
            }
        }
        return list;
    }



    public static void main(String[] args) {

    }


}
