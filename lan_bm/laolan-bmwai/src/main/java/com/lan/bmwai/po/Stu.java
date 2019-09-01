package com.lan.bmwai.po;

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
public class Stu {
	private int sId;
	private String sName;
	private int cId;
	private int cIntroduceId;

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int getcIntroduceId() {
		return cIntroduceId;
	}

	public void setcIntroduceId(int cIntroduceId) {
		this.cIntroduceId = cIntroduceId;
	}
}
