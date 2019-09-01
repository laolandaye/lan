package com.kun.xining7.dao;

import com.kun.xining7.po.ActivityMediaPo;
import com.kun.xining7.po.MediaPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaJpaDao extends JpaRepository<MediaPo, Long>,JpaSpecificationExecutor<MediaPo>{




}
