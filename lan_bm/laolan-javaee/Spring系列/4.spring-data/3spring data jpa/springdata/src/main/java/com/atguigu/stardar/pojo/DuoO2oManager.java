package com.atguigu.stardar.pojo;

import javax.persistence.*;

@Table(name="DUO_O2O_MANAGER")
@Entity
public class DuoO2oManager {

	@GeneratedValue
	@Id
	private Integer id;

	@Column(name="MGR_NAME")
	private String mgrName;

	//对于不维护关联关系, 没有外键的一方, 使用 @OneToOne 来进行映射, 建议设置 mappedBy="mgr"
	@OneToOne(mappedBy="mgr", cascade=CascadeType.ALL)
//	@OneToOne(mappedBy="mgr",fetch=FetchType.LAZY)
	private DuoO2oDepartment dept;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMgrName() {
		return mgrName;
	}

	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}

	public DuoO2oDepartment getDept() {
		return dept;
	}

	public void setDept(DuoO2oDepartment dept) {
		this.dept = dept;
	}
}
