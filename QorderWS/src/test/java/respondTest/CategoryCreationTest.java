package respondTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.model.category.Category;

public class CategoryCreationTest {

	private AppClient client;

	@Before
	public void setUpBeforeClass() throws Exception {
		client = new AppClient();
	}

	@Test
	public final void testPutCategoryToMenuSuccess() {
		System.out.println("\nTest successful category save to web service:");
		long menuId = 1;
		Category category = createMockCategory();
		client.putNewCategory("/categories/menu?id=", menuId, category);

		System.out
				.println("Check object characteristics after parsing from Json:\n");
		MenuDTO menuDTO = client
				.requestForMenu("/menus/menu?id=", menuId);
		
		assertNotNull(menuDTO);
		
		for (CategoryDTO categoryDTO : menuDTO.getCategoryInfoList()) {
			System.out.println(categoryDTO.toString());
			if (categoryDTO.getName().equals(category.getName())) {
				try
				{
					client.postNewProducts("/products/category?id=",categoryDTO.getId(), getProducts());
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
					fail();
				}
			}

		}
	}

	@Test
	public final void testPutCategoryToMenuFail() {
		System.out.println("\nTest failed category save to web service:");
		long menuId = 100;
		Category category = createMockCategory();

		try {
			client.putNewCategory("/categories/menu?id=", menuId, category);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			assertNotNull(ex);
		}
	}

	private Category createMockCategory() {
		Category category = Mockito.mock(Category.class);
		Mockito.when(category.getName()).thenReturn("Bread");
		return category;
	}
	
	private List<DetailedProductDTO> getProducts() {
		List<DetailedProductDTO> foodProductList = new ArrayList<DetailedProductDTO>();

		// category bread product 1
		DetailedProductDTO product1 = new DetailedProductDTO();
		product1.setName("Pita");
		product1.setPrice(BigDecimal.valueOf(0.5));
		product1.setDetails("From Cyprus-From Lebanon");

		// category bread product 2
		DetailedProductDTO product2 = new DetailedProductDTO();
		product2.setName("Bread");
		product2.setPrice(BigDecimal.valueOf(1.0));
		product2.setDetails("Seven seeds-Tranditional");

		foodProductList.add(product1);
		foodProductList.add(product2);
		return foodProductList;
	}
}
