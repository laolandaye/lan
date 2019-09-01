package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DanO2mOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DanO2mOrderDao extends JpaRepository<DanO2mOrder, Integer>,
        JpaSpecificationExecutor<DanO2mOrder> {

}
 