package respondTest;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.dto.category.DetailedCategoryDTO;
import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.dto.product.ProductDTO;

public class ProductCreationTest {

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
	public final void requstNewProductCreationtest() {
		System.out.println("New product creation test:");
		long categoryId = 1;
		List<DetailedProductDTO> products = new ArrayList<DetailedProductDTO>();
		products.add(createMockProduct());
		client.postNewProducts("/products/category?id=", categoryId, products);

		System.out
				.println("\nCheck object characteristics after parsing from Json:");
		DetailedCategoryDTO categoryDTO = client.requestForCategory("/categories/category?id=",	categoryId);
		System.out.println("Category info: " + categoryDTO.getName());
		Iterator<ProductDTO> productItr = categoryDTO.getProductList()
				.iterator();
		while (productItr.hasNext()) {
			ProductDTO productInfo = productItr.next();
			System.out.println(productInfo.toString());
		}
		assertNotNull(categoryDTO);
	}

	@Test
	public final void requstNewProductCreationFailtest() {
		System.out.println("New product creation fail test:");
		long categoryId = 100;
		List<DetailedProductDTO> products = new ArrayList<DetailedProductDTO>();
		products.add(createMockProduct());
		try {
			client.postNewProducts("/products/category?id=", categoryId, products);
		} 
		catch (Exception ex) 
		{
			System.out.println(ex.getMessage());
			assertNotNull(ex);
		}
	}

	private DetailedProductDTO createMockProduct() {
		DetailedProductDTO product = new DetailedProductDTO();
		product.setName("club sandwich");
		product.setPrice(BigDecimal.valueOf(4.5));
		product.setDetails("classic-with chicken nuggets-with giros");
		product.setDescription("I'm a description for club sandwich!");
		return product;
	}

}
