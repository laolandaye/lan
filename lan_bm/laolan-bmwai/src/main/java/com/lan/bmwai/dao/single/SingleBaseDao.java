package com.lan.bmwai.dao.single;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * DAO:Data Access Object 数据访问对象
 * executeUpdate：  insert、update、delete封装到一个方法中
 * executeBatch：    批量插入  PreparedStatement
 * List<Map<String, Object>> queryForList：
 * <E> List<E> queryForList
 * <E> List<E> queryForList
 * Map<String, Object> queryForObject
 * <E> E queryForObject
 * <E> E queryForObject
 */
public interface SingleBaseDao {

    // insert、update、delete封装到一个方法中
    int executeUpdate(String sql, Object... params) throws SQLException;

    // 批量插入
    int[] executeBatch(String sql, List<Object[]> params) throws SQLException;

    /**
     * 查询封装到一个方法中
     *
     * @param sql    任意查询SQL语句
     * @param params 查询附带条件
     * @return List对应查询结果的表格数据 Map<String, Object>对应一行数据，String对应列名，Object对应列的值
     */
    List<Map<String, Object>> queryForList(String sql, Object... params);

    /**
     * 通过PRowMapper接口定义的规范，将每一行转换为对应的对象
     */
    <E> List<E> queryForList(SinglePRowMapper<E> mapper, String sql, Object... params);

    /**
     * 【补充】反射：在程序运行过程中动态调用类的方法或修改类的属性，后门
     * Java程序的运行原理：Hello.java  →编译→   Hello.class  → JVM
     * 				BaseDaoImpl2.java  →编译→   BaseDaoImpl2.class  → JVM
     * 【面向对象】类和对象？
     * 		类：具有【共同特征】和【共同行为】的同一类对象的集合。
     * 				类的属性		类的方法（构造方法、成员方法）
     * 				Field		Method
     * .class字节码文件是不是也具有共同的特征？
     * 		所有字节码文件的描述信息，我们可以归类到Class类中，【类的类】。
     * 		将所有.class字节码文件都可以看做对象，这一列对象具有共同特征，可以归类为一个类，Class类。
     *
     * 查询返回任意对象的List<Emp> list=  BaseDao的对象.queryForList(Emp.class,
     * "select * from emp_bk where empno=?",7936)
     * <p>
     * 调用示例 List<Dept> list =  queryForList(Dept.class,
     * "select * from dept_bk where empno=?",50)
     *
     */
    public <E> List<E> queryForList(Class<E> clazz, String sql, Object... params);

    /**
     * 得到单行数据，实际对应一个对象，查询结果只返回一行数据的时候，比如根据主键查询
     * 通过身份证获取用户信息  身份证号码   	PersonInfo
     */
    public Map<String, Object> queryForObject(String sql, Object... params);

    public <E> E queryForObject(SinglePRowMapper<E> mapper, String sql, Object... params);

    public <E> E queryForObject(Class<E> clazz, String sql, Object... params) ;

}
