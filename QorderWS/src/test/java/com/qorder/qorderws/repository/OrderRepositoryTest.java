package com.qorder.qorderws.repository;

import com.qorder.qorderws.WebServiceApplication;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.model.order.Order;
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
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebServiceApplication.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OrderRepositoryTest extends DBTestCase {

	@Autowired
	private IOrderRepository orderRepository;
	
	@Autowired
	private DataSource dataSource;


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
		IDatabaseConnection connection = new DatabaseDataSourceConnection(dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
	}

	@Test
	public void testFetchOrderForBusiness() {
		Business business = new Business();
		business.setId(1L);

		List<Order> orderList = orderRepository.findOrdersByBusiness(business);
		Order someOrder = orderList.get(0);
		assertNotNull(someOrder);
	}

	@Test
	public void testSave() {
		Business someBusiness = new Business();
		someBusiness.setId(1L);
		
		Order someOrder = new Order();
		someOrder.setBusiness(someBusiness);
		someOrder.setTableNumber("50B");
		orderRepository.save(someOrder);
		
		boolean orderPersisted = orderRepository.findOrdersByBusiness(someBusiness).stream()
                        .anyMatch((order) -> order.getTableNumber().equals(someOrder.getTableNumber()));
		assertTrue(orderPersisted);
	}

	@Test
	public void testFindById() {
		assertEquals("25", this.orderRepository.findOne(1L).getTableNumber());
	}

	@Test
	public void testFindByIdDoesntExist() {
		assertNull(orderRepository.findOne(3000L));
	}

	@Test
	public void testFetchPendingOrdersForBusiness() {
		Business business = new Business();
		business.setId(2L);

		List<Order> orderList = this.orderRepository.findOrdersByStatus(business, EOrderStatus.PENDING);
		assertEquals(2, orderList.size());
		assertEquals("15", orderList.get(0).getTableNumber());
	}

	@Test
	public void testFetchAcceptedOrdersForBusiness() {
		Business business = new Business();
		business.setId(1L);
		List<Order> orderList = this.orderRepository.findOrdersByStatus(business, EOrderStatus.ACCEPTED);
		assertEquals(2, orderList.size());
		assertEquals("25", orderList.get(0).getTableNumber());
	}

	@Test
	public void testFetchServicedOrdersForBusiness() {
		Business business = new Business();
		business.setId(2L);
		List<Order> orderList = this.orderRepository.findOrdersByStatus(business, EOrderStatus.SERVICED);
		assertEquals(1, orderList.size());
		assertEquals("17", orderList.get(0).getTableNumber());
	}

}