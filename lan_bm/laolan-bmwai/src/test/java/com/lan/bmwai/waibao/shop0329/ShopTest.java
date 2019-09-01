package com.lan.bmwai.waibao.shop0329;


import com.lan.bmwai.CommonJunitTest;
import com.lan.bmwai.waibao.ShopQueryData;
import org.junit.Test;
import waibao.ShopUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 43.226.165.181  QAZwsx#@!
 jdbc.driver=com.mysql.jdbc.Driver
 jdbc.url=jdbc:mysql://127.0.0.1:3307/innovation?useUnicode=true&characterEncoding=utf8
 jdbc.user=root
 jdbc.password=root
 */
public class ShopTest extends CommonJunitTest {



    //sp_goods
    //维度：goods_classify
    //外键：所属人员sys_user_id（多对一）
    @Test
    public void executeSpGoods()  throws Exception {

        List<Map<String, Object>> conMap1 = ShopQueryData.queryAll("sys_user");
        List<Map<String, Object>> conMap2 = ShopQueryData.queryFwDimBy("SPFL");

        List<Object[]> params = new ArrayList<Object[]>();

        for (int i = 0; i < 200; i++) {
            params.add(new Object[]{
                    ShopUtils.getUUID(),
                    "商品" + i,
                    ShopUtils.getIntRandom(100, 5000),
                    ShopUtils.getIntRandom(100, 50000),
                    "",
                    new Date(),
                    new Date(),
                    ShopUtils.getRandomList(conMap2, "dim_code"),
                    ShopUtils.getRandomList(conMap1, "id")
            });
        }


        StringBuffer sb2 = new StringBuffer();
        sb2.append(" INSERT INTO sp_goods ( " +
                " id, " +
                " name, " +
                " num, " +
                " price, " +
                " img_src, " +
                " update_time, " +
                " creare_time, " +
                " goods_classify, " +
                " sys_user_id " +
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
                "  ? " +
                " ); ");
        baseDao.executeBatch(sb2.toString(), params);
    }


    //sp_message
    //维度：message_status
    //外键：所属人员sys_user_id（多对一）
    @Test
    public void executeMessage()  throws Exception {

        List<Map<String, Object>> conMap1 = ShopQueryData.queryAll("sys_user");
        List<Map<String, Object>> conMap2 = ShopQueryData.queryFwDimBy("LY");

        List<Object[]> params = new ArrayList<Object[]>();

        for (int i = 0; i < 200; i++) {
            params.add(new Object[]{
                    ShopUtils.getUUID(),
                    "留言" + i,
                    ShopUtils.getRandomList(conMap2, "dim_code"),
                    new Date(),
                    new Date(),
                    ShopUtils.getRandomList(conMap1, "id")
            });
        }


        StringBuffer sb2 = new StringBuffer();
        sb2.append(" INSERT INTO sp_message (  " +
                "  id,  " +
                "  message,  " +
                "  message_status,  " +
                "  create_time,  " +
                "  update_time,  " +
                "  sys_user_id  " +
                ")  " +
                "VALUES  " +
                "  (  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?,  " +
                "    ?  " +
                "  ); ");
        baseDao.executeBatch(sb2.toString(), params);
    }

}
