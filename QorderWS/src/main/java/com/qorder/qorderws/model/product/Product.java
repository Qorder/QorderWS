package com.qorder.qorderws.model.product;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID")
	private long id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PRICE")
	private BigDecimal price = new BigDecimal(0);

	@Column(name = "DETAILS")
	@CollectionTable(name = "PRODUCT_DETAILS", joinColumns = @JoinColumn(name = "FK_PRODUCT_ID"))
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> details = new ArrayList<String>();

	public Product(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public Product() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}
	
	public boolean addDetail(String detail) {
		return details.add(detail);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Product other = (Product) obj;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
}