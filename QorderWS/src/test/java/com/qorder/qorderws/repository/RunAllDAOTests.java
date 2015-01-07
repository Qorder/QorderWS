package com.qorder.qorderws.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BusinessRepositoryTest.class, MenuRepositoryTest.class, CategoryRepositoryTest.class, ProductRepositoryTest.class, OrderRepositoryTest.class })
public class RunAllDAOTests {

}
