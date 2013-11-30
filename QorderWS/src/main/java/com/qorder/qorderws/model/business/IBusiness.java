package com.qorder.qorderws.model.business;

import java.util.List;

import com.qorder.qorderws.model.category.ICategory;
import com.qorder.qorderws.model.menu.Menu;

public interface IBusiness {

	long getId();

	void setId(long id);

	String getName();

	void setName(String name);

	Menu getMenu();

	void setMenu(Menu menu);

	List<ICategory> getProductCategoryList();

	void setProductCategoryList(List<ICategory> productCategoryList);

	void addProductCategory(ICategory productCategory);

}
