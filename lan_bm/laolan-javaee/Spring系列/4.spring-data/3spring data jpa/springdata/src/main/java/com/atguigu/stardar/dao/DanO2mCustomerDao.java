package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DanO2mCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DanO2mCustomerDao extends JpaRepository<DanO2mCustomer, Integer>,
        JpaSpecificationExecutor<DanO2mCustomer> {

}
 