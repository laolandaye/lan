package com.lan.bmwai.bm.cqrcbmysql;

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

/*
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://114.116.14.239:3306/cqrcb_demo?useUnicode=true&amp;characterEncoding=UTF-8
jdbc.user=root
jdbc.password=bmsoft@123
 */
public class CqrcbmysqlTest extends CommonJunitTest {

    private static final Logger LOG = LoggerFactory.getLogger(CqrcbmysqlTest.class);

    //批量关联应用与api.txt  kun_api_permit
    @Test
    public void kun_api_cdr_total()  throws Exception {
        baseDao.executeUpdate("TRUNCATE TABLE api_cdr_total ");

        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> apiPermits = CommonQuery.queryAll("api_permit");
        List<String> timeIds = ListUtil.createMonthBetween("2020-07-18 00:00:00", "2020-07-27 08:00:00", "1");
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

}