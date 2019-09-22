package com.lan.po;

import javax.persistence.*;
import java.util.Date;

@Table(name="CUTOMERS")
@Entity
public class Customer {
	
	private Integer id;
	private String lastName;

	private String email;
	private int age;
	
	private Date createdTime;
	private Date birth;
	
	private String aa;
	
//	@Column(name = "id") 如果与表名一致可省略
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Integer getId() {
		return id;
	}
	@Column(name = "last_name", length = 50, nullable = false)
	public String getLastName() {
		return lastName;
	}
	@Column(name = "email", length = 254, nullable = false)
	public String getEmail() {
		return email;
	}
	@Column(name = "age", length = 11, nullable = false)
	public int getAge() {
		return age;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", nullable = false)
	public Date getCreatedTime() {
		return createdTime;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "birth", nullable = false)
	public Date getBirth() {
		return birth;
	}

	//工具方法. 不需要映射为数据表的一列. 
	@Transient
	public String getInfo(){
		return "lastName: " + lastName + ", email: " + email;
	}
	@Transient
	public String getAa() {
		return aa;
	}




	public void setAa(String aa) {
		this.aa = aa;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "dan_many2one_Customer [id=" + id + ", lastName=" + lastName + ", email=" + email + ", age=" + age + ", createdTime="
				+ createdTime + ", birth=" + birth + "]";
	}


}
