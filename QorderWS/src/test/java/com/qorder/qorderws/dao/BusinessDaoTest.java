package com.qorder.qorderws.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qorder.qorderws.model.business.Business;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class BusinessDaoTest {

	@Autowired
	private IBusinessDAO businessDAO;
	private Business testBus;

	public IBusinessDAO getBusinessDao() {
		return businessDAO;
	}

	public void setBusinessDao(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	@Before
	public void setUp() throws Exception {
		this.testBus = new Business("Jumbo");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		long i = businessDAO.add(testBus);
		assertEquals(i,9);
		
	}

}
