package com.lan.bmwai.bm.v400;

import com.lan._1utils._1str._2UuidUtil;
import com.lan._1utils._2collectionmap.ListUtil;

import com.lan._1utils._3DateStrUtil;
import com.lan.bmwai.CommonJunitTest;
import com.lan.bmwai.CommonQuery;
import com.lan.bmwai.utils.CommonUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class V400Test extends CommonJunitTest {

    private static final Logger LOG = LoggerFactory.getLogger(V400Test.class);

    //  给 input_param 添加 "" ( 除了 sql， null, ''  以外的),给value加“”
    @Test
    public void META_OBJ_ATTR_inputParam1()  throws Exception {
        String sql1 = " SELECT input_param AS INPUT_PARAM FROM meta_obj_attr WHERE obj_type IN(SELECT func_code FROM dp_proc_func_def_java ) AND input_param_type NOT IN ('sql') AND input_param IS NOT NULL  AND input_param != '' GROUP BY input_param ";
        List<Map<String, Object>> list1 = baseDao.queryForList(sql1);
        for (Map<String, Object> item1: list1) {
            String inputParam1 = (String) item1.get("INPUT_PARAM");
            List<String> inputParam2_1 = new ArrayList<>();
            for (String s1 : inputParam1.split("\\|")) {
                 // 可以忽略，没有逗号就当做一个元素
                List<String> inputParam2_2 = new ArrayList<>();
                // 从第一位开始,单个理解为 key ,一对理解为 value,key
                String[] ss= s1.split("\\,");
                if(ss.length > 1) {
                    for (int i = 0; i < ss.length; i++) {
                        if(i > 0) {
                            inputParam2_2.add("\"" + ss[i] + "\"");
                        } else {
                            inputParam2_2.add(ss[i]);
                        }
                    }
                    inputParam2_1.add(String.join(",", inputParam2_2));
                } else {
                    inputParam2_1.add("\"" + s1 + "\"");
                }

            }
            item1.put("inputParam2", String.join("|", inputParam2_1));
        }

        for (Map<String, Object> map : list1) {
            String updateSql = "UPDATE meta_obj_attr set input_param = '" + (String) map.get("inputParam2") +
                    "' WHERE obj_type IN(SELECT func_code FROM dp_proc_func_def_java ) " +
                    "AND input_param = '" + (String) map.get("INPUT_PARAM") +"'";
            LOG.info(updateSql);
            baseDao.executeUpdate(updateSql);
        }
    }

    // 恢复， 去除 “
    @Test
    public void META_OBJ_ATTR_inputParam1_2()  throws Exception {
        String sql1 = " SELECT input_param AS INPUT_PARAM FROM meta_obj_attr WHERE obj_type IN(SELECT func_code FROM dp_proc_func_def_java ) AND input_param_type NOT IN ('sql') AND input_param IS NOT NULL  AND input_param != '' GROUP BY input_param ";
        List<Map<String, Object>> list1 = baseDao.queryForList(sql1);
        for (Map<String, Object> item1: list1) {
            String inputParam1 = (String) item1.get("INPUT_PARAM");
            item1.put("inputParam2", String.join("|", inputParam1.replaceAll("\"", "")));
        }

        for (Map<String, Object> map : list1) {
            String updateSql = "UPDATE meta_obj_attr set input_param = '" + (String) map.get("inputParam2") +
                    "' WHERE obj_type IN(SELECT func_code FROM dp_proc_func_def_java ) " +
                    "AND input_param = '" + (String) map.get("INPUT_PARAM") +"'";
            LOG.info(updateSql);
            baseDao.executeUpdate(updateSql);
        }
    }

    //  给 dependent_conditions 的 ’ 改为 “
    @Test
    public void META_OBJ_ATTR_dependentConditions1()  throws Exception {
        String sql1 = " SELECT dependent_conditions AS DEPENDENT_CONDITIONS FROM meta_obj_attr WHERE obj_type IN(SELECT FUNC_CODE FROM dp_proc_func_def_java ) AND dependent_conditions is not null ";
        List<Map<String, Object>> list1 = baseDao.queryForList(sql1);
        String dataBaseType = "mysql";
        for (Map<String, Object> item1: list1) {
            String dependentConditions1 = (String) item1.get("DEPENDENT_CONDITIONS");
            item1.put("dependentConditions2", dependentConditions1.replaceAll("\\'", "\""));
            // 针对单引号的处理
            if("mysql".equals(dataBaseType)) {
                dependentConditions1 = dependentConditions1.replaceAll("\\'", "\\\\\'");
            }
            item1.put("dependentConditions1", dependentConditions1);
        }

        for (Map<String, Object> map : list1) {
            String updateSql = "UPDATE meta_obj_attr set dependent_conditions = '" + (String) map.get("dependentConditions2") +
                    "' WHERE obj_type IN(SELECT func_code FROM dp_proc_func_def_java ) " +
                    "AND dependent_conditions = '"+ (String) map.get("dependentConditions1") + "'";
            LOG.info(updateSql);
            baseDao.executeUpdate(updateSql);
        }
    }

    // 模拟数据 api_cdr_total
    @Test
    public void api_cdr_total()  throws Exception {
        baseDao.executeUpdate("TRUNCATE TABLE api_cdr_total ");

        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> apiPermits = CommonQuery.queryAll("api_permit");
        List<String> timeIds = ListUtil.createMonthBetween("2020-07-06 00:00:00", "2020-07-14 08:00:00", "1");
        for (Map<String, Object> apiPermit : apiPermits) {
            for (int i = 0; i < timeIds.size(); i++) {
                params.add(new Object[]{
                        _2UuidUtil.getUUID(),
                        0,
                        apiPermit.get("api_id"),
                        apiPermit.get("app_key"),
                        CommonUtils.getIntRandom(10, 50),
                        CommonUtils.getIntRandom(1, 20),
                        CommonUtils.getIntRandom(1, 20),
                        CommonUtils.getIntRandom(10, 50),
                        CommonUtils.getIntRandom(10, 50),
                        CommonUtils.getIntRandom(100, 500),
                        CommonUtils.getIntRandom(10, 50),
                        timeIds.get(i),
                        _3DateStrUtil.getWeekOfDate(timeIds.get(i)),
                        CommonUtils.getIntRandom(10, 50)
                });
            }
        }

        String sql = "INSERT INTO api_cdr_total ( " +
                " id, " +
                " acc_err_times, " +
                " api_id, " +
                " app_key, " +
                " avg_resp_time, " +
                " biz_err_times, " +
                " charging_times, " +
                " max_resp_time, " +
                " min_resp_time, " +
                " req_times, " +
                " resp_body_len, " +
                " time_id, " +
                " api_week, " +
                " resp_record_num " +
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
                "  ? " +
                " );";
        baseDao.executeBatch(sql, params);
    }

    // api_cdr, api_cdr_total 生成 permit 数据
    @Test
    public void cdr()  throws Exception {
        baseDao.executeUpdate("TRUNCATE TABLE api_cdr_total ");

        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> apiPermits = CommonQuery.queryAll("api_permit");
        List<String> timeIds = ListUtil.createMonthBetween("2020-08-06 00:00:00", "2020-08-17 11:00:00", "1");
        for (Map<String, Object> apiPermit : apiPermits) {
            for (int i = 0; i < timeIds.size(); i++) {
                params.add(new Object[]{
                        _2UuidUtil.getUUID(),
                        0,
                        apiPermit.get("api_id"),
                        apiPermit.get("id"),
                        apiPermit.get("app_key"),
                        CommonUtils.getIntRandom(10, 50),
                        CommonUtils.getIntRandom(1, 20),
                        CommonUtils.getIntRandom(1, 20),
                        CommonUtils.getIntRandom(10, 50),
                        CommonUtils.getIntRandom(10, 50),
                        CommonUtils.getIntRandom(100, 500),
                        CommonUtils.getIntRandom(10, 50),
                        timeIds.get(i),
                        _3DateStrUtil.getWeekOfDate(timeIds.get(i)),
                        CommonUtils.getIntRandom(10, 50)
                });
            }
        }

        String sql = "INSERT INTO api_cdr_total ( " +
                " id, " +
                " acc_err_times, " +
                " api_id, " +
                " api_permit_id, " +
                " app_key, " +
                " avg_resp_time, " +
                " biz_err_times, " +
                " charging_times, " +
                " max_resp_time, " +
                " min_resp_time, " +
                " req_times, " +
                " resp_body_len, " +
                " time_id, " +
                " api_week, " +
                " resp_record_num " +
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
                "  ? " +
                " );";
        baseDao.executeBatch(sql, params);
    }

    @Test
    public void fw_param_config_lv1()  throws Exception {
        baseDao.executeUpdate("TRUNCATE TABLE fw_dim_copy1 ");

        String sql1 = "  SELECT param_type FROM fw_param_config GROUP BY param_type ORDER BY param_type ";
        List<Map<String, Object>> list1 = baseDao.queryForList(sql1);

        List<Object[]> params1_1 = new ArrayList<Object[]>();
        for (int i = 0; i < list1.size(); i++) {
            params1_1.add(new Object[]{
                    _2UuidUtil.getUUID(),
                    list1.get(i).get("param_type"),
                    1,
                    list1.get(i).get("param_type") + "配置",
                    (i+1),
                    1
            });
        }
        
        String sql1_1 = "INSERT INTO fw_dim_copy1 ( " +
                " dim_id, " +
                " dim_code, " +
                " dim_level, " +
                " dim_name, " +
                " dim_seq, " +
                " dim_status " +
                ") " +
                "VALUES " +
                " ( " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ? " +
                " );";
        baseDao.executeBatch(sql1_1, params1_1);
    }

    @Test
    public void fw_param_config_lv2()  throws Exception {
        List<Map<String, Object>> list1 = CommonQuery.queryAll("fw_dim_copy1");

        String sql1_1 = "INSERT INTO fw_dim_copy1 ( " +
                " dim_id, " +
                " dim_code, " +
                " dim_level, " +
                " dim_name, " +
                " dim_seq, " +
                " dim_status, " +
                " dim_value_code, " +
                " dim_value_name, " +
                " pid, " +
                " remark " +
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
                "  ? " +
                " );";


        for (Map<String, Object> map : list1) {
            List<Map<String, Object>> listConfig = baseDao.queryForList(" SELECT * FROM fw_param_config WHERE param_type = '" + map.get("dim_code") + "' ORDER BY param_code " );
            List<Object[]> params1_1 = new ArrayList<Object[]>();
            for (int i = 0; i < listConfig.size(); i++) {
                params1_1.add(new Object[]{
                        _2UuidUtil.getUUID(),
                        listConfig.get(i).get("param_code"),
                        2,
                        listConfig.get(i).get("param_name"),
                        (i+1),
                        1,
                        listConfig.get(i).get("param_value"),
                        listConfig.get(i).get("param_name"),
                        map.get("dim_id"),
                        listConfig.get(i).get("param_remark")
                });
            }
            baseDao.executeBatch(sql1_1, params1_1);
        }


    }
}