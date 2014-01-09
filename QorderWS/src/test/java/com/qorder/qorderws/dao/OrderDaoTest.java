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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.OrderDoesNotExistException;
import com.qorder.qorderws.model.order.EOrderStatus;
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
		//Warning : change the ID each time you add an order in the DbunitOrders.xml file.
		assertEquals("50", this.testOrderDAO.findById(6).getTableNumber());
		}
	
	@Test
	public void testFindById() throws OrderDoesNotExistException {
		assertEquals("25",this.testOrderDAO.findById(1).getTableNumber());
		}
	
	@Test(expected=OrderDoesNotExistException.class)
	public void testFindByIdDoesntExist() throws OrderDoesNotExistException {
	    this.testOrderDAO.findById(3000);
	    }
	
	@Test
	public void testFetchPendingOrdersForBusiness() throws BusinessDoesNotExistException {
		List<Order> orderList= new ArrayList<Order>();		
		orderList = this.testOrderDAO.fetchOrdersByStatus(2, EOrderStatus.PENDING);
		assertEquals(2, orderList.size());
		assertEquals("15", orderList.get(0).getTableNumber());
	}
	
	@Test
	public void testFetchAcceptedOrdersForBusiness() throws BusinessDoesNotExistException {
		List<Order> orderList= new ArrayList<Order>();		
		orderList = this.testOrderDAO.fetchOrdersByStatus(1, EOrderStatus.ACCEPTED);
		assertEquals(2, orderList.size());
		assertEquals("25", orderList.get(0).getTableNumber());
	}

	@Test
	public void testFetchServicedOrdersForBusiness() throws BusinessDoesNotExistException {
		List<Order> orderList= new ArrayList<Order>();		
		orderList = this.testOrderDAO.fetchOrdersByStatus(2, EOrderStatus.SERVICED);
		assertEquals(1, orderList.size());
		assertEquals("17", orderList.get(0).getTableNumber());
	}
	
}