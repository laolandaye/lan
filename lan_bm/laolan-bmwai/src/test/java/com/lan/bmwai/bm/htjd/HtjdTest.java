package com.lan.bmwai.bm.htjd;


import com.lan.bmwai.CommonJunitTest;
import org.junit.Test;
import test.bm.dtfp.DtfpUtils;
import test.bm.htjd.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 43.226.165.181  QAZwsx#@!
 jdbc.driver=com.mysql.jdbc.Driver
 jdbc.url=jdbc:mysql://118.184.212.218:3306/htjd?useUnicode=true&characterEncoding=utf8
 jdbc.user=root
 jdbc.password=L2019Mysql@
 */
public class HtjdTest extends CommonJunitTest {

    @Test
    public void update()  throws Exception{
        List<Map<String, Object>> result = HtjdQueryData.queryZsAreaInfo();
        StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE zs_area_info SET area_center = ? WHERE id = ? ");
        for (Map<String, Object> map : result) {
            String center = HtjdUtils.getCenterLnglat((String) map.get("area_coordinate2"));
            baseDao.executeUpdate(sb.toString(), new Object[]{center, map.get("id")});
        }
    }

    @Test
    public void update3()  throws Exception{
        List<Map<String, Object>> result = HtjdQueryData.queryZsAreaInfo();
        StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE zs_area_info SET area_coordinate2 = ? WHERE id = ? ");
        for (Map<String, Object> map : result) {
            List<double []> center = HtjdUtils.getGaodeLnglat2((String) map.get("area_coordinate"));
            String gaodePath = "[";
            for (int i =0; i < center.size(); i++) {
                gaodePath += "[";
                gaodePath += center.get(i)[1];
                gaodePath += ",";
                gaodePath += center.get(i)[0];
                gaodePath += "]";
                if(i < center.size() - 1) {
                    gaodePath += ",";
                } else {
                }
            }
            gaodePath += "]";
            System.out.println(gaodePath);
            baseDao.executeUpdate(sb.toString(), new Object[]{gaodePath, map.get("id")});
        }

    }

    //zs_base_ly_info
    @Test
    public void executeZsBaseLyInfo()  throws Exception {
        List<Object[]> params = new ArrayList<Object[]>();

        for (int i=0; i<20; i++) {
            params.add(new Object[]{
                    UuidUtils.getUUID(),
                    "公司" + i,
                    "公司简称" + i,
                    DtfpUtils.getIntRandom(100, 2000),
                    DtfpUtils.getIntRandom(10, 50),
                    DtfpUtils.getIntRandom(1000, 20000),
                    DtfpUtils.getIntRandom(1000, 20000),
                    new Date(),
                    "地址" + i
            });
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO zs_base_ly_info (  " +
                " id,  " +
                " ly_name,  " +
                " ly_simple_name,  " +
                " ly_size,  " +
                " ly_level_num,  " +
                " ly_longitude,  " +
                " ly_latitude,  " +
                " create_time,  " +
                " address  " +
                ")  " +
                "VALUES  " +
                " (  " +
                "  ?,  " +
                "  ?,  " +
                "  ?,  " +
                "  ?,  " +
                "  ?,  " +
                "  ?,  " +
                "  ?,  " +
                "  ?,  " +
                "  ?  " +
                " ); ");

        baseDao.executeBatch(sb.toString(), params);
    }

    //zs_project_base
    /**
     * 唯一确定： 项目id
     * 维度：1.project_stats 项目状态 2.project_source 项目来源（招商项目投资类型）
     * 外键：1.project_areaid 项目用地 2.company_id 项目投资公司
     * TRUNCATE TABLE zs_project_base
     */
    @Test
    public void executeZsProjectBase()  throws Exception {
        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> result1 = HtjdQueryData.queryFwDimBy("ZS_XMTZLX_DM");
        List<Map<String, Object>> result2 = HtjdQueryData.queryFwDimBy("ZS_XMZT_DM");
        List<Map<String, Object>> result3 = HtjdQueryData.queryZsCompanyInfo();
        List<Map<String, Object>> result4 = HtjdQueryData.queryZsAreaInfo();
        int j = 1;
        for (Map<String, Object> map1 : result1) {
            for (Map<String, Object> map2 : result2) {
                for (Map<String, Object> map3 : result3) {
                for (Map<String, Object> map4 : result4) {
                    for (int i = 0; i < DtfpUtils.getIntRandom(0, 3); i++) {
                        j++;
                        params.add(new Object[]{
                                UuidUtils.getUUID(),
                                "项目" + (j+1),
                                "项目编码" + (j+1),
                                new Date(),
                                HtjdUtils.getIntRandom(1000, 20000),
                                HtjdUtils.getIntRandom(1, 3),
                                HtjdUtils.getIntRandom(1000, 20000),
                                HtjdUtils.getIntRandom(1000, 20000),
                                HtjdUtils.getIntRandom(100, 500),
                                new Date(),
                                new Date(),
                                HtjdUtils.getIntRandom(100, 500),
                                HtjdUtils.getIntRandom(100, 500),
                                HtjdUtils.getIntRandom(1, 3),
                                map4.get("id"),
                                map3.get("id"),
                                map2.get("dim_code"),
                               "地址" + (j+1),
                                map1.get("dim_code"),
                                ""
                        });
                    }
                    }
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO zs_project_base (  " +
                "  id,  " +
                "  project_name,  " +
                "  project_code,  " +
                "  contract_time,  " +
                "  total_investment,  " +
                "  project_region,  " +
                "  project_longitude,  " +
                "  project_latitude,  " +
                "  area_covered,  " +
                "  build_begin_time,  " +
                "  build_end_time,  " +
                "  company_person,  " +
                "  investment_person,  " +
                "  project_important,  " +
                "  project_areaid,  " +
                "  company_id,  " +
                "  project_stats,  " +
                "  project_address,  " +
                "  project_source,  " +
                "  team_code  " +
                ")  " +
                "VALUES  " +
                "  (  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?  " +
                "  ) ");

        baseDao.executeBatch(sb.toString(), params);
    }


    //zs_project_progress
    /**
     * (唯一确定：project_id，fill_date)
     * 维度：current_progress 当前进展(对应维度工程状态) ZS_XMZT_DM
     * 外键：project_id 项目ID
     * fill_date
     * TRUNCATE TABLE zs_project_progress
     */
    @Test
    public void executeZsProjectProgress()  throws Exception {
        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> conMap1 = HtjdQueryData.queryFwDimBy("ZS_XMZT_DM");
        List<Map<String, Object>> result1 = HtjdUtils.createFillDate(12);
        List<Map<String, Object>> result2 = HtjdQueryData.queryAll("zs_project_base");
        for (Map<String, Object> map1 : result1) {
            for (Map<String, Object> map2 : result2) {
                params.add(new Object[]{
                        test.bm.htjd.utils.UuidUtils.getUUID(),
                        map2.get("id"),
                        map1.get("fill_date"),
                        HtjdUtils.getRandomList(conMap1, "dim_code"),
                        HtjdUtils.getIntRandom(1000, 20000),
                        HtjdUtils.getIntRandom(100, 500),
                        HtjdUtils.getIntRandom(1000, 20000),
                        HtjdUtils.getIntRandom(1000, 20000),
                        HtjdUtils.getIntRandom(1000, 20000),
                        HtjdUtils.getIntRandom(1000, 20000),
                        HtjdUtils.getIntRandom(1000, 20000),
                        HtjdUtils.getIntRandom(1000, 20000),
                        HtjdUtils.getIntRandom(1000, 20000),
                        HtjdUtils.getIntRandom(1000, 20000),
                        HtjdUtils.getIntRandom(1, 2),
                        "",
                        "",
                        "",
                        "",
                        "",
                        new Date()
                });
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO zs_project_progress ( " +
                " id, " +
                " project_id, " +
                " fill_date, " +
                " current_progress, " +
                " increase_profit, " +
                " increase_work, " +
                " increase_sale, " +
                " increase_num, " +
                " new_output_value, " +
                " plant_new_output_value, " +
                " investment_total, " +
                " investment_completed, " +
                " investment_planned, " +
                " investment_completion, " +
                " new_or_up, " +
                " projec_group, " +
                " problems_coor, " +
                " project_remark, " +
                " reporter_tel, " +
                " data_reporter, " +
                " create_time " +
                ") " +
                "VALUES " +
                " ( " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ? " +
                " ); ");

        baseDao.executeBatch(sb.toString(), params);
    }
}
