package com.kun.xining7.dao;

import com.kun.framework.core.jdbc.JdbcTemplate;
import com.kun.utils.PageBean;
import com.kun.xining7.po.StudentPo;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.kun.framework.core.jdbc.datastore.SqlUpdate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class StudentDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getDataSource() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate();
        }
        return jdbcTemplate;
    }

    @Autowired
    private SqlUpdate sqlUpdate;

    //分页获得学生信息
    public PageBean<Map<String, Object>> findAllStudents(int pageNo, int pageSize, int classNo, String key) {
        String sql = " FROM tb_student s left JOIN tb_class c ON s.class_no = c.class_no LEFT JOIN tb_grade g ON g.grade_no = c.grade_no WHERE 2 > 1 " ;
        if(classNo != 0) {
            sql += " and s.class_no = "+ classNo;
        }
        if(!"".equals(key)) {
            sql += " AND  ( ";
            sql += " s.student_id LIKE '%" + key + "%' or ";
            sql += " s.name LIKE '%" + key + "%' or ";
            sql += " s.nation LIKE '%" + key + "%' or ";
            sql += " s.origin LIKE '%" + key + "%' or ";
            sql += " s.address LIKE '%" + key + "%' or ";
            sql += " s.contacts_name LIKE '%" + key + "%' ";
            sql += " ) ";
        }
        String columnSql = " SELECT s.id, s.student_id AS studentId, s.name, s.sex, s.nation, s.origin, s.address, s.contacts_name AS contactsName, s.contacts_phone AS contactsPhone, s.class_no AS classNo, c.class_name as className,  g.grade_name AS gradeName ";
        columnSql += sql;
        columnSql += " limit " + (pageNo - 1) * pageSize + "," + pageSize;
        List<Map<String, Object>> listMap = getDataSource().queryForList(columnSql);
        String countSql = " SELECT count(1) as total ";
        countSql += sql;
        List<Map<String, Object>> totalList = getDataSource().queryForList(countSql);
        int total = Integer.valueOf(totalList.get(0).get("total") + "");
        int totalPage = (total - 1) / pageSize + 1;
        return new PageBean<>(listMap, totalPage, pageNo, pageSize, total);
    }

    //查询一个班级学生信息班级学生
    public List<Map<String, Object>> findStudentsByGroupClass(int classNo) {
        String sql = "SELECT s.student_id as studentId, s.name, CASE  WHEN s.sex = UPPER('F') THEN '男' ELSE '女' END AS sex,s.nation, s.origin, s.address, s.contacts_name AS contactsName, s.contacts_phone AS contactsPhone, c.class_name AS className, g.grade_name AS gradeName\n" +
                "FROM tb_student s LEFT JOIN tb_class c ON c.class_no = s.class_no LEFT JOIN tb_grade g ON c.grade_no = g.grade_no WHERE s.class_no = " + classNo;
        return getDataSource().queryForList(sql);
    }

    public  Object insertBatchStudents(List<List<String>> excelDataList, Integer classNo) throws IOException {
        String sqlStart = "INSERT INTO tb_student (student_id, name, sex, nation, origin, address, contacts_name, contacts_phone, class_no) VALUES ( ";
        String sqlEnd = " ) ";

        //批量执行sql，拼接
        List<String> sqls = new ArrayList<String>();

        if (excelDataList != null && excelDataList.size() > 0) {
            for (int j = 1; j < excelDataList.size(); j++) {
                String values = "";
                List<String> fielDataTypeList = excelDataList.get(j);
                for (int i = 0; i < fielDataTypeList.size(); i++) {
                    values += " '" + excelDataList.get(j).get(i) + "', ";
                }
                String sql2 = sqlStart + values + " '" + classNo + "' " + sqlEnd;
                sqls.add(sql2);
            }
        }
        return sqlUpdate.batchExecute("", sqls);
    }

    public  Object insertBatch(List<List<String>> excelDataList) throws IOException {
        String sqlStart = " INSERT INTO zs_company (id, company_name, company_address, company_type, employe_num, invest_num, invest_total, assets_total, residence) VALUES ( ";
        String sqlEnd = " ) ";

        //批量执行sql，拼接
        List<String> sqls = new ArrayList<String>();

        if (excelDataList != null && excelDataList.size() > 0) {
            for (int j = 1; j < excelDataList.size(); j++) {
                String values = "";
                List<String> fielDataTypeList = excelDataList.get(j);
                for (int i = 0; i < fielDataTypeList.size(); i++) {
                    values += " '" + excelDataList.get(j).get(i) + "', ";
                }
                String sql2 = sqlStart + values + sqlEnd;
                sqls.add(sql2);
            }
        }
        return sqlUpdate.batchExecute("", sqls);
    }
}
