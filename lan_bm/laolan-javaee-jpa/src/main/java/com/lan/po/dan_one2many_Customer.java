package com.lan.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name="ONE2MANY_CUTOMERS")
@Entity
public class dan_one2many_Customer {

	private Integer id;
	private String lastName;

	private String email;
	private int age;

	private Date createdTime;
	private Date birth;

	private Set<dan_one2many_Order> orders = new HashSet<>();

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "last_name", length = 50, nullable = false)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	//映射单向 1-n 的关联关系
	//	1.使用 @OneToMany 来映射 1-n 的关联关系;使用 @JoinColumn 来映射外键列的名称
	//	2.可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	//	3.可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略. 
	//注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用 @JoinColumn 属性了. 
	@JoinColumn(name="CUSTOMER_ID")
//	@OneToMany
//	@OneToMany(fetch=FetchType.EAGER)
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.REMOVE})
	public Set<dan_one2many_Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<dan_one2many_Order> orders) {
		this.orders = orders;
	}

}
