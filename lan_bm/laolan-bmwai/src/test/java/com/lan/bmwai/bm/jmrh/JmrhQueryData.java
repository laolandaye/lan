package com.lan.bmwai.bm.jmrh;

import com.lan.bmwai.CommonJunitTest;

import java.util.List;
import java.util.Map;

public class JmrhQueryData extends CommonJunitTest {

    /**
     * 1.查看县一级维度id
     */
    public static List<Map<String, Object>> queryFwTeam() {
        String sql = "  SELECT * FROM fw_team where team_level = '2' ";
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
            if("TZ_GCZT_DM".equals(str)) {
            } else if("JJYX_SSHY_DM".equals(str)) {
            } else if("JJYX_QYLX_DM".equals(str)) {
            } else if("TZ_HYLX_DM".equals(str)) {
            }
        }
        return list;
    }

    /**
     * 查询所有
     */
    public static List<Map<String, Object>> queryAll(String table) {
        String sql = "  SELECT * FROM  " + table;
        List<Map<String, Object>> list = baseDao.queryForList(sql);

        for(Map emp:list){
            System.out.println(emp);
        }
        return list;
    }


    public static void main(String[] args) {

    }


}
