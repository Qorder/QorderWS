package com.qorder.qorderws.dao;

import java.io.FileInputStream;
import java.util.ArrayList;
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

import com.qorder.qorderws.exception.OrderDoesNotExistException;
import com.qorder.qorderws.model.order.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class OrderDaoTest extends DBTestCase {
	
	@Autowired
	private IOrderDAO testOrderDAO;
	@Autowired
	private DataSource testDataSource;

	public IOrderDAO getOrderDao() {
		return testOrderDAO;
	}

	public void setOrderDao(IOrderDAO orderDAO) {
		this.testOrderDAO = orderDAO;
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/java/com/qorder/qorderws/dao/DemoORDERS.xml"));
	}
	
	/* Inserts XML dataset into the db before EACH test. if an item gets deleted, it will be reinserted
	 * before running the next test. Although, if an item is inserted manually, thus incrementing the id,
	 * the latter will be "consumed. 
	*/
	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
	}

	@After
	public void tearDown() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
		DatabaseOperation.DELETE.execute(connection, getDataSet());
	}
	
	@Test
	public void testFetchOrderForBusiness() throws OrderDoesNotExistException{
		List<Order> orderList= new ArrayList<Order>();
		orderList =	this.testOrderDAO.fetchOrderForBusiness(1);
		assertEquals("25", orderList.get(0).getTableNumber());
		assertEquals(1, orderList.size());
		
	}

	@Test
	public void testGetSessionFactory() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSessionFactory() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	

}	