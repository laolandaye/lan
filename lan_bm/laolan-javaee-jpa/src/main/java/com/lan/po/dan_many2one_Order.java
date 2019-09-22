package com.lan.po;

import com.lan.po.dan_many2one_Customer;

import javax.persistence.*;

@Table(name="MANY2ONE_ORDERS")
@Entity
public class dan_many2one_Order {
	@GeneratedValue
	@Id
	private Integer id;
	@Column(name="ORDER_NAME")
	private String orderName;

	//映射单向 n-1 的关联关系
	//使用 @ManyToOne 来映射多对一的关联关系， fetch 属性来修改默认的关联属性的加载策略
	//使用 @JoinColumn 来映射外键.
	@JoinColumn(name="CUSTOMER_ID")
//	@ManyToOne
//	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL, targetEntity = dan_many2one_Customer.class)
	private dan_many2one_Customer customer;


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


	public dan_many2one_Customer getCustomer() {
		return customer;
	}

	public void setCustomer(dan_many2one_Customer customer) {
		this.customer = customer;
	}

}
