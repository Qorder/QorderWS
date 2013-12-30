package respondTest;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.dto.category.DetailedCategoryDTO;
import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.model.product.Product;

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
		Product product = createMockProduct();
		client.putNewProduct("http://localhost:8080/qorderws/products/category?id=", categoryId, product);
		
		System.out.println("\nCheck object characteristics after parsing from Json:");
		DetailedCategoryDTO categoryDTO = client.requestForCategory("http://localhost:8080/qorderws/categories/category?id=",categoryId);
		System.out.println("Category info: " + categoryDTO.getName());
		Iterator<ProductDTO> productItr = categoryDTO.getProductList().iterator();
		while(productItr.hasNext())
		{
			ProductDTO productInfo = productItr.next();
			System.out.println(productInfo.toString());
		}
		assertNotNull(categoryDTO);
	}
	
	@Test
	public final void requstNewProductCreationFailtest() {
		System.out.println("New product creation fail test:");
		long categoryId = 100;
		Product product = createMockProduct();
		
		try
		{
			client.putNewProduct("http://localhost:8080/qorderws/products/category?id=", categoryId, product);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	
	
	private Product createMockProduct() {
		Product product = new Product();
		product.setName("club sandwitch");
		product.setPrice(BigDecimal.valueOf(4.5));
		product.addDescription("classic");
		product.addDescription("with chicken nuggets");
		product.addDescription("with giros");
		return product;
	}

}
