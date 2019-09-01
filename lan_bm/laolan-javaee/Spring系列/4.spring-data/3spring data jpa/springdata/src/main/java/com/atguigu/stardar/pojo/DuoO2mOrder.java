package com.atguigu.stardar.pojo;

import javax.persistence.*;

@Table(name="DUO_O2M_ORDER")
@Entity
public class DuoO2mOrder {
	@GeneratedValue
	@Id
	private Integer id;
	@Column(name="ORDER_NAME")
	private String orderName;
	//映射单向 n-1 的关联关系
	//使用 @ManyToOne 来映射多对一的关联关系
	//使用 @JoinColumn 来映射外键.
	//可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	@JoinColumn(name="CUSTOMER_ID", foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
	@ManyToOne(fetch=FetchType.LAZY)
	private DuoO2mCustomer customer;


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

	public DuoO2mCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(DuoO2mCustomer customer) {
		this.customer = customer;
	}
}
