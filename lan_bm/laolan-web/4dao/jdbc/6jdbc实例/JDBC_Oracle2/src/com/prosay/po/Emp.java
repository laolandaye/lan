package com.prosay.po;

import java.util.Date;

/**
 * 实体类，三个一一对应（类名与表名，类中成员属性于表的列（名称和数据库类型），表中的一行数据对应类的一个实例） 代码自动生成工具，可以根据建表语句生成实体类
 *
 * @ClassName : Emp
 * @Description : TODO(这里用一句话描述这个类的作用)
 * @author : 猿道教育Java学院院长-Will老师
 * @date : 2018年7月17日 下午10:22:23 需要本课视频的同学加【猿道王牌助理--Shirley老师】QQ：1916054912
 *       需要本课视频的同学加【猿道一级助理--Anna老师】QQ：2123479036 <a
 *       href="https://ke.qq.com/course/204364#tuin=626ac804">Java免费直播分享入场券</a>
 */
public class Emp {
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private Date hiredate; // 特别注意
	private double sal;
	private double comm;
	private int deptno;
	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", hiredate=" + hiredate
				+ ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno + "]";
	}
	public Emp(){}
	public Emp(int empno, String ename, String job, int mgr, Date hiredate, double sal, double comm, int deptno) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public double getComm() {
		return comm;
	}
	public void setComm(double comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
}
