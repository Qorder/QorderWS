package com.qorder.qorderws.model.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.qorder.qorderws.model.product.Product;

@Entity
@Table(name="PRODUCT_HOLDER")
public class ProductHolder {
	
	@Id
	@GeneratedValue
	@Column(name="PRODUCT_HOLDER_ID")
	private long id;
	
	@Column(name="PRODUCT_QUANTITY")
	private int quantity;
	
	@Column(name="SELECTED_ATTRIBUTES")
	private String selectedAttributes;
	
	@Column(name="COMMENTS")
	private String notes;
	
	@ManyToOne(targetEntity=Product.class)
	@JoinColumn(name="PRODUCT_ID")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Product product;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSelectedAttributes() {
		return selectedAttributes;
	}

	public void setSelectedAttributes(String selectedAttributes) {
		this.selectedAttributes = selectedAttributes;
	}
	
	public BigDecimal getHoldingProductsPrice() {
		return product.getPrice().multiply(BigDecimal.valueOf(quantity));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductHolder other = (ProductHolder) obj;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}
