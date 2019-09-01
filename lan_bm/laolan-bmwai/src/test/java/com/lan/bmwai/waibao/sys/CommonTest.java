package com.lan.bmwai.waibao.sys;


import com.lan.bmwai.CommonJunitTest;
import com.lan.bmwai.waibao.ShopQueryData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 43.226.165.181  QAZwsx#@!
 jdbc.driver=com.mysql.jdbc.Driver
 jdbc.url=jdbc:mysql://127.0.0.1:3307/innovation?useUnicode=true&characterEncoding=utf8
 jdbc.user=root
 jdbc.password=root
 */
public class CommonTest extends CommonJunitTest {

    //sys_role_permission
    @Test
    public void executeSysRolePermission()  throws Exception {

        StringBuffer sb = new StringBuffer();
        sb.append(" DELETE FROM sys_role_permission WHERE sys_role_id = '0' ");
        baseDao.executeUpdate(sb.toString());

        List<Map<String, Object>> result1 = ShopQueryData.queryAll("sys_permission");
        List<Object[]> params = new ArrayList<Object[]>();

        for (int i = 0; i < result1.size(); i++) {
            params.add(new Object[]{
                    waibao.ShopUtils.getUUID(),
                    "0",
                    result1.get(i).get("id")
            });
        }


        StringBuffer sb2 = new StringBuffer();
        sb2.append(" INSERT INTO sys_role_permission ( " +
                " id, " +
                " sys_role_id, " +
                " sys_permission_id " +
                ") " +
                "VALUES " +
                " (?, ?, ?) ");
        baseDao.executeBatch(sb2.toString(), params);
    }

    /*public String dimCode1 = "SPFL";
    public String dimName1 = "商品分类";
    public String [] dim2 = new String[]{
            "图书","手机","家用电器","数码","家具","衣服","钟表","生鲜","内裤"
    };*/

    /*public String dimCode1 = "LY";
    public String dimName1 = "留言";
    public String [] dim2 = new String[]{
            "审核中","已通过","已驳回"
    };*/

    /*public String dimCode1 = "XMTYPE";
    public String dimName1 = "项目类型";
    public String [] dim2 = new String[]{
            "sys","shop","car"
    };*/

    /*public String dimCode1 = "GKLX";
    public String dimName1 = "顾客类型";
    public String [] dim2 = new String[]{
            "到电","来电","购车"
    };*/
    /*public String dimCode1 = "JBQJ";
    public String dimName1 = "加班，请假状态";
    public String [] dim2 = new String[]{
            "hr未审核","hr审核通过","老总未审核","老总审核通过","已驳回"
    };*/

    public String dimCode1 = "QCXZ";
    public String dimName1 = "奖惩，薪资状态";
    public String [] dim2 = new String[]{
            "hr提交","员工核准","老总未审核","老总审核通过","已驳回"
    };


    @Test
    public void executeFwDim()  throws Exception {

        StringBuffer sb0 = new StringBuffer();
        sb0.append(" DELETE FROM fw_dim WHERE dim_code = '"+ dimCode1 +"' OR pid = '"+ dimCode1 +"'");
        baseDao.executeUpdate(sb0.toString());

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO fw_dim ( " +
                " dim_id, " +
                " dim_code, " +
                " dim_level, " +
                " dim_name " +
                ") " +
                "VALUES " +
                " ( " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ? " +
                " ); ");
        baseDao.executeUpdate(sb.toString(), new Object[]{
                dimCode1,
                dimCode1,
                1,
                dimName1
        });

        List<Object[]> params = new ArrayList<Object[]>();

        for (int i = 0; i < dim2.length; i++) {
            params.add(new Object[]{
                    dimCode1 + (i + 1),
                    dimCode1 + (i + 1),
                    2,
                    dim2[i],
                    (i + 1),
                    dimCode1
            });
        }


        StringBuffer sb2 = new StringBuffer();
        sb2.append(" INSERT INTO fw_dim ( " +
                " dim_id, " +
                " dim_code, " +
                " dim_level, " +
                " dim_name, " +
                " dim_seq, " +
                " pid " +
                ") " +
                "VALUES " +
                " ( " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ?, " +
                "  ? " +
                " ); ");
        baseDao.executeBatch(sb2.toString(), params);
    }

}
