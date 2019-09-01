package com.atguigu.stardar.pojo;

import javax.persistence.*;

@Table(name="DAN_M2O_ORDER")
@Entity
public class DanM2oOrder {
	@GeneratedValue
	@Id
	private Integer id;
	@Column(name="ORDER_NAME")
	private String orderName;

	//映射单向 n-1 的关联关系
	//使用 @ManyToOne 来映射多对一的关联关系， fetch 属性来修改默认的关联属性的加载策略
	//使用 @JoinColumn 来映射外键.
//	@JoinColumn(name="CUSTOMER_ID")
	@JoinColumn(name = "CUSTOMER_ID",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
//	@ManyToOne
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = DanM2oCustomer.class)
	private DanM2oCustomer customer;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}


	public DanM2oCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(DanM2oCustomer customer) {
		this.customer = customer;
	}

}
