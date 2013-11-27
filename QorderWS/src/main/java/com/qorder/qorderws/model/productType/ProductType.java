package com.qorder.qorderws.model.productType;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qorder.qorderws.model.product.IProduct;
import com.qorder.qorderws.model.product.Product;

@Entity
@Table(name="PRODUCT_TYPE")
public class ProductType  implements IProductType{
	
	@Id
    @GeneratedValue
    @Column(name="PRODUCT_TYPE_ID")
	private long id;
	@Column(name="NAME")
	private String name;
	@OneToMany(targetEntity=Product.class, mappedBy="ProductType")
	private List<IProduct> productList = new ArrayList<IProduct>();	
	
		
	public long getId() {
		return this.id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void addProduct(IProduct product) {
		this.productList.add(product);
	}




	public void setProductList(List<IProduct> productList) {
		this.productList = productList;
	}



	@Override
	public List<IProduct> getProductList() {
		// TODO Auto-generated method stub
		return null;
	}


}
