package com.lan.bmwai.bm.bjfc;

import com.lan._1utils._1str._2UuidUtil;
import com.lan._1utils._2collectionmap.ListUtil;
import com.lan._1utils._3DateStrUtil;
import com.lan.bmwai.CommonJunitTest;
import com.lan.bmwai.CommonQuery;
import com.lan.bmwai.utils.CommonUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 大同项目 造数据
 *  //project_progress  由project_id， company_id， fill_date 确定唯一一条
 *  v_pp_pi_invest fill_date,xzqy_dm/team_code,project_nature
 */
/*
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/bjfc?useUnicode=true&characterEncoding=utf8
jdbc.user=root
jdbc.password=root
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://192.1680.130:3306/bjfc?useUnicode=true&characterEncoding=utf8
jdbc.user=root
jdbc.password=Abcd!234
 */
public class BjfcTest extends CommonJunitTest {

    private static final Logger LOG = LoggerFactory.getLogger(BjfcTest.class);

    //批量关联应用与api.txt  kun_api_permit
    @Test
    public void kun_api_cdr_total()  throws Exception {
        baseDao.executeUpdate("TRUNCATE TABLE kun_api_cdr_total ");

        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> apiPermits = CommonQuery.queryAll("kun_api_permit");
        List<String> timeIds = ListUtil.createMonthBetween("2020-06-16 00:00:00", "2020-06-21 08:00:00", "1");
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

        String sql = "INSERT INTO kun_api_cdr_total ( " +
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

}