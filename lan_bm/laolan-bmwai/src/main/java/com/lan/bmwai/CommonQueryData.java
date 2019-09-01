package com.lan.bmwai;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonQueryData extends CommonJunitTest {

    //1.团队与区域对应
    public static Map<String, Object> teamXzqy = new HashMap<>();
    //2.区域与团队对应
    public static Map<String, Object> xzqyTeam = new HashMap<>();
    //3. 工程状态维度 TZ_GCZT_DM
    public static List<Object> tzGcztDm = new ArrayList<>();
    //4. 所属行业维度 JJYX_SSHY_DM
    public static List<Object> jjyxSshyDm = new ArrayList<>();
    //4. 所属行业维度 TZ_HYLX_DM
    public static List<Object> tzHylxDm = new ArrayList<>();
    //5. 企业类型维度 JJYX_QYLX_DM
    public static List<Object> jjyxQylxDm = new ArrayList<>();




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
                tzGcztDm.add(emp.get("dim_code"));
            } else if("JJYX_SSHY_DM".equals(str)) {
                jjyxSshyDm.add(emp.get("dim_code"));
            } else if("JJYX_QYLX_DM".equals(str)) {
                jjyxQylxDm.add(emp.get("dim_code"));
            } else if("TZ_HYLX_DM".equals(str)) {
                tzHylxDm.add(emp.get("dim_code"));
            }
        }
        return list;
    }


    /**
     *
     */
    public static List<Map<String, Object>> queryCompanyInfo() {
        String sql = "  SELECT * FROM company_info  ";
        List<Map<String, Object>> list = baseDao.queryForList(sql);

        for(Map emp:list){
            System.out.println(emp);
        }
        return list;
    }




    /**
     *
     */
    public static List<Map<String, Object>> queryProjectInfo() {
        String sql = "  SELECT * FROM project_info  ";
        List<Map<String, Object>> list = baseDao.queryForList(sql);

        for(Map emp:list){
            System.out.println(emp);
        }
        return list;
    }

    /**
     * 5. 处理中文乱码
     */
    public static String getZwlm(String str) throws UnsupportedEncodingException {
        String name = new String(str.getBytes("utf-8"), "utf-8");
        return name;
    }

    /**
     *
     */
    public static List<Map<String, Object>> queryEnterpIndicators() {
        String sql = "  SELECT * FROM enterp_indicators  ";
        List<Map<String, Object>> list = baseDao.queryForList(sql);

        for(Map emp:list){
            System.out.println(emp);
        }
        return list;
    }


    public static void main(String[] args) {
        queryFwTeam();
//        queryCompanyInfo();
//        createFillDate(24);
//        queryProjectInfo();
//        queryFwDimBy("TZ_GCZT_DM");

    }


}
