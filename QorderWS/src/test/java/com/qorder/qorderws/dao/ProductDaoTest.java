package com.qorder.qorderws.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
@Transactional
public class ProductDaoTest extends DBTestCase {

	@Autowired
	private IProductDAO testProductDAO;
	
	@Autowired
	private ICategoryDAO testCategoryDAO;
	
	@Autowired
	private DataSource testDataSource;
	
	public IProductDAO getProductDao() {
		return testProductDAO;
	}

	public void setProductDao(IProductDAO ProductDAO) {
		this.testProductDAO = ProductDAO;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/Dbunit/DemoDatabase.xml"));
	}

	/*
	 * Inserts XML dataset into the db before EACH test. if an item gets
	 * deleted, it will be reinserted before running the next test. Although, if
	 * an item is inserted manually, thus incrementing the id, the latter will
	 * be "consumed.
	 */
	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
	}
	
	@After
	public void restoreDB() {
		//DatabaseOperation
	}
	
	@Test
	public void testProductExists() throws ResourceNotFoundException {
			Product product = this.testProductDAO.findById(1);
			assertNotNull(product);	
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void testProductDoesNotExist() throws ResourceNotFoundException {
		Product product = testProductDAO.findById(1337);
		assertNull(product);
	}

	@Test
	public void testFetchProductsForCategory() throws ResourceNotFoundException {
		List<Product> productList = testCategoryDAO.findById(1).getProductList();
		assertNotNull(productList);
	}
	
	@Test
	public void testSuccessfulUpdate() throws ResourceNotFoundException {
		Product product = testProductDAO.findById(2);
		product.setDescription("updated desc");
		testProductDAO.update(product);
		
		Product product2 = testProductDAO.findById(2);
		assertEquals(product.getDescription(), product2.getDescription());
	}
	
	@Test(expected=ResourceNotFoundException.class)
	public void testSuccessfulDelete() throws ResourceNotFoundException {
		Product product = testProductDAO.findById(3);	
		testProductDAO.delete(product);
		
		Product product2 = testProductDAO.findById(3);
		assertNull(product2);
	}
	
	@Test
	public void testSuccessfulDescriptionUpdate() throws ResourceNotFoundException {
		Product product = this.testProductDAO.findById(1);
		product.getDetails().add("mauri");
		testProductDAO.update(product);
		
		Product product2 = this.testProductDAO.findById(1);
		boolean detailUpdated = product2.getDetails().stream().anyMatch((detail) -> {
			return detail.equals("mauri");
		});
		
		assertTrue(detailUpdated);
	}
	
	@Test
	public void testSuccessfulProductCreation() throws ResourceNotFoundException {
		Product product = new Product();
		product.setName("New simple Beer");
		product.setPrice(BigDecimal.valueOf(7));
		product.setDescription("Some description");
		
		Category someCategory = testCategoryDAO.findById(1);
		someCategory.addProduct(product);
		testCategoryDAO.save(someCategory);
		
		boolean productCreated = testCategoryDAO.findById(1).getProductList().stream()
				.anyMatch((fetchedProduct) -> {
					return fetchedProduct.equals(product);
				});
		
		assertTrue(productCreated);
	}
	
	 @Test(expected = ResourceNotFoundException.class)
	 public void testNoOrphansAfterCategoryDeleted() throws ResourceNotFoundException, IOException {
		 Category someCategory = testCategoryDAO.findById(3);
		 assertNotNull(someCategory);
		 
		 Long someProductId = someCategory.getProductList().get(0).getId();
		 
		 testCategoryDAO.delete(someCategory);
		 
		 Product product = testProductDAO.findById(someProductId);
		 assertNull(product);
	   }
	
}
