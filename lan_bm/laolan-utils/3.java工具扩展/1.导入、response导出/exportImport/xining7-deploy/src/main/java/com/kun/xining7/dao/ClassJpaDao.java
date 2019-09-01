package com.kun.xining7.dao;

import com.kun.xining7.po.ClassPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClassJpaDao extends JpaRepository<ClassPo, Integer>,JpaSpecificationExecutor<ClassPo>{

    //获取所有班级(grade=&pageNo=&pageSize=)
    @Query(nativeQuery = true,
            value =" SELECT c.id as id,c.class_no as classNo,c.class_name as className, g.id as gradeId,g.grade_no as gradeGradeNo,g.grade_name as gradeGradeName,g.order as gradeOrder " +
            " FROM tb_class c  LEFT JOIN tb_grade g ON c.grade_no = g.grade_no " +
            " ORDER BY g.grade_no, c.class_no " )
    List<Object> findAllClasses();

}
