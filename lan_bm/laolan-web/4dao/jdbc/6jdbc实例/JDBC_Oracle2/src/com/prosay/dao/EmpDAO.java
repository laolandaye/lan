package com.prosay.dao;

import java.sql.SQLException;
import java.util.List;

import com.prosay.po.Emp;

/**
 * DAO：Data Access Object数据访问对象
 * 	接口：定义操作数据库的规范和标准 CRUD
 * @ClassName	:	EmpDAO
 * @Description	:	TODO(这里用一句话描述这个类的作用)
 * @author		:	猿道教育Java学院院长-Will老师
 * @date		:	2018年7月17日 下午10:28:38
 *         需要本课视频的同学加【猿道王牌助理--Shirley老师】QQ：1916054912
 *	                     需要本课视频的同学加【猿道一级助理--Anna老师】QQ：2123479036
 *<a href="https://ke.qq.com/course/204364#tuin=626ac804">Java免费直播分享入场券</a>
 */
public interface EmpDAO {
	int addEmp(Emp emp) throws SQLException;
	int addBatch(List<Emp> emps) throws SQLException;
	int modifyEmp(Emp emp) throws SQLException;
	int removeEmp(int id) throws SQLException;

	List<Emp> queryAll() throws SQLException; 				// 查询所有
	List<Emp> queryLike(String name) throws SQLException;	// 根据姓名模糊查找
	List<Emp> queryCombo(Emp emp) throws SQLException ;				// 组合查询，根据条件自动拼接
}
