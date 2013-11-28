package com.qorder.qorderws.model.category;

import java.util.List;

import com.qorder.qorderws.model.product.IProduct;

public interface ICategory {

	long getId();

	void setId(long id);

	String getName();

	void setName(String name);

	void addProduct(IProduct product);

	List<IProduct> getProductList();

	void setProductList(List<IProduct> productList);

}
