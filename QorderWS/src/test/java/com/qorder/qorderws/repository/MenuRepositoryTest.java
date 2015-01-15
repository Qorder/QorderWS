package com.qorder.qorderws.repository;

import com.qorder.qorderws.WebServiceApplication;
import com.qorder.qorderws.configuration.BaseDbUnitTestCase;
import com.qorder.qorderws.model.menu.Menu;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebServiceApplication.class)
public class MenuRepositoryTest extends BaseDbUnitTestCase {

	@Autowired
	private IMenuRepository menuRepository;


	@Test
	public void testExistsFindById() {
		boolean menuExists = menuRepository.exists(4L);
		assertTrue(menuExists);
	}
	
	@Test
	public void testMenuNotFoundByID() {
		Menu aMenu = menuRepository.findOne(2500L);
		Assert.assertNull(aMenu);
	}
}
