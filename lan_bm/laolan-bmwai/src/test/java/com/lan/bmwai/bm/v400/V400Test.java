package com.lan.bmwai.bm.v400;

import com.lan._1utils._1str._2UuidUtils;
import com.lan.bmwai.CommonJunitTest;
import com.lan.bmwai.utils.BjghUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
}