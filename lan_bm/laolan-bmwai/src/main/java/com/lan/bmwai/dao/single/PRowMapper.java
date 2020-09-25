package com.lan.bmwai.dao.single;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 行数据映射接口：定义一套规范和标准，具体实现有开发人员根据需求实现
 * 		ResultSet，转换为用户需要的任意实体对象
 * @ClassName	:	PRowMapper2
 */
public interface PRowMapper<T> {
	public T mappingRow(ResultSet rs, int rownum) throws SQLException;
}
