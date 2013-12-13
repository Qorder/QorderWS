package com.qorder.qorderws.dao;

import java.io.FileInputStream;

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

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.product.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class ProductDaoTest extends DBTestCase {
	
 
		
		@Autowired
		private IBusinessDAO testBusinessDAO;
		@Autowired
		private DataSource testDataSource;
		private Business testBus;

		private Product testProd;

		public IBusinessDAO getBusinessDao() {
			return testBusinessDAO;
		}

		public void setBusinessDao(IBusinessDAO businessDAO) {
			this.testBusinessDAO = businessDAO;
		}
		
		@Override
		protected IDataSet getDataSet() throws Exception {
			return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/java/com/qorder/qorderws/dao/DemoDatabase.xml"));
		}
		
		/* Inserts XML dataset into the db before EACH test. if an item gets deleted, it will be reinserted
		 * before running the next test. Although, if an item is inserted manually, thus incrementing the id,
		 * the latter will be "consumed. 
		*/
		@Before
		public void setUp() throws Exception {
			IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
			DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
			this.testProd = new Product();
		}
		
		@Test
		public void testGetProductList() throws BusinessDoesNotExistException {
			this.testBus=testBusinessDAO.findById(1);
			//System.out.println(this.testBus.getCategoryList().get(0).getProductList().get(0).getAttributeList().get(0).getDescription());
			assertNotNull(this.testBus.getCategoryList().get(1).getProductList());
		}
		
		@Test
		public void testGetProductListThatIsNull() throws BusinessDoesNotExistException {
			this.testBus=testBusinessDAO.findById(3);
			assertEquals(0,this.testBus.getCategoryList().get(0).getProductList().size());
		}
		
		@Test
		public void testAddProduct() throws BusinessDoesNotExistException {
			this.testProd.setName("mpyra5");
			this.testBus = this.testBusinessDAO.findById(3);
			this.testBus.getCategoryList().get(0).addProduct(testProd);
			testBusinessDAO.update(this.testBus);
			this.testBus = this.testBusinessDAO.findById(3);
			assertEquals(1, this.testBus.getCategoryList().get(0).getProductList().size());
		}
		
		@Test
		public void testDeleteProduct() throws BusinessDoesNotExistException {
			this.testBus = this.testBusinessDAO.findById(1);
			this.testBus.getCategoryList().get(0).getProductList().remove(0);
			this.testBusinessDAO.update(this.testBus);
			assertEquals(3, this.testBus.getCategoryList().get(0).getProductList().size());
			
		}
		
		@Test
		public void testUpdateProductName() throws BusinessDoesNotExistException {
			this.testBus = this.testBusinessDAO.findById(2);
			this.testBus.getCategoryList().get(0).getProductList().get(0).setName("allagiOnomatos");
			this.testBusinessDAO.update(this.testBus);
			this.testBus = this.testBusinessDAO.findById(2);
			assertEquals("allagiOnomatos", this.testBus.getCategoryList().get(0).getProductList().get(0).getName());
		}
		
		@Test
		public void testDeleteAllProducts() throws BusinessDoesNotExistException {
			this.testBus = this.testBusinessDAO.findById(1);
			this.testBus.getCategoryList().get(1).getProductList().clear();
			this.testBusinessDAO.update(this.testBus);
			assertEquals(0, this.testBus.getCategoryList().get(1).getProductList().size());	
		}
		

}
