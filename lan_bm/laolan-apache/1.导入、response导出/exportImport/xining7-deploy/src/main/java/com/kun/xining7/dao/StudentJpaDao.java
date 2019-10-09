package com.kun.xining7.dao;

import com.kun.xining7.po.ClassPo;
import com.kun.xining7.po.StudentPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentJpaDao extends JpaRepository<StudentPo, Integer>,JpaSpecificationExecutor<StudentPo>{


    //更具activity_id条件删除
    @Modifying
    @Query(value = " DELETE FROM tb_student WHERE id IN ( :ids ) ", nativeQuery = true)
    Integer deleteStudentes(@Param("ids") List<String> ids) throws Exception;

}
