package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DuoM2mCategory;
import com.atguigu.stardar.pojo.DuoM2mItem;
import com.atguigu.stardar.pojo.DuoO2mCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public interface DuoM2mCategoryDao extends JpaRepository<DuoM2mCategory, Integer>,
		JpaSpecificationExecutor<DuoM2mCategory> {

}