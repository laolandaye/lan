package com.lan.bmwai.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
 */
public class BaseDaoImpl implements BaseDao {

    private static final Logger LOG = LoggerFactory.getLogger(BaseDaoImpl.class);
    private String jdbcDruid = "";


    public BaseDaoImpl() {
    }

    public BaseDaoImpl(String jdbcDruid) {
        this.jdbcDruid = jdbcDruid;
    }

    public String getJdbcDruid() {
        return jdbcDruid;
    }

    public void setJdbcDruid(String jdbcDruid) {
        this.jdbcDruid = jdbcDruid;
    }

    // 判断走哪个数据源
    private Connection getConnectionByTwo() {
        Connection conn;
        if("two".equals(jdbcDruid)) {
            conn = DruidUtils2.getConnection();
        } else {
            conn = DruidUtils.getConnection();
        }
        return conn;
    }

    private void closeConnectionByTwo(Connection conn) throws SQLException {
        if("two".equals(jdbcDruid)) {
            DruidUtils2.closeConnection(conn);
        } else {
            DruidUtils.closeConnection(conn);
        }
    }

    private void releaseByTwo(PreparedStatement ps) {
        if("two".equals(jdbcDruid)) {
            DruidUtils2.release(ps);
        } else {
            DruidUtils.release(ps);
        }
    }

    private void releaseByTwo(PreparedStatement ps, ResultSet rs) {
        if("two".equals(jdbcDruid)) {
            DruidUtils2.release(ps, rs);
        } else {
            DruidUtils.release(ps, rs);
        }
    }

    @Override
    public void closeConnection() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getConnectionByTwo();
            closeConnectionByTwo(conn);
        }  catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setParameters(PreparedStatement ps, Object[] params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }

    // insert、update、delete封装到一个方法中
    @Override
    public int  executeUpdate(String sql, Object... params) throws SQLException  {
        Connection conn = getConnectionByTwo();
        PreparedStatement ps = conn.prepareStatement(sql);
        setParameters(ps, params);
        int n = ps.executeUpdate();
        releaseByTwo(ps);
        return n;
    }


   /* public int executeUpdate(String sql, Object... params) {
        int n = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DruidUtils2.getConnection();
            ps = conn.prepareStatement(sql);
            setParameters(ps, params);
            n = ps.executeUpdate();
            return n;
        } catch (SQLException e) {
            e.printStackTrace();
            return n;
        }  finally {
            DruidUtils2.release(ps);
        }
    }*/

    // 批量插入
    @Override
    public int[] executeBatch(String sql, List<Object[]> params) throws SQLException  {
        Connection conn = getConnectionByTwo();
        PreparedStatement ps = conn.prepareStatement(sql);
        for (Object[] param : params) {
            setParameters(ps, param);
            ps.addBatch();
        }
        int [] n = ps.executeBatch();
        releaseByTwo(ps);
        return n;
    }

    /*public void executeBatch(String sql, List<Object[]> params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DruidUtils2.getConnection();
            ps = conn.prepareStatement(sql);
            for (Object[] param : params) {
                setParameters(ps, param);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            DruidUtils2.release(ps);
        }
    }*/

    /**
     * 查询封装到一个方法中
     *
     * @param sql    任意查询SQL语句
     * @param params 查询附带条件
     * @return List对应查询结果的表格数据 Map<String, Object>对应一行数据，String对应列名，Object对应列的值
     */
    @Override
    public List<Map<String, Object>> queryForList(String sql, Object... params) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnectionByTwo();
            ps = conn.prepareStatement(sql);
            setParameters(ps, params);
            rs = ps.executeQuery();            // ResultSet结果集对象，实质看做一张表数据
            // 获取元数据（包含查询结果集中的所有信息，不限于列别名，列的数量等等，包含表格相应信息）
            ResultSetMetaData rsmd = rs.getMetaData();
            int num = rsmd.getColumnCount();        // 获取结果中共有多少列
            Map<String, Object> obj;    // 解析得到的一行数据
            while (rs.next()) {
                obj = new HashMap<String, Object>();
                // 遍历所有列，得到键值对，放入obj中
                for (int i = 1; i <= num; i++) {
                    // 获取SQL语句中的列别名
                    String columnLabel = rsmd.getColumnLabel(i);
                    obj.put(columnLabel, rs.getObject(i));
                }
                result.add(obj);
            }
            LOG.info("查询总条数：" + result.size() + "条");
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        } finally {
            releaseByTwo(ps, rs);
        }
    }



    /**
     * 通过PRowMapper接口定义的规范，将每一行转换为对应的对象
     *
     * @param mapper
     * @param sql
     * @param params
     * @return
     */
    @Override
    public <E> List<E> queryForList(PRowMapper<E> mapper, String sql, Object... params) {
        List<E> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnectionByTwo();
            ps = conn.prepareStatement(sql);
            setParameters(ps, params);
            rs = ps.executeQuery();            // ResultSet结果集对象，实质看做一张表数据
            int rownum = 0;        // 行号
            while (rs.next()) {
                rownum++;
                result.add(mapper.mappingRow(rs, rownum));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        } finally {
            releaseByTwo(ps, rs);
        }
    }

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
     */

    /**
     * 查询返回任意对象的List<Emp> list=  BaseDao的对象.queryForList(Emp.class,
     * "select * from emp_bk where empno=?",7936)
     * <p>
     * 调用示例 List<Dept> list =  queryForList(Dept.class,
     * "select * from dept_bk where empno=?",50)
     *
     * @param clazz  Class
     * @param sql    任意查询SQL语句
     * @param params 参数
     * @return
     */
    @Override
    public <E> List<E> queryForList(Class<E> clazz, String sql, Object... params) {
        List<E> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnectionByTwo();
            ps = conn.prepareStatement(sql);
            setParameters(ps, params);
            rs = ps.executeQuery();            // ResultSet结果集对象，实质看做一张表数据
            // 获取元数据（包含查询结果集中的所有信息，不限于列别名，列的数量等等，包含表格相应信息）
            ResultSetMetaData rsmd = rs.getMetaData();
            int num = rsmd.getColumnCount();        // 获取结果中共有多少列
            E obj = null;
            // 通过反射构造一个对象添加到List中去
            Method[] methods = clazz.getMethods();

            // 通过方法获取所有属性（属性名，属性对应的方法）找出所有set方法及其属性值
            Map<String, Method> property = new HashMap<>();
            // property中绑定的是所有是set方法和对应的属性值
            Method method;
            for (int i = 0; i < methods.length; i++) {
                method = methods[i];        // 从所有方法中逐个取方法
                String methodName = method.getName();
                if (methodName.startsWith("set")) {
                    // Emp类中有个setEmpno,,, <empno,setEmpno方法的所有信息>
                    property.put(methodName.substring(3).toUpperCase(), method);
                }
            }

            while (rs.next()) {
                obj = clazz.newInstance();        // 调用无参构造方法实例化，所有属性值默认零值
                // 调用set方法给所有属性设置值（元数据中的所有列的值都必须设置到obj对象中去）
                for (int i = 1; i <= num; i++) {
                    String columnName = rsmd.getColumnLabel(i);
                    // 获取的列名和通过反射得到的类所有属性匹配，调用相应set方法修改obj的属性值
                    if (property.containsKey(columnName)) {
                        // 从property中获取set方法，并调用此方法设置obj的属性值
                        method = property.get(columnName);
                        // 通过反射调用method方法修改obj对象的属性值为rs.getObject(i)
                        //method.invoke(obj, rs.getInt(i));
                        // 获取set方法中的唯一一个参数的类型
                        String typeParam = method.getParameterTypes()[0].getSimpleName();
                        if ("int".equals(typeParam)) {
                            method.invoke(obj, rs.getInt(i));
                        } else if ("String".equals(typeParam)) {
                            method.invoke(obj, rs.getString(i));
                        } else if ("Date".equals(typeParam)) {
                            method.invoke(obj, rs.getDate(i));
                        } else if ("double".equals(typeParam)) {
                            method.invoke(obj, rs.getDouble(i));
                        }
                    }
                }
                result.add(obj);        // 行数据转换为对象添加到list中
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }  finally {
            releaseByTwo(ps, rs);
        }
    }

    /**
     * 得到单行数据，实际对应一个对象，查询结果只返回一行数据的时候，比如根据主键查询
     * 通过身份证获取用户信息  身份证号码   	PersonInfo
     *
     * @param sql
     * @param params
     * @return
     */
    @Override
    public Map<String, Object> queryForObject(String sql, Object... params) {
        Map<String, Object> result = new HashMap<String, Object>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnectionByTwo();
            ps = conn.prepareStatement(sql);
            setParameters(ps, params);
            rs = ps.executeQuery();            // ResultSet结果集对象，实质看做一张表数据
            // 获取元数据（包含查询结果集中的所有信息，不限于列别名，列的数量等等，包含表格相应信息）
            ResultSetMetaData rsmd = rs.getMetaData();
            int num = rsmd.getColumnCount();        // 获取结果中共有多少列

            if (rs.next()) {        // 可以使用while循环，一行数据的时候if(rs.next())
                for (int i = 1; i <= num; i++) {
                    String columnLabel = rsmd.getColumnLabel(i);
                    result.put(columnLabel, rs.getObject(i));
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }  finally {
            releaseByTwo(ps, rs);
        }
    }

    @Override
    public <E> E queryForObject(PRowMapper<E> mapper, String sql, Object... params) {
        E result = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnectionByTwo();
            ps = conn.prepareStatement(sql);
            setParameters(ps, params);
            rs = ps.executeQuery();            // ResultSet结果集对象，实质看做一张表数据
            int rownum = 0;
            if (rs.next()) {        // 可以使用while循环，一行数据的时候if(rs.next())
                rownum++;
                result = mapper.mappingRow(rs, rownum);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        } finally {
            releaseByTwo(ps, rs);
        }

    }

    @Override
    public <E> E queryForObject(Class<E> clazz, String sql, Object... params) {
        E obj = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnectionByTwo();
            ps = conn.prepareStatement(sql);
            setParameters(ps, params);
            rs = ps.executeQuery();            // ResultSet结果集对象，实质看做一张表数据
            // 获取元数据（包含查询结果集中的所有信息，不限于列别名，列的数量等等，包含表格相应信息）
            ResultSetMetaData rsmd = rs.getMetaData();
            int num = rsmd.getColumnCount();        // 获取结果中共有多少列

            // 通过反射构造一个对象添加到List中去
            Method[] methods = clazz.getMethods();

            // 通过方法获取所有属性（属性名，属性对应的方法）找出所有set方法及其属性值
            Map<String, Method> property = new HashMap<>();
            // property中绑定的是所有是set方法和对应的属性值
            Method method;
            for (int i = 0; i < methods.length; i++) {
                method = methods[i];        // 从所有方法中逐个取方法
                String methodName = method.getName();
                if (methodName.startsWith("set")) {
                    // Emp类中有个setEmpno,,, <empno,setEmpno方法的所有信息>
                    property.put(methodName.substring(3).toUpperCase(), method);
                }
            }

            while (rs.next()) {
                obj = clazz.newInstance();        // 调用无参构造方法实例化，所有属性值默认零值
                // 调用set方法给所有属性设置值（元数据中的所有列的值都必须设置到obj对象中去）
                for (int i = 1; i <= num; i++) {
                    String columnName = rsmd.getColumnLabel(i);
                    // 获取的列名和通过反射得到的类所有属性匹配，调用相应set方法修改obj的属性值
                    if (property.containsKey(columnName)) {
                        // 从property中获取set方法，并调用此方法设置obj的属性值
                        method = property.get(columnName);
                        // 通过反射调用method方法修改obj对象的属性值为rs.getObject(i)
                        //method.invoke(obj, rs.getInt(i));
                        // 获取set方法中的唯一一个参数的类型
                        String typeParam = method.getParameterTypes()[0].getSimpleName();
                        if ("int".equals(typeParam)) {
                            method.invoke(obj, rs.getInt(i));
                        } else if ("String".equals(typeParam)) {
                            method.invoke(obj, rs.getString(i));
                        } else if ("Date".equals(typeParam)) {
                            method.invoke(obj, rs.getDate(i));
                        } else if ("double".equals(typeParam)) {
                            method.invoke(obj, rs.getDouble(i));
                        }
                    }
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return obj;
        } finally {
            releaseByTwo(ps, rs);
        }
    }

}
