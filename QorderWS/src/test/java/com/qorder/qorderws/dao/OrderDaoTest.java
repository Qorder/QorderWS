package com.qorder.qorderws.dao;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.qorder.qorderws.repository.IOrderDAO;
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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.model.order.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
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
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/Dbunit/DbunitOrders.xml"));
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

	@Test
	public void testFetchOrderForBusiness() throws ResourceNotFoundException {
		List<Order> orderList = testOrderDAO.fetchOrdersForBusiness(1);
		Order someOrder = orderList.get(0);
		assertNotNull(someOrder);
	}

	@Test
	public void testSave() throws ResourceNotFoundException {
		Business someBusiness = new Business();
		someBusiness.setId(1L);
		
		Order someorder = new Order();
		someorder.setBusiness(someBusiness);
		someorder.setTableNumber("50B");
		testOrderDAO.save(someorder);
		
		boolean orderPersisted = testOrderDAO.fetchOrdersForBusiness(1L).stream()
                        .anyMatch((order) -> { 
					return order.getTableNumber().equals("50B");
				});
		assertTrue(orderPersisted);
	}

	@Test
	public void testFindById() throws ResourceNotFoundException {
		assertEquals("25", this.testOrderDAO.findById(1).getTableNumber());
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testFindByIdDoesntExist() throws ResourceNotFoundException {
		this.testOrderDAO.findById(3000);
	}

	@Test
	public void testFetchPendingOrdersForBusiness() throws ResourceNotFoundException {
		List<Order> orderList = new ArrayList<Order>();
		orderList = this.testOrderDAO.fetchOrdersByStatus(2,
				EOrderStatus.PENDING);
		assertEquals(2, orderList.size());
		assertEquals("15", orderList.get(0).getTableNumber());
	}

	@Test
	public void testFetchAcceptedOrdersForBusiness() throws ResourceNotFoundException {
		List<Order> orderList = new ArrayList<Order>();
		orderList = this.testOrderDAO.fetchOrdersByStatus(1,
				EOrderStatus.ACCEPTED);
		assertEquals(2, orderList.size());
		assertEquals("25", orderList.get(0).getTableNumber());
	}

	@Test
	public void testFetchServicedOrdersForBusiness()
			throws ResourceNotFoundException {
		List<Order> orderList = new ArrayList<Order>();
		orderList = this.testOrderDAO.fetchOrdersByStatus(2,
				EOrderStatus.SERVICED);
		assertEquals(1, orderList.size());
		assertEquals("17", orderList.get(0).getTableNumber());
	}

}