package com.lan.bmwai;

import com.lan.bmwai.dao.BaseDao;
import com.lan.bmwai.dao.BaseDaoImpl;
import com.lan.bmwai.po.Emp;
import com.lan.bmwai.po.StuClassDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;

public class BaseDaoTest {

	private static final Logger LOG = LoggerFactory.getLogger(BaseDaoTest.class);

	/**
	 * queryForList 返回多个
	 * queryForObject 返回单个
	 */
	public static void main(String[] args) {
		queryForList1();
		queryForList3();
		queryForObject3();
	}

	private static void queryForList1() {
		BaseDao baseDao = new BaseDaoImpl();

		String sql = "  SELECT * FROM emp  ";
		List<Map<String, Object>> list = baseDao.queryForList(sql);

		for(Map emp:list){
			System.out.println(emp);
		}

        baseDao.closeConnection();
	}

	private static void queryForList3() {
		BaseDao baseDao = new BaseDaoImpl();

		//返回实体类
		String sql = "SELECT * FROM emp_bk ORDER BY empno";
		List<Emp> list = baseDao.queryForList(Emp.class, sql);
		if (list == null || list.size()==0) {
			System.out.println("没有找到任何数据。");
			return;
		}
		for(Emp emp:list){
			System.out.println(emp);
		}

		//返回自定义实体类
		String sql2= "select s.S_ID sId, s.S_NAME sName, c.C_NAME cName from T_STU s LEFT JOIN T_CLASS c ON s.S_C_ID = c.C_ID ";
		List<StuClassDo> list2 = baseDao.queryForList(StuClassDo.class, sql2);
		for(StuClassDo stuClassDo: list2){
			System.out.println(stuClassDo);
		}

        baseDao.closeConnection();
	}

	private static void queryForObject3() {
		BaseDao baseDao = new BaseDaoImpl();

		//返回单个实体类
		String sql = " SELECT * FROM emp WHERE ROWNUM <= 1 ";
		Emp emp = baseDao.queryForObject(Emp.class, sql);
		System.out.println(emp);

//		//返回自定义实体类
		String sql2= "select s.S_ID sId, s.S_NAME sName, c.C_NAME cName from T_STU s LEFT JOIN T_CLASS c ON s.S_C_ID = c.C_ID  WHERE ROWNUM <= 1 ";
		StuClassDo stuClassDo = baseDao.queryForObject(StuClassDo.class, sql2);
		System.out.println(stuClassDo);

        baseDao.closeConnection();
	}
}
