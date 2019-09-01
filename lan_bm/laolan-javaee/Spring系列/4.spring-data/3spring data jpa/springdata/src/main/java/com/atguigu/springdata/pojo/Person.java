package com.atguigu.springdata.pojo;

import javax.persistence.*;
import java.util.Date;

@Table(name="JPA_PERSONS")
@Entity
public class Person {

	private Integer id;
	private String lastName;

	private String email;
	private Date birth;

	private Address address;
	private Integer addressId;
	
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}

	@Column(name="ADD_ID")
	public Integer getAddressId() {
		return addressId;
	}

	@JoinColumn(name="ADDRESS_ID", foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
	@ManyToOne
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
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

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", birth=" + birth +
				'}';
	}
}
