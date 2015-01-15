package com.qorder.qorderws.model.menu;

import com.qorder.qorderws.model.category.Category;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MENUS")
public class Menu implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name = "MENU_ID")
	private Long id;
	
	@OneToMany(targetEntity = Category.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name ="FK_MENU_ID")
	private List<Category> categoryList = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean addCategory(Category category) {
		return categoryList.add(category);
	}
	
	public void removeCategory(Category category) {
		categoryList.remove(category);
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
