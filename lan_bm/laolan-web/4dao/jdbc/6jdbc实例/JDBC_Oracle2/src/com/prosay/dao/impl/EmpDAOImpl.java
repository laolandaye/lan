package com.prosay.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialStruct;

import com.prosay.dao.EmpDAO;
import com.prosay.po.Emp;
import com.prosay.utils.JDBCUtils;

public class EmpDAOImpl implements EmpDAO {
	Connection conn = null;
	Statement st = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public int addEmp(Emp emp) throws SQLException {
		int n = -1;
		// 注意：在企业开发中尽量写上列名，以后表要增加列的时候，这条SQL语句不会报错
		String sql = "INSERT INTO emp_bk(empno,ename,job,mgr,hiredate,sal,comm,deptno) VALUES(?,?,?,?,?,?,?,?)";
		conn = JDBCUtils.getConnection();
		ps = conn.prepareStatement(sql);
		psSetInsertParams(emp);		// 给PreparedStatement对象设置插入语句的参数值
		n = ps.executeUpdate();
		JDBCUtils.closeAll(rs, ps, conn);
		return n;
	}

	/**
	 * 给PreparedStatement对象设置插入语句的参数值
	 * @param emp
	 * @throws SQLException
	 */
	private void psSetInsertParams(Emp emp) throws SQLException {
		ps.setInt(1, emp.getEmpno());
		ps.setString(2, emp.getEname());
		ps.setString(3, emp.getJob());
		ps.setInt(4, emp.getMgr());
		// Date类型本质是时间毫秒数，每个一毫秒计数增加1
		ps.setDate(5, new java.sql.Date(emp.getHiredate().getTime()));
		ps.setDouble(6, emp.getSal());
		ps.setDouble(7, emp.getComm());
		ps.setInt(8, emp.getDeptno());
	}

	@Override
	public int addBatch(List<Emp> emps) throws SQLException {
		// 注意：在企业开发中尽量写上列名，以后表要增加列的时候，这条SQL语句不会报错
		String sql = "INSERT INTO emp_bk(empno,ename,job,mgr,hiredate,sal,comm,deptno) VALUES(?,?,?,?,?,?,?,?)";
		conn = JDBCUtils.getConnection();
		ps = conn.prepareStatement(sql);
		for(Emp emp : emps){
			psSetInsertParams(emp);		// 给PreparedStatement对象设置插入语句的参数值
			ps.addBatch();				// 添加到批处理命令中
		}
		int[] results = ps.executeBatch();	// 执行批处理SQL
		JDBCUtils.closeAll(rs, ps, conn);
		return results.length;
	}

	@Override
	public int modifyEmp(Emp emp) throws SQLException {
		int n = -1;
		conn = JDBCUtils.getConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE emp_bk SET ename=?");
		sql.append(",job=?,mgr=?,");
		sql.append("hiredate=?,");
		sql.append("sal=?,comm=?,deptno=?");
		sql.append(" WHERE empno=?");
		ps = conn.prepareStatement(sql.toString());

		ps.setString(1, emp.getEname());
		ps.setString(2, emp.getJob());
		ps.setInt(3, emp.getMgr());
		// Date类型本质是时间毫秒数，每个一毫秒计数增加1
		ps.setDate(4, new java.sql.Date(emp.getHiredate().getTime()));
		ps.setDouble(5, emp.getSal());
		ps.setDouble(6, emp.getComm());
		ps.setInt(7, emp.getDeptno());
		ps.setInt(8, emp.getEmpno());

		n = ps.executeUpdate();
		JDBCUtils.closeAll(rs, ps, conn);
		return n;
	}

	@Override
	public int removeEmp(int id) throws SQLException {
		int n = -1;
		conn = JDBCUtils.getConnection();
		ps = conn.prepareStatement("DELETE FROM emp_bk WHERE empno=?");
		ps.setInt(1, id);
		n = ps.executeUpdate();
		JDBCUtils.closeAll(rs, ps, conn);
		return n;
	}

	@Override
	public List<Emp> queryAll() throws SQLException {
		List<Emp> list = new ArrayList<Emp>();
		conn = JDBCUtils.getConnection();
		ps = conn.prepareStatement("SELECT * FROM emp_bk");
		rs = ps.executeQuery();
		resultSet2List(list);		// 将结果集转换为Emp的list
		JDBCUtils.closeAll(rs, st, conn);
		return list;
	}

	/**
	 * 将结果集转换为Emp的list
	 * @param list
	 * @throws SQLException
	 */
	private void resultSet2List(List<Emp> list) throws SQLException {
		while (rs != null && rs.next()) {
			int empno = rs.getInt(1);
			String ename = rs.getString("ename");
			String job = rs.getString("job");
			int mgr = rs.getInt("mgr");
			Date hiredate = rs.getDate("hiredate");
			double sal = rs.getDouble("sal");
			double comm = rs.getDouble("comm");
			int deptno = rs.getInt("deptno");
			list.add(new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno));
		}
	}

	@Override
	public List<Emp> queryLike(String name) throws SQLException {
		List<Emp> list = new ArrayList<>();
		String sql = "SELECT * FROM emp_bk where ename like ?";
		conn = JDBCUtils.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, "%"+name+"%");
		rs = ps.executeQuery();
		resultSet2List(list);		// 将结果集转换为Emp的list
		JDBCUtils.closeAll(rs, ps, conn);
		return list;
	}

	/*@Override
	public List<Emp> queryCombo(Emp emp) throws SQLException  {
		List<Emp> list = new ArrayList<>();
		if (emp == null) {		// 没有填写任何查询条件，默认查询所有
			list = queryAll();
			return list;
		}
		StringBuffer sql = new StringBuffer();
		// 养成习惯sql语句拼接时前后留空格
		sql.append("SELECT * FROM emp_bk where 1=1 ");
		conn = JDBCUtils.getConnection();
		st = conn.createStatement();
		if (emp.getEname() != null) {
			sql.append(" and ename='" +emp.getEname() +"'");
		}
		if (emp.getJob() != null) {
			sql.append(" and job='" +emp.getJob() +"'");
		}
		// …… 根据需要无限拼接
		rs = st.executeQuery(sql.toString());
		resultSet2List(list);		// 将结果集转换为Emp的list
		JDBCUtils.closeAll(rs, st, conn);
		return list;
	}*/
	@Override
	public List<Emp> queryCombo(Emp emp) throws SQLException  {
		List<Emp> list = new ArrayList<>();
		if (emp == null) {		// 没有填写任何查询条件，默认查询所有
			list = queryAll();
			return list;
		}
		StringBuffer sql = new StringBuffer();
		// 养成习惯sql语句拼接时前后留空格
		sql.append("SELECT * FROM emp_bk where 1=1 ");
		conn = JDBCUtils.getConnection();
		List<Object> params = new ArrayList<>();
		if (emp.getEname() != null) {
			sql.append(" and ename=?");
			params.add(emp.getEname());
		}
		if (emp.getJob() != null) {
			sql.append(" and job=?");
			params.add(emp.getJob());
		}
		ps = conn.prepareStatement(sql.toString());
		// 设置参数，参数的个数到底多少个，参数顺序问题
		for(int i=0; i<params.size(); i++){
			ps.setObject((i+1), params.get(i));
		}

		// …… 根据需要无限拼接
		rs = ps.executeQuery();
		resultSet2List(list);		// 将结果集转换为Emp的list
		JDBCUtils.closeAll(rs, ps, conn);
		return list;
	}

}
