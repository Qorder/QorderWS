package com.qorder.qorderws.repository;

import com.qorder.qorderws.WebServiceApplication;
import com.qorder.qorderws.configuration.BaseDbUnitTestCase;
import com.qorder.qorderws.model.business.Business;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebServiceApplication.class)
public class BusinessRepositoryTest extends BaseDbUnitTestCase {

	@Autowired
	private IBusinessRepository businessRepository;


	@Test
	public void testExistsFindById() {
		boolean businessExists = businessRepository.exists(1L);
		assertTrue(businessExists);
	}

	@Test
	public void testBusinessNotFoundByID() {
		boolean businessExists = businessRepository.exists(1337L);
		assertFalse(businessExists);
	}
	
	@Test
	public void testBusinessCreation() {
		System.out.println("Testing Business creation:");
		Business newBusiness = new Business();

		Business persistedBusiness = businessRepository.save(newBusiness);
		Assert.assertNotNull(persistedBusiness.getId());
	}
	
	@Test
	public void testMenuCreationAfterBusiness() {
		System.out.println("Testing Menu creation non null field of Business:");
		Business newBusiness = new Business();

		Business persistedBusiness = businessRepository.save(newBusiness);
		Assert.assertNotNull(persistedBusiness.getMenu().getId());
	}

}
