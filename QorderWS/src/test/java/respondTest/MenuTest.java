package respondTest;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.MenuDTO;

public class MenuTest {
	
	private AppClient client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		client = new AppClient();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {
		
	}
	
	@Test
	public final void testSuccessfulGetMenuById() {
		System.out.println("Test menu controller succesful respond:");
		long businessId=1;
		MenuDTO businessInfo =  client.requestForMenu("http://localhost:8080/qorderws/menus/business?id=",businessId);
		System.out.println("1: Check object characteristics after parsing from Json:");
		System.out.println("Business info: " + businessInfo.getBusinessName());
		Iterator<CategoryDTO> categoryItr = businessInfo.getCategoryInfoList().iterator();
		while(categoryItr.hasNext())
		{
			CategoryDTO categoryInfo = categoryItr.next();
			System.out.println(categoryInfo.toString());
		}
		assertNotNull(businessInfo);
	}
	
	@Test
	public final void testFailedGetMenuById() {
		System.out.println("\n\n2: Test menu controller exceptions for non stored objects:");
		long businessId = 100;
		MenuDTO fetchedInfo = null;
		try
		{
			 fetchedInfo =  client.requestForMenu("http://localhost:8080/qorderws/menus/business?id=",businessId);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		assertNull(fetchedInfo);
	}

}
