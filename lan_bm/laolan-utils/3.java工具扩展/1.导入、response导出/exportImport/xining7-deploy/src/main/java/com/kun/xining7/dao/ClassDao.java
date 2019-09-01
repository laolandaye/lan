package com.kun.xining7.dao;

import com.kun.framework.core.jdbc.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ClassDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getDataSource() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate();
        }
        return jdbcTemplate;
    }

    //获取所有班级(grade=&pageNo=&pageSize=)
    public List<Map<String, Object>> findAllClasses() {
        String sql = "SELECT c.id as id,c.class_no as classNo,c.class_name as className, g.id as gradeId,g.grade_no as gradeGradeNo,g.grade_name as gradeGradeName,g.order as gradeOrder " +
                " FROM tb_class c  LEFT JOIN tb_grade g ON c.grade_no = g.grade_no " +
                " ORDER BY g.grade_no, c.class_no ";
        return getDataSource().queryForList(sql);
    }
}
