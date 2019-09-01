package com.lan.bmwai.bm.jmrh;


import com.lan.bmwai.CommonJunitTest;
import com.lan.bmwai.bm.htjd.HtjdQueryData;
import org.junit.Test;
import test.bm.htjd.utils.UuidUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 43.226.165.181  QAZwsx#@!
 jdbc.driver=com.mysql.jdbc.Driver
 jdbc.url=jdbc:mysql://118.184.212.218:3306/jmrh?useUnicode=true&characterEncoding=utf8
 jdbc.user=root
 jdbc.password=L2019Mysql@
 */
public class JmrhTest extends CommonJunitTest {


    //jm_news_pic_video
    /**
     * 唯一确定： news_id
     * 维度：1.news_type 新闻类型 JM_XWLX  2.news_status (维)审核状态
     * 外键：1.publish_person
     * TRUNCATE TABLE jm_news_pic_video
     */
    @Test
    public void executeJmNewsPicVideo()  throws Exception {
        List<Object[]> params = new ArrayList<Object[]>();

        List<Map<String, Object>> cons1 = HtjdQueryData.queryFwDimBy("JM_XWLX");

        for (int i = 0; i < 50; i++) {
            params.add(new Object[]{
                UuidUtils.getUUID(),
                "新闻标题" + i,
                JmrhUtils.getRandomList(cons1, "dim_code"),
                "",
                new Date(),
                "发布人",
                "发布部门",
                "新闻内容" + i,
                "新闻状态" + i,
            });
        }
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO jm_news_pic_video (  " +
                " news_id,  " +
                " news_title,  " +
                " news_type,  " +
                " picture_url,  " +
                " publish_time,  " +
                " publish_person,  " +
                " publish_department,  " +
                " news_context,  " +
                " news_status  " +
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
                " ) ");

        baseDao.executeBatch(sb.toString(), params);
    }

}
