package com.lan.bmwai.bm.dtfp;

import com.lan.bmwai.CommonJunitTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 大同项目 造数据
 *  //project_progress  由project_id， company_id， fill_date 确定唯一一条
 *  v_pp_pi_invest fill_date,xzqy_dm/team_code,project_nature
 */
/*
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://43.226.165.181:3306/dtfw?useUnicode=true&characterEncoding=utf8
jdbc.user=root
jdbc.password=QAZwsx#@!

 */
public class DtfpTest extends CommonJunitTest {

    //company_info
    @Test
    public void executeCompanyInfo()  throws Exception {
        List<Map<String, Object>> result1 = DtfpQueryData.queryFwTeam();
        List<Map<String, Object>> result2 = DtfpQueryData.queryFwDimBy("JJYX_SSHY_DM");
        List<Map<String, Object>> result3 = DtfpQueryData.queryFwDimBy("JJYX_QYLX_DM");

        List<Object[]> params = new ArrayList<Object[]>();
        for (Map<String, Object> map1 : result1) {
            for (Map<String, Object> map2 : result2) {
                for (Map<String, Object> map3 : result3) {
                    params.add(new Object[]{
                            test.bm.dtfp.DtfpUtils.getUUID(),
                            "",
                            map1.get("team_name") + "企业" + new Date(),
                            new Date(),
                            "地址:" + map1.get("team_name") + "企业" + new Date(),
                            map1.get("team_code"),
                            map1.get("team_id"),
                            map2.get("dim_code"),
                            test.bm.dtfp.DtfpUtils.getIntRandom(1000, 5000),
                            "",
                            "",
                            map3.get("dim_code"),
                            test.bm.dtfp.DtfpUtils.getIntRandom(5000, 10000),
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            new Date(),
                            "",
                            "",
                            test.bm.dtfp.DtfpUtils.getIntRandom(1, 3),
                            test.bm.dtfp.DtfpUtils.getIntRandom(1, 3)
                    });
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO company_info ( " +
                "  company_id, " +
                "  company_name, " +
                "  company_cn_name, " +
                "  company_set_time, " +
                "  company_address, " +
                "  company_area, " +
                "  team_code, " +
                "  company_trade, " +
                "  company_staff_num, " +
                "  company_legal, " +
                "  company_tel, " +
                "  company_type, " +
                "  company_size, " +
                "  company_product, " +
                "  data_reporter, " +
                "  reporter_eamil, " +
                "  reporter_tel, " +
                "  industry_enterprises, " +
                "  company_profiles, " +
                "  company_logo, " +
                "  company_website, " +
                "  create_time, " +
                "  company_latitude, " +
                "  company_longitude, " +
                "  company_major, " +
                "  company_scale " +
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
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ? " +
                " ); ");
        baseDao.executeBatch(sb.toString(), params);

    }

    //enterp_indicators
    @Test
    public void executeEnterpIndicators()  throws Exception {
        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> result1 = DtfpQueryData.queryCompanyInfo();
        List<Map<String, Object>> result2 = test.bm.dtfp.DtfpUtils.createFillDate(12);
//        List<Map<String, Object>> result2 = BjghUtils.createFillDate2("2019-04",52);
        for (Map<String, Object> map1 : result1) {
            for (Map<String, Object> map2 : result2) {
                params.add(new Object[]{
                        test.bm.dtfp.DtfpUtils.getUUID(),
                        map1.get("company_id"),
                        map1.get("company_name"),
                        map1.get("company_area"),
                        map1.get("team_code"),
                        map2.get("fill_date"),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        new Date()
                });
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO enterp_indicators ( " +
                "  Indica_ID, " +
                "  company_ID, " +
                "  company_name, " +
                "  xzqy_dm, " +
                "  team_code, " +
                "  fill_date, " +
                "  total_cur_month, " +
                "  total_pre_month, " +
                "  total_cur_cumulative, " +
                "  total_pre_cumulative, " +
                "  add_cur_month, " +
                "  add_pre_month, " +
                "  add_cur_cumulative, " +
                "  add_pre_cumulative, " +
                "  yield_cur_month, " +
                "  yield_pre_month, " +
                "  yield_cur_cumulative, " +
                "  yield_pre_cumulative, " +
                "  income_cur_month, " +
                "  income_pre_month, " +
                "  income_cur_cumulative, " +
                "  income_pre_cumulative, " +
                "  output_cur_month, " +
                "  output_pre_month, " +
                "  output_cur_cumulative, " +
                "  output_pre_cumulative, " +
                "  profit_cur_month, " +
                "  profit_pre_month, " +
                "  profit_cur_cumulative, " +
                "  profit_pre_cumulative, " +
                "  tax_cur_month, " +
                "  tax_pre_month, " +
                "  tax_cur_cumulative, " +
                "  tax_pre_cumulative, " +
                "  Taxpaid_cur_month, " +
                "  taxpaid_pre_month, " +
                "  taxpaid_cur_cumulative, " +
                "  taxpaid_pre_cumulative, " +
                "  capital_cur_month, " +
                "  capital_pre_month, " +
                "  capital_cur_cumulative, " +
                "  capital_pre_cumulative, " +
                "  receivable_cur_month, " +
                "  receivable_pre_month, " +
                "  receivable_cur_cumulative, " +
                "  receivable_pre_cumulative, " +
                "  assets_cur_month, " +
                "  assets_pre_month, " +
                "  assets_cur_cumulative, " +
                "  assets_pre_cumulative, " +
                "  bilit_cur_month, " +
                "  bilit_pre_month, " +
                "  bilit_cur_cumulative, " +
                "  bilit_pre_cumulative, " +
                "  create_time " +
                ") " +
                "VALUES " +
                "  ( " +
                "    ?, ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, " +
                "    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? " +
                "  ); " +
                "  ");

        baseDao.executeBatch(sb.toString(), params);
    }


    @Test
    public void executeProjectProgress()  throws Exception {
        List<Object[]> params = new ArrayList<Object[]>();

        DtfpQueryData.queryFwDimBy("TZ_GCZT_DM");

        List<Map<String, Object>> result1 = DtfpQueryData.queryProjectInfo();
//        List<Map<String, Object>> result2 = ShopQueryData.createFillDate(24);
        List<Map<String, Object>> result2 = test.bm.dtfp.DtfpUtils.createFillDate2("2019-04",52);
        for (Map<String, Object> map1 : result1) {
            for (Map<String, Object> map2 : result2) {
                params.add(new Object[]{
                        test.bm.dtfp.DtfpUtils.getUUID(),
                        map1.get("project_id"),
                        map1.get("company_id"),
                        map1.get("team_code"),
                        map1.get("xzqy_dm"),
                        2,
                        map2.get("fill_date"),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        DtfpQueryData.tzGcztDm.get(test.bm.dtfp.DtfpUtils.getIntRandom(0,8)),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        "",
                        test.bm.dtfp.DtfpUtils.getIntRandom(1,3),
                        "",
                        "",
                        "",
                        new Date()
                });
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO project_progress (  " +
                "  record_id,  " +
                "  project_id,  " +
                "  company_id,  " +
                "  team_code,  " +
                "  xzqy_dm,  " +
                "  record_index,  " +
                "  fill_date,  " +
                "  investment_total,  " +
                "  increase_num,  " +
                "  increase_work,  " +
                "  increase_profit,  " +
                "  increase_sale,  " +
                "  current_progress,  " +
                "  investment_completed,  " +
                "  investment_planned,  " +
                "  investment_completion,  " +
                "  new_output_value,  " +
                "  plant_new_output_value,  " +
                "  throughput_num,  " +
                "  new_or_up,  " +
                "  projec_group,  " +
                "  problems_coor,  " +
                "  project_remark,  " +
                "  create_time  " +
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
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?  " +
                "  );  " +
                "  ");

        baseDao.executeBatch(sb.toString(), params);
    }

    //dt_project_invest_ele
    @Test
    public void executeDtProjectInvestEle()  throws Exception {
        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> result1 = DtfpQueryData.queryFwTeam();
        List<Map<String, Object>> result2 = test.bm.dtfp.DtfpUtils.createFillDate(24);
        for (Map<String, Object> map1 : result1) {
            for (Map<String, Object> map2 : result2) {
                params.add(new Object[]{
                        test.bm.dtfp.DtfpUtils.getUUID(),
                        map2.get("fill_date"),
                        map1.get("team_code"),
                        map1.get("team_id"),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000)
                });
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO dt_project_invest_ele ( " +
                " id, " +
                " fill_date, " +
                " xzqy_dm, " +
                " team_code, " +
                " gykg, " +
                " gydz, " +
                " zwhz, " +
                " zrrtzhkg, " +
                " jtsyz, " +
                " wsdz, " +
                " nz, " +
                " wz " +
                ") VALUES  ( " +
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

    //dt_project_invest_ele 定时器跑一个月的数据
    @Deprecated
    @Test
    public void executeDtProjectInvestEle2()  throws Exception {

        String sql = "  SELECT fill_date,xzqy_dm,team_code,investment_total,project_nature,IFNULL(abroad_capital, 0) as abroad_capital, " +
                "IFNULL(home_capital, 0) as home_capital FROM v_pp_pi_month vppm " +
                "WHERE fill_date = date_format(DATE_SUB(STR_TO_DATE(now(), '%Y-%m-%d'),INTERVAL 1 MONTH),'%Y-%m') ";
        List<Map<String, Object>> list = baseDao.queryForList(sql);
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }

        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> result1 = DtfpQueryData.queryFwTeam();
        List<Map<String, Object>> result2 = test.bm.dtfp.DtfpUtils.createFillDate(24);
        for (Map<String, Object> map1 : result1) {
            for (Map<String, Object> map2 : result2) {
                params.add(new Object[]{
                        test.bm.dtfp.DtfpUtils.getUUID(),
                        map2.get("fill_date"),
                        map1.get("team_code"),
                        map1.get("team_id"),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(10000, 1000000)
                });
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO dt_project_invest_ele ( " +
                " id, " +
                " fill_date, " +
                " xzqy_dm, " +
                " team_code, " +
                " gykg, " +
                " gydz, " +
                " zwhz, " +
                " zrrtzhkg, " +
                " jtsyz, " +
                " wsdz, " +
                " nz, " +
                " wz " +
                ") VALUES  ( " +
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

//        baseDao.executeBatch(sb.toString(), params);
    }

    //dt_job_num
    @Test
    public void executeDtJobNum()  throws Exception {
        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> result1 = DtfpQueryData.queryFwTeam();
        List<Map<String, Object>> result2 = test.bm.dtfp.DtfpUtils.createFillDate(24);
        List<Map<String, Object>> result3 = DtfpQueryData.queryFwDimBy("TZ_HYLX_DM");
        for (Map<String, Object> map1 : result1) {
            for (Map<String, Object> map2 : result2) {
                for (Map<String, Object> map3 : result3) {
                    params.add(new Object[]{
                            test.bm.dtfp.DtfpUtils.getUUID(),
                            map2.get("fill_date"),
                            map1.get("team_code"),
                            map1.get("team_id"),
                            map3.get("dim_code"),
                            test.bm.dtfp.DtfpUtils.getIntRandom(100, 2000)
                    });
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO dt_job_num ( " +
                " id, " +
                " fill_date, " +
                " xzqy_dm, " +
                " team_code, " +
                " job_trade, " +
                " job_num " +
                ") VALUES  ( " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ? " +
                " ); ");

        baseDao.executeBatch(sb.toString(), params);
    }

    //dt_job_nums
    @Test
    public void executeDtJobNums()  throws Exception {
        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> result1 = DtfpQueryData.queryFwTeam();
        List<Map<String, Object>> result2 = test.bm.dtfp.DtfpUtils.createFillDate(24);
        List<Map<String, Object>> result3 = DtfpQueryData.queryFwDimBy("TZ_HYLX_DM");
        for (Map<String, Object> map1 : result1) {
            for (Map<String, Object> map2 : result2) {
                for (Map<String, Object> map3 : result3) {
                    params.add(new Object[]{
                            map2.get("fill_date"),
                            map1.get("team_code"),
                            map1.get("team_id"),
                            map3.get("dim_code"),
                            test.bm.dtfp.DtfpUtils.getIntRandom(100, 2000),
                            test.bm.dtfp.DtfpUtils.getIntRandom(1000, 20000),
                            test.bm.dtfp.DtfpUtils.getIntRandom(1000, 20000),
                            test.bm.dtfp.DtfpUtils.getIntRandom(1000, 20000),
                            test.bm.dtfp.DtfpUtils.getIntRandom(1000, 20000),
                            test.bm.dtfp.DtfpUtils.getIntRandom(1000, 20000)
                    });
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO dt_job_nums ( " +
                " fill_date, " +
                " xzqy_dm, " +
                " team_code, " +
                " job_trade, " +
                " job_num, " +
                " investment_total, " +
                " investment_completed, " +
                " investment_planned, " +
                " investment_completion, " +
                " investment_fixed " +
                ") VALUES  ( " +
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

    //dt_job_nums 定时器一月统计一次
    @Test
    public void executeDtJobNums2()  throws Exception {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM dt_job_nums WHERE fill_date = DATE_FORMAT(now(),'%Y-%m') ");
        baseDao.executeUpdate(sb.toString());


        StringBuffer sb2 = new StringBuffer();
//        sb2.append(" INSERT INTO dt_job_nums " +
//                "SELECT DATE_FORMAT(now(),'%Y-%m') AS fill_date, " +
//                "  company_area AS xzqy_dm,team_code,company_trade as job_trade, SUM(company_staff_num) AS job_num FROM company_info " +
//                "GROUP BY company_area,team_code,company_trade  ");
        sb2.append(" INSERT INTO dt_job_nums " +
                "SELECT DATE_FORMAT(now(),'%Y-%m') AS fill_date,T1.team_code,T1.xzqy_dm,T1.industry_type,SUM(T1.increase_work) AS job_num ,SUM(T1.investment_total) AS investment_total  " +
                ",SUM(T1.investment_completed) AS investment_completed  " +
                ",SUM(T1.investment_planned) AS investment_planned  " +
                ",SUM(T1.investment_completion) AS investment_completion  " +
                ",SUM(T1.investment_fixed) AS investment_fixed   FROM  " +
                "( SELECT * FROM v_pp_pi_month ) T1 INNER JOIN  " +
                "( SELECT MAX(fill_date) as fill_date,project_id FROM  v_pp_pi_month  " +
                "    WHERE  fill_date IS NOT NULL GROUP BY project_id ORDER BY project_id  " +
                ") T2  " +
                "ON T1.project_id = T2.project_id AND T1.fill_date = T2.fill_date  " +
                "GROUP BY T1.team_code,T1.xzqy_dm,T1.industry_type");
        baseDao.executeUpdate(sb2.toString());
    }

    @Test
    public void executeProductInfo()  throws Exception {

        List<Object[]> params = new ArrayList<Object[]>();
        List<Map<String, Object>> result1 = DtfpQueryData.queryEnterpIndicators();
        int k = 10000;
        for (int i = 0; i < result1.size(); i++) {
            //每一项随机添加
            for (int j = 0; j <  test.bm.dtfp.DtfpUtils.getIntRandom(2, 4); j++) {
                params.add(new Object[]{
                        test.bm.dtfp.DtfpUtils.getUUID(),
                        "产品" + k,
                        "万元",
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        test.bm.dtfp.DtfpUtils.getIntRandom(1000, 10000),
                        result1.get(i).get("Indica_ID").toString()
                });
            }
        }

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO product_info ( " +
                " id, " +
                " product_name, " +
                " unit, " +
                " cur_month, " +
                " cur_cumulative, " +
                " pre_month, " +
                " pre_cumulative, " +
                " data_id " +
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
                "  ? " +
                " ); ");
        baseDao.executeBatch(sb.toString(), params);
    }
}