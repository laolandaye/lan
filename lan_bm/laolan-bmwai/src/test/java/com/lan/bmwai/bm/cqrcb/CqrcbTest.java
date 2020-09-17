package com.lan.bmwai.bm.cqrcb;

import com.lan._1utils._1str._2UuidUtil;
import com.lan._1utils._2collectionmap.ListUtil;
import com.lan._1utils._3DateStrUtil;
import com.lan.bmwai.CommonJunitTest;
import com.lan.bmwai.CommonQuery;
import com.lan.bmwai.utils.CommonUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 大同项目 造数据
 * //project_progress  由project_id， company_id， fill_date 确定唯一一条
 * v_pp_pi_invest fill_date,xzqy_dm/team_code,project_nature
 */
/*
jdbc.driver=oracle.jdbc.driver.OracleDriver
jdbc.url=jdbc:oracle:thin:@139.159.243.23:1521/bmsoft
jdbc.user=cqrcb_demo
jdbc.password=cqrcb
 */
public class CqrcbTest extends CommonJunitTest {

    private static final Logger LOG = LoggerFactory.getLogger(CqrcbTest.class);

    //批量关联应用与api.txt  kun_api_permit
    @Test
    public void kun_api_cdr_total() throws Exception {
        baseDao.executeUpdate("TRUNCATE TABLE kun_api_cdr_total ");

        List<Map<String, Object>> apiPermits = CommonQuery.queryAll("kun_api_permit");
        List<String> timeIds = ListUtil.createMonthBetween("2020-05-05 00:00:00", "2020-05-08 08:00:00", "1");
        for (Map<String, Object> apiPermit : apiPermits) {
            for (int i = 0; i < timeIds.size(); i++) {
                Object[] param = new Object[]{
                        _2UuidUtil.getUUID(),
                        0,
                        apiPermit.get("API_ID"),
                        apiPermit.get("APP_KEY"),
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
                };

                String sql = "INSERT INTO kun_api_cdr_total ( " +
                        " ID, " +
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
                        " )";
                baseDao.executeUpdate(sql, param);
            }

        }

    }
}