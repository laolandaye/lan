package com.atguigu.stardar.pojo;

import javax.persistence.*;

@Table(name="DUO_O2O_DEPARTMENT")
@Entity
public class DuoO2oDepartment {

	@GeneratedValue
	@Id
	private Integer id;

	@Column(name="DEPT_NAME")
	private String deptName;

	//使用 @OneToOne 来映射 1-1 关联关系。
	//若需要在当前数据表中添加主键则需要使用 @JoinColumn 来进行映射.
	//注意, 1-1 关联关系, 所以需要添加 unique=true
	@JoinColumn(name="MGR_ID", unique=true, foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
	@OneToOne(cascade=CascadeType.ALL)
//	@OneToOne(fetch=FetchType.LAZY)
	private DuoO2oManager mgr;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public DuoO2oManager getMgr() {
		return mgr;
	}

	public void setMgr(DuoO2oManager mgr) {
		this.mgr = mgr;
	}
}
