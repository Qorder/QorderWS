package com.qorder.qorderws.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BusinessDaoTest.class, CategoryDaoTest.class,
		ProductDaoTest.class })
public class RunAllTests {

}
