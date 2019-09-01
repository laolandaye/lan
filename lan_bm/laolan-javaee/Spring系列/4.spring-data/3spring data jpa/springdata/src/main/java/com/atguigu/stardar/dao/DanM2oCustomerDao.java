package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DanM2oCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DanM2oCustomerDao extends JpaRepository<DanM2oCustomer, Integer>,
        JpaSpecificationExecutor<DanM2oCustomer> {

}
 