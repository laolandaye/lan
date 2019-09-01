package com.lan.bmwai;


import com.lan.bmwai.dao.BaseDao;
import com.lan.bmwai.dao.BaseDaoImpl;
import com.lan.bmwai.dao.DruidUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * 测试逐条插入数据和批量插入数据的效率
 * @ClassName	:	TestInsert
 * @Description	:	TODO(这里用一句话描述这个类的作用)
 * @author		:	猿道教育Java学院院长-Will老师
 * @date		:	2018年7月26日 下午8:38:36
 */
public class TestInsert {
	public static void main(String[] args) throws Exception {
//		insertBatch1();//逐条插入
//		insertBatch2();//批量插入
		insertBatchTran22();//事务批量插入
//		insertBatchTran11();//事务批量插入
	}

	private static void insertBatch1()  throws SQLException {
		BaseDao baseDao = new BaseDaoImpl();
		String sql = "INSERT INTO emp_bk(empno,ename,job) VALUES(?,?,?)";
		List<Object[]> params = new ArrayList<Object[]>();
		for(int i = 9000; i < 9007; i++){
			params.add(new Object[]{i,"Newway"+i,"Student"});
		}
		// 总体而言批量插入速度比逐条插入速度要快，但是不是绝对的，要看内存、数据量大小

		System.out.println("【逐条插入】开始....");
		long startTime = System.currentTimeMillis();
		for(int i = 0; i<params.size(); i++){
			baseDao.executeUpdate(sql, params.get(i));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("逐条插入耗时：" + (endTime-startTime));

        baseDao.closeConnection();
	}

	private static void insertBatch2()  throws SQLException {
		BaseDao baseDao = new BaseDaoImpl();
		String sql = "INSERT INTO emp_bk(empno,ename,job) VALUES(?,?,?)";
		List<Object[]> params = new ArrayList<Object[]>();
		for(int i=8000; i<8007; i++){
			params.add(new Object[]{i,"Newway"+i,"Student"});
		}
		// 总体而言批量插入速度比逐条插入速度要快，但是不是绝对的，要看内存、数据量大小

		System.out.println("【批量插入】开始。。。。");
		long startTime2 = System.currentTimeMillis();
		baseDao.executeBatch(sql, params);
		long endTime2 = System.currentTimeMillis();
		System.out.println("批量插入耗时：" + (endTime2-startTime2));

        baseDao.closeConnection();
	}

	//这里抛异常版本（分层结构中不适用）
	private static void insertBatchTran1() {
		Connection conn = null;
		try {
			conn = DruidUtils.getConnection();
			// 开启事务
			DruidUtils.openTransaction();

			BaseDao baseDao = new BaseDaoImpl();
			String sql = "INSERT INTO emp_bk(empno,ename,job) VALUES(?,?,?)";
			List<Object[]> params = new ArrayList<Object[]>();
			for(int i = 9010; i < 9017; i++){
				params.add(new Object[]{i,"Newway"+i,"Student"});
			}
			// 总体而言批量插入速度比逐条插入速度要快，但是不是绝对的，要看内存、数据量大小

			System.out.println("【逐条插入】开始....");
			long startTime = System.currentTimeMillis();
			for(int i = 0; i<params.size(); i++){
				if(i == 3) {
					int a = i/0; //手动抛异常
				}
				baseDao.executeUpdate(sql, params.get(i));
			}
			long endTime = System.currentTimeMillis();
			System.out.println("逐条插入耗时：" + (endTime-startTime));

			DruidUtils.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			// 出现问题，进行事务回滚
			try {
				DruidUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// 事务提交
			try {
				DruidUtils.closeConnection(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

    private static void insertBatchTran11() throws Exception{
        Connection conn = conn = DruidUtils.getConnection();
        try {
            // 开启事务
            DruidUtils.openTransaction();

            BaseDao baseDao = new BaseDaoImpl();
            String sql = "INSERT INTO emp_bk(empno,ename,job) VALUES(?,?,?)";
            List<Object[]> params = new ArrayList<Object[]>();
            for(int i = 9010; i < 9017; i++){
                params.add(new Object[]{i,"Newway"+i,"Student"});
            }
            // 总体而言批量插入速度比逐条插入速度要快，但是不是绝对的，要看内存、数据量大小

            System.out.println("【逐条插入】开始....");
            long startTime = System.currentTimeMillis();
            for(int i = 0; i<params.size(); i++){
				if(i == 3) {
					int a = i/0; //手动抛异常
				}
                baseDao.executeUpdate(sql, params.get(i));
            }
            long endTime = System.currentTimeMillis();
            System.out.println("逐条插入耗时：" + (endTime-startTime));

            DruidUtils.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            // 出现问题，进行事务回滚
            DruidUtils.rollbackTransaction();
            throw new Exception(e);
        } finally {
            // 关闭连接
            DruidUtils.closeConnection(conn);
        }
    }

    private static void insertBatchTran2() {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            // 开启事务
            DruidUtils.openTransaction();

            BaseDao baseDao = new BaseDaoImpl();
            String sql = "INSERT INTO emp_bk(empno,ename,job) VALUES(?,?,?)";
            List<Object[]> params = new ArrayList<Object[]>();
            for(int i=8000; i<8007; i++){
                //手动抛异常
                if(i==8005) {
                    params.add(new Object[]{"8005--","Newway"+i,"Student"});
                } else {
                    params.add(new Object[]{i,"Newway"+i,"Student"});
                }
            }
            // 总体而言批量插入速度比逐条插入速度要快，但是不是绝对的，要看内存、数据量大小

            System.out.println("【批量插入】开始。。。。");
            long startTime2 = System.currentTimeMillis();
            baseDao.executeBatch(sql, params);
            long endTime2 = System.currentTimeMillis();
            System.out.println("批量插入耗时：" + (endTime2-startTime2));

            DruidUtils.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            // 出现问题，进行事务回滚
            try {
                DruidUtils.rollbackTransaction();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            // 事务提交
            try {
                DruidUtils.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertBatchTran22() throws Exception {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            // 开启事务
            DruidUtils.openTransaction();

            BaseDao baseDao = new BaseDaoImpl();
            String sql = "INSERT INTO emp_bk(empno,ename,job) VALUES(?,?,?)";
            List<Object[]> params = new ArrayList<Object[]>();
            for(int i=8000; i<8007; i++){
                //手动抛异常
                if(i==8005) {
                    params.add(new Object[]{"8005--","Newway"+i,"Student"});
                } else {
                    params.add(new Object[]{i,"Newway"+i,"Student"});
                }
            }
            // 总体而言批量插入速度比逐条插入速度要快，但是不是绝对的，要看内存、数据量大小

            System.out.println("【批量插入】开始。。。。");
            long startTime2 = System.currentTimeMillis();
            baseDao.executeBatch(sql, params);
            long endTime2 = System.currentTimeMillis();
            System.out.println("批量插入耗时：" + (endTime2-startTime2));

            DruidUtils.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            // 出现问题，进行事务回滚
            DruidUtils.rollbackTransaction();
            throw new Exception(e);
        } finally {
            DruidUtils.closeConnection(conn);
        }
    }
}
