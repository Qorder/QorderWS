package com.qorder.qorderws.repository;

import com.qorder.qorderws.WebServiceApplication;
import com.qorder.qorderws.configuration.BaseDbUnitTestCase;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebServiceApplication.class)
public class ProductRepositoryTest extends BaseDbUnitTestCase {

	@Autowired
	private IProductRepository productRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;


	
	@Test
	public void testProductExists() {
			boolean productExists = this.productRepository.exists(1L);
			assertNotNull(productExists);
	}
	
	@Test
	public void testProductDoesNotExist() {
		boolean productExists = productRepository.exists(1337L);
		assertFalse(productExists);
	}

	@Test
	public void testFetchProductsForCategory() {
		List<Product> productList = categoryRepository.findOne(1L).getProductList();
		assertNotNull(productList);
	}
	
	@Test
	public void testSuccessfulUpdate() {
		Product product = productRepository.findOne(2L);
		product.setDescription("updated desc");
		productRepository.save(product);
		
		Product product2 = productRepository.findOne(2L);
		assertEquals(product.getDescription(), product2.getDescription());
	}
	
	@Test
	public void testSuccessfulDelete() {
		Product product = productRepository.findOne(3L);
		productRepository.delete(product);
		
		Product product2 = productRepository.findOne(3L);
		assertNull(product2);
	}
	
	@Test
	public void testSuccessfulDescriptionUpdate() {
		Product product = productRepository.findOne(1L);
		product.getDetails().add("mauri");
		productRepository.save(product);
		
		Product product2 = productRepository.findOne(1L);
		boolean detailUpdated = product2.getDetails().stream().anyMatch((detail) -> {
			return detail.equals("mauri");
		});
		
		assertTrue(detailUpdated);
	}
	
	@Test
	public void testSuccessfulProductPersist() {
		Product product = new Product();
		product.setName("New simple Beer");
		product.setPrice(BigDecimal.valueOf(7));
		product.setDescription("Some description");

		Category someCategory = categoryRepository.findOne(1L);
		someCategory.addProduct(product);
		categoryRepository.save(someCategory);
		
		boolean productCreated = categoryRepository.findOne(1L).getProductList().stream()
				.anyMatch((fetchedProduct) -> fetchedProduct.getName().equals(product.getName()));
		assertTrue(productCreated);
	}
	
	 @Test
	 public void testNoOrphansAfterCategoryDeleted() {
		 Category someCategory = categoryRepository.findOne(3L);
		 assertNotNull(someCategory);
		 
		 Long someProductId = someCategory.getProductList().get(0).getId();
		 
		 categoryRepository.delete(someCategory);
		 
		 Product product = productRepository.findOne(someProductId);
		 assertNull(product);
	   }
	
}
