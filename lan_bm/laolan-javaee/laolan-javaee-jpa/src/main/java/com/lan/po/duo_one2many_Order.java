package com.lan.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="ONEMANY_ORDERS")
@Entity
public class duo_one2many_Order {
	@GeneratedValue
	@Id
	private Integer id;
	@Column(name="ORDER_NAME")
	private String orderName;

	//映射单向 n-1 的关联关系
	//使用 @ManyToOne 来映射多对一的关联关系
	//使用 @JoinColumn 来映射外键.
	//可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	@JoinColumn(name="CUSTOMER_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	private duo_one2many_Customer customer;


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


	public duo_one2many_Customer getCustomer() {
		return customer;
	}

	public void setCustomer(duo_one2many_Customer customer) {
		this.customer = customer;
	}
}
