package com.atguigu.stardar.pojo;

import javax.persistence.*;

@Table(name="DAN_O2M_ORDER")
@Entity
public class DanO2mOrder {
	@GeneratedValue
	@Id
	private Integer id;
	@Column(name="ORDER_NAME")
	private String orderName;


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

}
