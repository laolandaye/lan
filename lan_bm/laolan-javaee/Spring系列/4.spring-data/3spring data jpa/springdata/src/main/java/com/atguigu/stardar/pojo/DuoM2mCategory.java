package com.atguigu.stardar.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name="DUO_M2M_CATEGORIE")
@Entity
public class DuoM2mCategory {

	@GeneratedValue
	@Id
	private Integer id;
	@Column(name="CATEGORY_NAME")
	private String categoryName;
	@ManyToMany(mappedBy="categories", cascade = CascadeType.ALL)
	@org.hibernate.annotations.ForeignKey(name = "none")
	private Set<DuoM2mItem> items = new HashSet<>();


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public Set<DuoM2mItem> getItems() {
		return items;
	}

	public void setItems(Set<DuoM2mItem> items) {
		this.items = items;
	}
}
