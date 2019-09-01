package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DuoO2mCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DuoO2mCustomerDao extends JpaRepository<DuoO2mCustomer, Integer>,
        JpaSpecificationExecutor<DuoO2mCustomer> {

}
 