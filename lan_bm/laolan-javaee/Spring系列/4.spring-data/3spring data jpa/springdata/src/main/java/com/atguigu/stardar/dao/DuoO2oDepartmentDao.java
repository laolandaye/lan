package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DuoO2mCustomer;
import com.atguigu.stardar.pojo.DuoO2oDepartment;
import com.atguigu.stardar.pojo.DuoO2oManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.*;

public interface DuoO2oDepartmentDao extends JpaRepository<DuoO2oDepartment, Integer>,
		JpaSpecificationExecutor<DuoO2oDepartment> {

}