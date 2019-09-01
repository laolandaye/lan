package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DuoO2oManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DuoO2oManagerDao extends JpaRepository<DuoO2oManager, Integer>,
		JpaSpecificationExecutor<DuoO2oManager> {

}