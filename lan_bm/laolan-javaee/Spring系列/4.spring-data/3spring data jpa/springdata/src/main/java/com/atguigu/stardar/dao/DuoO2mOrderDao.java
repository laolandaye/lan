package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DuoO2mOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DuoO2mOrderDao extends JpaRepository<DuoO2mOrder, Integer>,
        JpaSpecificationExecutor<DuoO2mOrder> {

}
 