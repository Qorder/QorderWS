package com.qorder.qorderws.dao;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.exception.ProductDoesNotExistException;
import com.qorder.qorderws.model.product.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class ProductDaoTest extends DBTestCase {

	@Autowired
	private IProductDAO testProductDAO;
	@Autowired
	private DataSource testDataSource;
	private Product testProd;

	public IProductDAO getProductDao() {
		return testProductDAO;
	}

	public void setProductDao(IProductDAO ProductDAO) {
		this.testProductDAO = ProductDAO;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream(
				"src/test/java/com/qorder/qorderws/dao/DemoDatabase.xml"));
	}

	/*
	 * Inserts XML dataset into the db before EACH test. if an item gets
	 * deleted, it will be reinserted before running the next test. Although, if
	 * an item is inserted manually, thus incrementing the id, the latter will
	 * be "consumed.
	 */
	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(
				testDataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		this.testProd = new Product();
	}
	
	//TODO : Tests gia periptoseis pou den iparxei to product
	
	@Test
	public void testExistsFindById() throws ProductDoesNotExistException
	{
		this.testProd = this.testProductDAO.findById(1);
		assertEquals("MPYRA1",this.testProd.getName());	
	}
	
	@Test(expected=ProductDoesNotExistException.class)
	public void testDoesNotExistFindById() throws ProductDoesNotExistException {
		this.testProd= this.testProductDAO.findById(1337);
	}
	
	@Test
	public void testFetchProductsForCategory() throws CategoryDoesNotExistException {
		List<Product> testProdList = testProductDAO.fetchProductsForCategory(1);
		assertEquals(4, testProdList.size());
		assertEquals("MPYRA4", testProdList.get(3).getName());
	}
	
	@Test
	public void testExistsUpdate() throws ProductDoesNotExistException {
		this.testProd.setId(5);
		this.testProd.setName("MPYRA5MINPWUPDATED15");
		this.testProductDAO.update(testProd);

		this.testProd = this.testProductDAO.findById(5);
		assertTrue(this.testProd.getName().contentEquals("MPYRA5MINPWUPDATED15"));
	}
	
	@Test(expected=ProductDoesNotExistException.class)
	public void testExistsDelete() throws ProductDoesNotExistException {
		this.testProd.setId(5);
		this.testProductDAO.delete(testProd);
		this.testProductDAO.findById(5);
	}
	
	//TODO : test gia attributes
	@Test
	public void testFetchProductsForCategoryAndDescriptions() throws CategoryDoesNotExistException {
		List<Product> testProdList = testProductDAO.fetchProductsForCategory(1);
		assertEquals(2, testProdList.get(0).getDescriptions().size());
		assertEquals("MIKRH",testProdList.get(0).getDescriptions().get(0));
	}
	
	@Test
	public void testExistsUpdateDescriptions() throws ProductDoesNotExistException {
		this.testProd = this.testProductDAO.findById(1);
		this.testProd.getDescriptions().add("mauri");
		this.testProductDAO.update(testProd);
		this.testProd = this.testProductDAO.findById(1);
		assertEquals("mauri",this.testProd.getDescriptions().get(2));
	}
	
	@Test
	public void testSaveProductsWithoutDescriptions() throws ProductDoesNotExistException{
		this.testProd = new Product();
		this.testProd.setName("mpyra6");
		this.testProd.setPrice(BigDecimal.valueOf(7));
		this.testProductDAO.save(testProd);
		this.testProd = this.testProductDAO.findById(6);
		assertTrue(this.testProd.getDescriptions().isEmpty());
		assertEquals("mpyra6",this.testProd.getName());
	}
	
	@Test
	public void testSaveProductsAndDescriptions() throws ProductDoesNotExistException{
		this.testProd = new Product();
		this.testProd.setName("mpyra7");
		this.testProd.setPrice(BigDecimal.valueOf(5));
		this.testProd.getDescriptions().add("save");
		this.testProductDAO.save(testProd);
		this.testProd = this.testProductDAO.findById(7);
		assertEquals("save",this.testProd.getDescriptions().get(0));
		assertEquals("mpyra7",this.testProd.getName());
	}
	
	
}
