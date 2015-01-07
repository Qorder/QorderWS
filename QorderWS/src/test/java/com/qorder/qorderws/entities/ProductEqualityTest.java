package com.qorder.qorderws.entities;

import com.qorder.qorderws.model.product.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductEqualityTest {

	private Product product1;

	private Product product2;

	@Before
	public void setUp() throws Exception {
		product1 = new Product();
		product2 = new Product();
	}

	@After
	public void tearDown() throws Exception {
		product1 = null;
		product2 = null;
	}

	@Test
	public void SuccessfulProductEqualitytest() {
		System.out.println("Successful product equality test");

		product1.setName("ProductName");
		product1.setPrice(BigDecimal.valueOf(1.1));
		product1.addDetail("Detail 1");
		product1.addDetail("Detail 2");
		product1.setDescription("Extended description for product 1");

		product2.setName("ProductName");
		product2.setPrice(BigDecimal.valueOf(1.1));
		product2.addDetail("Detail 1");
		product2.addDetail("Detail 2");
		product2.setDescription("Extended description for product 2");

		boolean equalityResult = product1.equals(product2);
		assertTrue(equalityResult);
	}

	@Test
	public void FailProductEqualitytestByName() {
		System.out.println("Failed product equality test by name");

		product1.setName("ProductName");
		product1.setPrice(BigDecimal.valueOf(1.1));
		product1.addDetail("Detail 1");
		product1.addDetail("Detail 2");


		product2.setName("OtherProductName");
		product2.setPrice(BigDecimal.valueOf(1.1));
		product2.addDetail("Detail 1");
		product2.addDetail("Detail 2");

		boolean equalityResult = product1.equals(product2);
		assertFalse(equalityResult);
	}

	@Test
	public void FailProductEqualitytestByPrice() {
		System.out.println("Failed product equality test by price");

		product1.setName("ProductName");
		product1.setPrice(BigDecimal.valueOf(1.1));
		product1.addDetail("Detail 1");
		product1.addDetail("Detail 2");

		product2.setName("ProductName");
		product2.setPrice(BigDecimal.valueOf(2.1));
		product2.addDetail("Detail 1");
		product2.addDetail("Detail 2");

		boolean equalityResult = product1.equals(product2);
		assertFalse(equalityResult);
	}

	@Test
	public void FailProductEqualitytestByOneMoreDescription() {
		System.out.println("Failed product equality test by one more description");
		
		product1.setName("ProductName");
		product1.setPrice(BigDecimal.valueOf(1.1));
		product1.addDetail("Detail 1");
		product1.addDetail("Detail 2");
		product1.addDetail("Detail 3");

		product2.setName("ProductName");
		product2.setPrice(BigDecimal.valueOf(1.1));
		product2.addDetail("Detail 1");
		product2.addDetail("Detail 2");

		boolean equalityResult = product1.equals(product2);
		assertFalse(equalityResult);
	}
	
	@Test
	public void FailProductEqualitytestByOneLessDescription() {
		System.out.println("Failed product equality test by one less description");
		
		product1.setName("ProductName");
		product1.setPrice(BigDecimal.valueOf(1.1));
		product1.addDetail("Detail 1");

		product2.setName("ProductName");
		product2.setPrice(BigDecimal.valueOf(1.1));
		product2.addDetail("Detail 1");
		product2.addDetail("Detail 2");

		boolean equalityResult = product1.equals(product2);
		assertFalse(equalityResult);
	}
	
	@Test
	public void FailProductEqualitytestByNullProductName() {
		System.out.println("Failed product equality test by one less description");
		
		product1.setName(null);
		product1.setPrice(BigDecimal.valueOf(1.1));
		product1.addDetail("Detail 1");
		product1.addDetail("Detail 2");

		product2.setName("ProductName");
		product2.setPrice(BigDecimal.valueOf(1.1));
		product2.addDetail("Detail 1");
		product2.addDetail("Detail 2");

		boolean equalityResult = product1.equals(product2);
		assertFalse(equalityResult);
	}
	
	@Test
	public void FailProductEqualitytestByNullDescriptionList() {
		System.out.println("Failed product equality test by one less description");
		
		product1.setName(null);
		product1.setPrice(BigDecimal.valueOf(1.1));
		product1.addDetail(null);

		product2.setName("ProductName");
		product2.setPrice(BigDecimal.valueOf(1.1));
		product2.addDetail("Detail 1");
		product2.addDetail("Detail 2");

		boolean equalityResult = product1.equals(product2);
		assertFalse(equalityResult);
	}
	
	
	

}
