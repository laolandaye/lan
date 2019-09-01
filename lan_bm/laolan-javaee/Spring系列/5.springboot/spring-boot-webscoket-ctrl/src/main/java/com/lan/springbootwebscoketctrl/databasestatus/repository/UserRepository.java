package com.lan.springbootwebscoketctrl.databasestatus.repository;

import com.lan.springbootwebscoketctrl.databasestatus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//继承JpaRepository来完成对数据库的操作
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "SELECT COUNT(1) from tbl_user ", nativeQuery = true)
    public Object getCount();
}
