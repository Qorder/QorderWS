package respondTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.dto.category.CategoryDTO;

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
	public final void testSuccessfulGetMenuById() {
		System.out.println("Test menu controller succesful respond:");
		try
		{
			long menuId=1;
			MenuDTO menuDTO =  client.requestForMenu("/menus/menu?id=",menuId);
			
			assertNotNull(menuDTO);
			
			System.out.println("1: Check object characteristics after parsing from Json:");
			Iterator<CategoryDTO> categoryItr = menuDTO.getCategoryInfoList().iterator();
			while(categoryItr.hasNext())
			{
				CategoryDTO categoryInfo = categoryItr.next();
				System.out.println(categoryInfo.toString());
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			fail();
		}
	}
	
	@Test
	public final void testFailedGetMenuById() {
		System.out.println("\n\n2: Test menu controller exceptions for non stored objects:");
		try
		{
			long menuId = 100;
			client.requestForMenu("/menus/menu?id=",menuId);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			assertNotNull(ex);
		}
	}

}
