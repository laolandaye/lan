package com.atguigu.JpqlNamedQuerySql.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NamedQuery(name = "testNamedQuery", query = " FROM Customer c WHERE c.id = ? ")
@Cacheable(true)
@Table(name="JPA_CUTOMERS")
@Entity
public class Customer {

	@GeneratedValue(strategy= GenerationType.AUTO)
	@Id
	private Integer id;
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	private String email;
	private int age;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	@Temporal(TemporalType.DATE)
	private Date birth;

	//映射单向 1-n 的关联关系
	//	1.使用 @OneToMany 来映射 1-n 的关联关系;使用 @JoinColumn 来映射外键列的名称
	//	2.可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	//	3.可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略.
//	@JoinColumn(name="CUSTOMER_ID")
	@OneToMany
//	@OneToMany(fetch=FetchType.EAGER)
//	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.REMOVE})
//	@OneToMany(fetch= FetchType.LAZY,cascade={CascadeType.ALL},mappedBy="customer")
	private Set<Order> orders = new HashSet<>();


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


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


	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	
	public Customer() {
	}
	public Customer(String lastName, int age) {
		super();
		this.lastName = lastName;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", age=" + age +
				", createdTime=" + createdTime +
				", birth=" + birth +
				", orders=" + orders +
				'}';
	}
}
