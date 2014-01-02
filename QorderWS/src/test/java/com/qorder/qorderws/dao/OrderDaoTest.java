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

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.OrderDoesNotExistException;
import com.qorder.qorderws.model.order.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class OrderDaoTest extends DBTestCase {
	
	@Autowired
	private IOrderDAO testOrderDAO;
	@Autowired
	private DataSource testDataSource;
	private Order order;

	public IOrderDAO getOrderDao() {
		return testOrderDAO;
	}

	public void setOrderDao(IOrderDAO orderDAO) {
		this.testOrderDAO = orderDAO;
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/Dbunit/DbunitOrders.xml"));
	}
	
	/* Inserts XML dataset into the db before EACH test. if an item gets deleted, it will be reinserted
	 * before running the next test. Although, if an item is inserted manually, thus incrementing the id,
	 * the latter will be "consumed. 
	*/
	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		this.order = new Order();
	}

	@After
	public void tearDown() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
		DatabaseOperation.DELETE.execute(connection, getDataSet());
	}
	
	@Test
	public void testFetchOrderForBusiness() throws BusinessDoesNotExistException{
		List<Order> orderList= new ArrayList<Order>();
		orderList =	this.testOrderDAO.fetchOrderForBusiness(1);
		assertEquals("25", orderList.get(0).getTableNumber());
		assertEquals(2, orderList.size());
		
	}
	
	@Test
	public void testSave() throws BusinessDoesNotExistException, OrderDoesNotExistException {
		     this.order.setTableNumber("50");
		     this.testOrderDAO.save(this.order);
		     assertEquals("50", this.testOrderDAO.findById(3).getTableNumber());
		    }
	
	@Test
	public void testFindById() throws OrderDoesNotExistException {
		assertEquals("25",this.testOrderDAO.findById(1).getTableNumber());
		}
	
	@Test(expected=OrderDoesNotExistException.class)
	   	public void testFindByIdDoesntExist() throws OrderDoesNotExistException {
	    this.testOrderDAO.findById(3000);
	    }
	
}