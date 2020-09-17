//package com.lan.bmwai.bm.v353;
//
//import com.lan._1utils._1str._2UuidUtils;
//import com.lan.bmwai.CommonJunitTest;
//import com.lan.bmwai.bm.v400.V400Test;
//import com.lan.bmwai.utils.BjghUtils;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//public class V353Test extends CommonJunitTest {
//
//    private static final Logger LOG = LoggerFactory.getLogger(V353Test.class);
//
//    //  dp_data_trans_log
//    @Test
//    public void dp_data_trans_log2()  throws Exception {
//        String jobId = "laolan_xml2oracle";
//        String batchNo= "20190821";
//        String stepId= "3";
//        String source= "ccf599ec775b4bf793486508d4824f41";
//        String target = "99692b4e3b174d3ba3f9509186bad28d";
//        String sourceType="3";
//        String targetType="Oracle";
//        String sourceLocation="/home/dam/laolan/laolan_xml.xml";
//        String targetLocation="CJ_USER";
//        int readNum=3;
//        int writeNum=3;
//        int errorNum=0;
//        int effectNum=3;
//        String startTime="2019-08-21 15:42:50";
//        String endTime="2019-08-21 15:43:02";
//        String totalTime="11s";
//        String speed = "0rec/s";
//        String delSql = "delete from dp_data_trans_log where job_id =? and batch_no=? and step_id=?";
//        String sql="INSERT INTO dp_data_trans_log (id,job_id,batch_no,step_id,source, target, source_type, target_type, source_location, "
//                + "target_location, read_num, write_num, error_num, effect_num, "
//                + "start_time, end_time, total_time, speed)" +
//                " VALUES " +
//                "	(?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?) ";
//        baseDao.executeUpdate(delSql,  new Object[] {jobId,batchNo,stepId});
//        baseDao.executeUpdate(sql,new Object[] {BjghUtils.getUUID(),jobId,batchNo,stepId,source,target, sourceType, targetType,
//                sourceLocation,targetLocation, readNum, writeNum, errorNum, effectNum,
//                startTime, endTime, totalTime, speed});
//    }
//
//    // dp_dev_physic_col 查询元模型
//    @Test
//    public void dp_dev_physic_col1()  throws Exception {
//        String tab_id = "42a7ba05fc28d843e5bc5a012c78f5b1";
//        String sql="SELECT col_name,col_type,ispkey,isparty,party_seq,col_length,col_precision,isnullable,default_value,col_cn_name,remark,col_seq FROM dp_dev_physic_col where tab_id=? ORDER BY col_seq; ";
//        List<Map<String, Object>> list = baseDao.queryForList(sql,  new Object[] {tab_id});
//        for (Map<String, Object> map : list) {
//            System.out.println(map);
//        }
//    }
//
//    // orcale 上级团队可以查询下级团队所有资源 META_OBJ_ATTR  dp组件
//    @Test
//    public void META_OBJ_ATTR1()  throws Exception {
//        List<String> inputParams = Arrays.asList(new String[]{"%data_trans_database%", "%tablefile%", "%dp_dev_file%", "%dam_gb_dim%"});
//        String INPUT_PARAM_Team = "%team_%"; //条件查询团队
//        for (String inputParam : inputParams) {
//            List<Map<String, Object>> list = getMaps_Meta_SELETE(inputParam, INPUT_PARAM_Team, "META_OBJ_ATTR");
//            for (Map<String, Object> map : list) {
//                String INPUT_PARAM = (String) map.get("INPUT_PARAM");
//                //  请在对应元模型修改，值显示当前团队，目前改为  team_code ='{teamId}'
//                //   显示本团队以及下级团队资源 team_code in ({teamIdStr})
//                if (INPUT_PARAM.contains("{teamId}")) {
//                    INPUT_PARAM = "SELECT DISTINCT T3.* FROM ( " + INPUT_PARAM;
//                    switch (inputParam) {
//                        case "%data_trans_database%": case "%dam_gb_dim%":
//                            INPUT_PARAM = INPUT_PARAM.replaceAll("\\=\'\\{teamId\\}\'", " IN (\\{teamIdStr\\}) ");
//                    break;
//                    case "%tablefile%":
//                            INPUT_PARAM = INPUT_PARAM.replaceAll("\\=\'\\{teamId\\}\'", " IN (\\{teamIdStr\\}) AND STATE = 'PUBLISH' ");
//                        break;
//                    case "%dp_dev_file%":
//                            INPUT_PARAM = INPUT_PARAM.replaceAll("\\=\'\\{teamId\\}\'", " IN (\\{teamIdStr\\}) AND FILE_STATUS = 'PUBLISH' ");
//                        break;
//                    default:
//                        break;
//                    }
//                    INPUT_PARAM += " ) T3 ";
//                    // 只有变化的语句才去执行删除，增加操作
//                    LOG.info( inputParam + "的ID:" + map.get("ID") + "已变化：" + INPUT_PARAM);
////                    getMaps_Meta_Del_Insert(map, INPUT_PARAM, "META_OBJ_ATTR");
//                }
//            }
//        }
//    }
//
//    // 1. 先查询选出要处理的sql
//    private List<Map<String, Object>> getMaps_Meta_SELETE(String INPUT_PARAM1, String INPUT_PARAM_Team, String table)  throws Exception {
//        String sql=" SELECT  " +
//                " T1.ID AS ID, T1.ATTR_CN_NAME AS ATTR_CN_NAME, T1.ATTR_GROUP AS ATTR_GROUP, T1.ATTR_NAME AS ATTR_NAME, T1.ATTR_STATUS AS ATTR_STATUS, T1.CREATE_TIME AS CREATE_TIME, T1.CREATOR AS CREATOR, T1.DEFAULT_VAL AS DEFAULT_VAL, T1.INPUT_PARAM AS INPUT_PARAM, T1.INPUT_PARAM_TYPE AS INPUT_PARAM_TYPE, T1.INPUT_TYPE AS INPUT_TYPE, T1.IS_NULL AS IS_NULL, T1.IS_READONLY AS IS_READONLY, T1.OBJ_TYPE AS OBJ_TYPE, T1.REMARK AS REMARK, T1.SEQ AS SEQ, T1.DEPENDENT_CONDITIONS AS DEPENDENT_CONDITIONS, T1.DEFAULT_VAL_TYPE AS DEFAULT_VAL_TYPE, T1.CHILDREN_ATTRS AS CHILDREN_ATTRS" +
//                ", T2.FUNC_CODE FROM  " +
//                "( SELECT * FROM " + table + " moa WHERE moa.INPUT_PARAM LIKE ?  AND INPUT_PARAM LIKE ? ) T1 " +
//                "LEFT JOIN dp_proc_func_def_java T2  " +
//                "ON T1.obj_type = T2.FUNC_CODE " +
//                "WHERE T2.FUNC_CODE IS NOT NULL ORDER BY T1.OBJ_TYPE ";
//
//        //查询处理 “数据库”
//        return baseDao.queryForList(sql,  new Object[] {INPUT_PARAM1, INPUT_PARAM_Team});
//    }
//
//    // 2.先删除，再插入，不要update语句
//    private void getMaps_Meta_Del_Insert(Map<String, Object> map, String INPUT_PARAM, String table) throws SQLException {
//        String sqlDEL = " DELETE FROM " + table + " WHERE ID = '" + map.get("ID").toString() + "' " ;
//        baseDao2.executeUpdate(sqlDEL);
//
//        String sqlInsert = "INSERT INTO " + table + " ( " +
//                "ID, " +
//                "ATTR_CN_NAME, " +
//                "ATTR_GROUP, " +
//                "ATTR_NAME, " +
//                "ATTR_STATUS, " +
//                "CREATE_TIME, " +
//                "CREATOR, " +
//                "DEFAULT_VAL, " +
//                "INPUT_PARAM, " +
//                "INPUT_PARAM_TYPE, " +
//                "INPUT_TYPE, " +
//                "IS_NULL, " +
//                "IS_READONLY, " +
//                "OBJ_TYPE, " +
//                "REMARK, " +
//                "SEQ, " +
//                "DEPENDENT_CONDITIONS, " +
//                "DEFAULT_VAL_TYPE, " +
//                "CHILDREN_ATTRS" +
//                ") " +
//                "VALUES " +
//                " ( " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ?, " +
//                "  ? " +
//                " )";
//        baseDao2.executeUpdate(sqlInsert, new Object[]{
//                (String) map.get("ID"),
//                (String) map.get("ATTR_CN_NAME"),
//                (String) map.get("ATTR_GROUP"),
//                (String) map.get("ATTR_NAME"),
//                (String) map.get("ATTR_STATUS"),
//                (String) map.get("CREATE_TIME"),
//                (String) map.get("CREATOR"),
//                (String) map.get("DEFAULT_VAL"),
//                INPUT_PARAM,
//                (String) map.get("INPUT_PARAM_TYPE"),
//                (String) map.get("INPUT_TYPE"),
//                (String) map.get("IS_NULL"),
//                (String) map.get("IS_READONLY"),
//                (String) map.get("OBJ_TYPE"),
//                (String) map.get("REMARK"),
//                Integer.parseInt(map.get("SEQ").toString()),
//                (String) map.get("DEPENDENT_CONDITIONS"),
//                (String) map.get("DEFAULT_VAL_TYPE"),
//                (String) map.get("CHILDREN_ATTRS")
//        });
//    }
//
//    // 直接处理本团队资源，  前提去掉脚本中的 DISTINCT
//    // 在外面加上 NPUT_PARAM = "SELECT DISTINCT T3.* FROM ( " + INPUT_PARAM;                INPUT_PARAM += " ) T3 ";
//    @Test
//    public void META_OBJ_ATTR2()  throws Exception {
//        List<String> inputParams = Arrays.asList(new String[]{"%data_trans_database%", "%tablefile%", "%dp_dev_file%", "%dam_gb_dim%"});
//        String INPUT_PARAM_Team = "%team_%"; //条件查询团队
//        for (String inputParam : inputParams) {
//            List<Map<String, Object>> list = getMaps_Meta_SELETE(inputParam, INPUT_PARAM_Team, "META_OBJ_ATTR");
//            for (Map<String, Object> map : list) {
//                String INPUT_PARAM = (String) map.get("INPUT_PARAM");
//                //  请在对应元模型修改，值显示当前团队，目前改为  team_code ='{teamId}'
//                //   显示本团队以及下级团队资源 team_code in ({teamIdStr})
//                    INPUT_PARAM = "SELECT DISTINCT T3.* FROM ( " + INPUT_PARAM;
//                    INPUT_PARAM += " ) T3 ";
//                    // 只有变化的语句才去执行删除，增加操作
//                    LOG.info( inputParam + "的ID:" + map.get("ID") + "已变化：" + INPUT_PARAM);
//                    getMaps_Meta_Del_Insert(map, INPUT_PARAM, "META_OBJ_ATTR");
//                }
//            }
//        }
//
//        // 下级团队资源， 不再需要 INPUT_PARAM = "SELECT DISTINCT T3.* FROM ( " + INPUT_PARAM;                INPUT_PARAM += " ) T3 ";
//    @Test
//    public void META_OBJ_ATTR2_2()  throws Exception {
//        List<String> inputParams = Arrays.asList(new String[]{"%data_trans_database%", "%tablefile%", "%dp_dev_file%", "%dam_gb_dim%"});
//        String INPUT_PARAM_Team = "%team_%"; //条件查询团队
//        for (String inputParam : inputParams) {
//            List<Map<String, Object>> list = getMaps_Meta_SELETE(inputParam, INPUT_PARAM_Team, "META_OBJ_ATTR");
//            for (Map<String, Object> map : list) {
//                String INPUT_PARAM = (String) map.get("INPUT_PARAM");
//                //  请在对应元模型修改，值显示当前团队，目前改为  team_code ='{teamId}'
//                //   显示本团队以及下级团队资源 team_code in ({teamIdStr})
//                if (INPUT_PARAM.contains("{teamId}")) {
//
//                    switch (inputParam) {
//                        case "%data_trans_database%": case "%dam_gb_dim%":
//                            INPUT_PARAM = INPUT_PARAM.replaceAll("\\=\'\\{teamId\\}\'", " IN (\\{teamIdStr\\}) ");
//                            break;
//                        case "%tablefile%":
//                            INPUT_PARAM = INPUT_PARAM.replaceAll("\\=\'\\{teamId\\}\'", " IN (\\{teamIdStr\\}) AND STATE = 'PUBLISH' ");
//                            break;
//                        case "%dp_dev_file%":
//                            INPUT_PARAM = INPUT_PARAM.replaceAll("\\=\'\\{teamId\\}\'", " IN (\\{teamIdStr\\}) AND FILE_STATUS = 'PUBLISH' ");
//                            break;
//                        default:
//                            break;
//                    }
//
//                    // 只有变化的语句才去执行删除，增加操作
//                    LOG.info( inputParam + "的ID:" + map.get("ID") + "已变化：" + INPUT_PARAM);
//                    getMaps_Meta_Del_Insert(map, INPUT_PARAM, "META_OBJ_ATTR");
//                }
//            }
//        }
//    }
//
//    // 本级团队，也要对也要加上已经发布的资源处理
//    @Test
//    public void META_OBJ_ATTR3()  throws Exception {
//        List<String> inputParams = Arrays.asList(new String[]{"%data_trans_database%", "%tablefile%", "%dp_dev_file%", "%dam_gb_dim%"});
//        String INPUT_PARAM_Team = "%team_%"; //条件查询团队
//        for (String inputParam : inputParams) {
//            List<Map<String, Object>> list = getMaps_Meta_SELETE(inputParam, INPUT_PARAM_Team, "META_OBJ_ATTR");
//            for (Map<String, Object> map : list) {
//                String INPUT_PARAM = (String) map.get("INPUT_PARAM");
//                //  请在对应元模型修改，值显示当前团队，目前改为  team_code ='{teamId}'
//                //   显示本团队以及下级团队资源 team_code in ({teamIdStr})
//                if (INPUT_PARAM.contains("{teamId}")) {
//                    String INPUT_PARAM2 = "";
//                    switch (inputParam) {
//                        case "%data_trans_database%": case "%dam_gb_dim%":
//                            INPUT_PARAM2 = INPUT_PARAM;
//                            break;
//                        case "%tablefile%":
//                            INPUT_PARAM2 = INPUT_PARAM.replaceAll("\\=\'\\{teamId\\}\'", "\\=\'\\{teamId\\}\' AND STATE = 'PUBLISH' ");
//                            break;
//                        case "%dp_dev_file%":
//                            INPUT_PARAM2 = INPUT_PARAM.replaceAll("\\=\'\\{teamId\\}\'", "\\=\'\\{teamId\\}\' AND FILE_STATUS = 'PUBLISH' ");
//                            break;
//                        default:
//                            break;
//                    }
//                    if(!INPUT_PARAM2.equals(INPUT_PARAM)) {
//                        // 只有变化的语句才去执行删除，增加操作
//                        LOG.info( inputParam + "的ID:" + map.get("ID") + "已变化：" + INPUT_PARAM2);
//                        getMaps_Meta_Del_Insert(map, INPUT_PARAM2, "META_OBJ_ATTR");
//                    }
//                }
//            }
//        }
//    }
//
//    // oracle 数据交换到 dam
//    @Test
//    public void META_OBJ_ATTR4()  throws Exception {
//        String sql1 = "select " +
//                " T1.ID AS ID, T1.ATTR_CN_NAME AS ATTR_CN_NAME, T1.ATTR_GROUP AS ATTR_GROUP, T1.ATTR_NAME AS ATTR_NAME, T1.ATTR_STATUS AS ATTR_STATUS, T1.CREATE_TIME AS CREATE_TIME, T1.CREATOR AS CREATOR, T1.DEFAULT_VAL AS DEFAULT_VAL, T1.INPUT_PARAM AS INPUT_PARAM, T1.INPUT_PARAM_TYPE AS INPUT_PARAM_TYPE, T1.INPUT_TYPE AS INPUT_TYPE, T1.IS_NULL AS IS_NULL, T1.IS_READONLY AS IS_READONLY, T1.OBJ_TYPE AS OBJ_TYPE, T1.REMARK AS REMARK, T1.SEQ AS SEQ, T1.DEPENDENT_CONDITIONS AS DEPENDENT_CONDITIONS, T1.DEFAULT_VAL_TYPE AS DEFAULT_VAL_TYPE, T1.CHILDREN_ATTRS AS CHILDREN_ATTRS" +
//                " from meta_obj_attr T1 where obj_type IN(select func_code FROM dp_proc_func_def_java)  ORDER BY LOWER(obj_type), seq ";
//        List<Map<String, Object>> list = baseDao.queryForList(sql1);
//        for (Map<String, Object> map : list) {
//            String INPUT_PARAM = (String) map.get("INPUT_PARAM");
//            LOG.info(INPUT_PARAM);
//            getMaps_Meta_Del_Insert(map, INPUT_PARAM, "META_OBJ_ATTR");
//        }
//    }
//
//    // 多数据源 使用， 取出 "帮助文档" 到 mysql
//    @Test
//    public void dp_proc_func_def_java1()  throws Exception {
//        String sql = "SELECT id AS ID, FUNC_CODE AS FUNC_CODE, REMARK AS REMARK FROM DP_PROC_FUNC_DEF_JAVA";
//        List<Map<String, Object>> maps = baseDao.queryForList(sql);
//        for (Map<String, Object> map : maps) {
//            String REMARK = (String) map.get("REMARK");
//            String dataBaseType = "mysql"; // 这里暂时手工维护， 对应baseDao2
//            // 转义 ‘ 号
//            if(REMARK.contains("\'")) {
//                switch (dataBaseType) {
//                    case "mysql":
//                        System.out.println(REMARK);
//                        REMARK = REMARK.replaceAll("\'", "\\\'");
//                        System.out.println(REMARK);
//                        break;
//                    default:
//                        break;
//                }
//
//            }
//
//            String sql2 = "UPDATE DP_PROC_FUNC_DEF_JAVA SET REMARK='" + (String) map.get("REMARK") + "' WHERE FUNC_CODE = '" + (String) map.get("FUNC_CODE") + "'";
////            LOG.info("执行修改语句:"+ sql2);
////            baseDao2.executeUpdate(sql2);
//        }
//
//    }
//
//    @Test
//    public void dp_proc_func_def_()  throws Exception {
//        System.out.println(_2UuidUtils.getUUID());
//
//    }
//
//}