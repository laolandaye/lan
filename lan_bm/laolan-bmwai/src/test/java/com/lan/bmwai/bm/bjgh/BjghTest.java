package com.lan.bmwai.bm.bjgh;

import com.lan.bmwai.CommonJunitTest;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class BjghTest extends CommonJunitTest {

    private static final Logger LOG = LoggerFactory.getLogger(BjghTest.class);

    //批量关联应用与api.txt  kun_api_permit
    @Test
    public void kun_api_permit1()  throws Exception {
        List<Map<String, Object>> cons = Arrays.asList(
                new HashMap<String, Object>(){{ put("app_key","2c9849536c502347016c5066d04d0000"); put("app_label","用户管理应用");put("_api_code","SP_UC_"); }},
                new HashMap<String, Object>(){{ put("app_key","2c9849536c502347016c506720ee0001"); put("app_label","中央厨房应用");put("_api_code","SP_CK_"); }},
                new HashMap<String, Object>(){{ put("app_key","2c9849536c502347016c50674baf0002"); put("app_label","指挥调度应用");put("_api_code","SP_DD_"); }},
                new HashMap<String, Object>(){{ put("app_key","2c9849536c502347016c506774120003"); put("app_label","监测管理应用");put("_api_code","SP_MS_"); }},
                new HashMap<String, Object>(){{ put("app_key","4028b8816bfed7b8016bfef3ec960000"); put("app_label","数据中台应用");put("_api_code","SP_DS_"); }}
        );

        for (Map<String, Object> con : cons) {
            dealkun_api_permit1(con.get("app_key").toString(), con.get("_api_code").toString());
        }
    }

    private void dealkun_api_permit1(String app_key, String _api_code) throws SQLException {
        StringBuffer sb2 = new StringBuffer();
        sb2.append(" DELETE  FROM kun_api_permit WHERE api_code LIKE '" + _api_code + "%' ");
        baseDao.executeUpdate(sb2.toString());

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO kun_api_permit ( " +
                " id, " +
                " api_code, " +
                " api_id, " +
                " app_key, " +
                " apply_user, " +
                " create_dt, " +
                " eff_time, " +
                " exp_time, " +
                " extend_cfg, " +
                " lastupd, " +
                " state, " +
                " user_id, " +
                " user_times_limit " +
                ") " +
                "SELECT " +
                "md5(uuid()) as id, " +
                "api_code, " +
                "api_id, " +
                "'" + app_key + "' as app_key, " +
                "'8a9e80126be92a1b016be92de2de0000' AS apply_user, " +
                "NOW() AS create_dt, " +
                "NOW() AS eff_time, " +
                "'2037-12-31 23:59:59' as exp_time, " +
                "extend_cfg, " +
                "NOW() AS lastupd, " +
                "'PUBLISH' AS state, " +
                "'8a9e80126be92a1b016be92de2de0000' AS user_id, " +
                "'-1' AS user_times_limit " +
                " FROM kun_api WHERE api_code LIKE '" + _api_code + "%' ");
        baseDao.executeUpdate(sb.toString());
    }


}