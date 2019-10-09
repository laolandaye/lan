package com.kun.xining7.dao;

import com.kun.xining7.po.ActivityMediaPo;
import com.kun.xining7.po.ActivityPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityMediaJpaDao extends JpaRepository<ActivityMediaPo, Long>,JpaSpecificationExecutor<ActivityMediaPo>{

    //更具activity_id条件删除
    @Modifying
    @Query(value = " DELETE FROM tb_activity_media WHERE activity_id = :activityId ", nativeQuery = true)
    Integer deleteMediaByActivityId(@Param("activityId") Integer activityId) throws Exception;

}
