package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 行数据映射接口：定义一套规范和标准，具体实现有开发人员根据需求实现
 * 		ResultSet，转换为用户需要的任意实体对象
 * @ClassName	:	PRowMapper
 * @Description	:	TODO(这里用一句话描述这个类的作用)
 * @author		:	猿道教育Java学院院长-Will老师
 * @param <T>
 * @date		:	2018年7月24日 下午9:32:44
 */
public interface PRowMapper<T> {
	public T mappingRow(ResultSet rs,int rownum) throws SQLException;
}
