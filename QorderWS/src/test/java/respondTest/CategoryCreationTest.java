package respondTest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

public class CategoryCreationTest {
	
	private AppClient client;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		client = new AppClient();
	}
	
	@Test
	public final void testPutCategoryToBusinessSuccess() {
		System.out.println("\nTest successful category save to web service:");
		long businessId = 1;
		Category category = createMockCategory();
		client.putNewCategory("http://localhost:8080/qorderws/categories/business?id=",businessId, category);
		
		
		System.out.println("Check object characteristics after parsing from Json:\n");
		MenuDTO businessInfo =  client.requestForMenu("http://localhost:8080/qorderws/menus/business?id=",businessId);
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
	public final void testPutCategoryToBusinessFail() {
		System.out.println("\nTest failed category save to web service:");
		long businessId = 100;
		Category category = createMockCategory();
		
		try
		{
			client.putNewCategory("http://localhost:8080/qorderws/categories/business?id=",businessId, category);	
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	private Category createMockCategory() {
		List<Product> foodProductList = new ArrayList<Product>();

		// category bread product 1
		Product product1 = new Product();
		product1.setName("Pita");
		product1.setPrice(BigDecimal.valueOf(0.5));
		product1.addDescription("From Cyprus");
		product1.addDescription("From Lebanon");

		// category brad product 2
		Product product2 = new Product();
		product2.setName("Bread");
		product2.setPrice(BigDecimal.valueOf(1.0));
		product2.addDescription("Tranditional");
		product2.addDescription("Seven seeds");
		
		Category category = Mockito.mock(Category.class);
		Mockito.when(category.getName()).thenReturn("Bread");
		Mockito.when(category.getProductList()).thenReturn(foodProductList);
		
		return category;
	}
}
