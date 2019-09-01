package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DanM2oOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DanM2oOrderDao extends JpaRepository<DanM2oOrder, Integer>,
        JpaSpecificationExecutor<DanM2oOrder> {

}
 