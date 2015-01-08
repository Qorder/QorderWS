package com.qorder.qorderws.entities;

import com.qorder.qorderws.model.order.ProductHolder;
import com.qorder.qorderws.model.product.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductHolderEqualityTest {

	private ProductHolder productHolder1;
	private ProductHolder productHolder2;
	
	@Before
	public void setUp() throws Exception {
		productHolder1 = new ProductHolder();
		productHolder2 = new ProductHolder();
	}

	@After
	public void tearDown() throws Exception {
		productHolder1 = null;
		productHolder2 = null;
	}

	@Test
	public void SuccessfulProductHolderEqualitytest() {
		System.out.println("Successful productHolder equality test");
		
		productHolder1.setNotes("Customer notes");
		productHolder2.setNotes("Customer notes");
		
		Product product = new Product();
		product.addDetail("Product detail");
		product.setName("MyProduct");
		product.setPrice(BigDecimal.valueOf(1.99));
		
		productHolder1.setProduct(product);
		productHolder2.setProduct(product);
		
		boolean equalityResult = productHolder1.equals(productHolder2);
		assertTrue(equalityResult);
	}
	
	@Test
	public void FailedProductHolderEqualitytestByDifNotes() {
		System.out.println("Failed productHolder equality test by note difference");
		
		productHolder1.setNotes("Customer notes");
		productHolder2.setNotes("Other customer notes");
		
		Product product = new Product();
		product.addDetail("Product detail");
		product.setName("MyProduct");
		product.setPrice(BigDecimal.valueOf(1.99));
		
		productHolder1.setProduct(product);
		productHolder2.setProduct(product);
		
		boolean equalityResult = productHolder1.equals(productHolder2);
		assertFalse(equalityResult);
	}
	
	@Test
	public void FailedProductHolderEqualitytestByDifProduct() {
		System.out.println("Failed productHolder equality test by product difference");
		
		productHolder1.setNotes("Customer notes");
		productHolder2.setNotes("Customer notes");
		
		Product product1 = new Product();
		product1.setName("MyProduct 1");
		product1.addDetail("Product detail");
		product1.setPrice(BigDecimal.valueOf(1.99));
		
		Product product2 = new Product();
		product2.setName("MyProduct 2");
		product2.addDetail("Product detail");
		product2.setPrice(BigDecimal.valueOf(2.99));
		
		productHolder1.setProduct(product1);
		productHolder2.setProduct(product2);
		
		boolean equalityResult = productHolder1.equals(productHolder2);
		assertFalse(equalityResult);
	}
	
	@Test
	public void FailedProductHolderEqualitytestByNullNote() {
		System.out.println("Failed productHolder equality test by null note");
		
		productHolder1.setNotes(null);
		productHolder2.setNotes("Customer notes");
		
		Product product = new Product();
		product.addDetail("Product detail");
		product.setName("MyProduct");
		product.setPrice(BigDecimal.valueOf(1.99));
		
		productHolder1.setProduct(product);
		productHolder2.setProduct(product);
		
		boolean equalityResult = productHolder1.equals(productHolder2);
		assertFalse(equalityResult);
	}
	
	@Test
	public void FailedProductHolderEqualitytestByNullProduct() {
		System.out.println("Failed productHolder equality test by null product");
		
		productHolder1.setNotes("Customer notes");
		productHolder2.setNotes("Customer notes");
		
		Product product1 = new Product();
		product1.setName("MyProduct 1");
		product1.addDetail("Product detail");
		product1.setPrice(BigDecimal.valueOf(1.99));
		
		Product product2 = null;
		
		productHolder1.setProduct(product1);
		productHolder2.setProduct(product2);
		
		boolean equalityResult = productHolder1.equals(productHolder2);
		assertFalse(equalityResult);
	}

}
