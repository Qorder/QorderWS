package respondTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

public class BusinessCreationTest {
	
	private AppClient client = new AppClient();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void CreateBusinessRequesttest() {
		long ownerId = 0;
		client.putNewBusiness("http://localhost:8080/qorderws/businesses/owner?id=", ownerId, createMockBussineses());
		
	}
	
	private Business createMockBussineses() {
		Business business = new Business("To Meraki");

		// category 1
		Category category1 = new Category();
		category1.setName("Food");
		
		List<Product> foodProductList = new ArrayList<Product>();

		// category 1 product 1
		Product product1 = new Product();
		product1.setName("Souvlaki");
		product1.setPrice(BigDecimal.valueOf(1.4));
		product1.addDetail("Chicken");
		product1.addDetail("pork");

		// category 1 product 2
		Product product2 = new Product();
		product2.setName("Giros");
		product2.setPrice(BigDecimal.valueOf(2.0));
		product2.addDetail("Chicken");
		product2.addDetail("pork");

		foodProductList.add(product1);
		foodProductList.add(product2);
		category1.setProductList(foodProductList);


		// category 2
		Category category2 = new Category();
		category2.setName("Drink");

		List<Product> drinkProductList = new ArrayList<Product>();

		// category 2 product 3
		Product product3 = new Product();
		product3.setName("Juice");
		product3.setPrice(BigDecimal.valueOf(2.0));
		product3.addDetail("Motion");
		product3.addDetail("3 fruits");
		product3.addDetail("7 vitamins");

		// category 2 product 4
		Product product4 = new Product();
		//product4.setId(3);
		product4.setName("Tea");
		product4.setPrice(BigDecimal.valueOf(2.0));
		product4.addDetail("Sweet");
		product4.addDetail("semisweet");
		product4.addDetail("no sugar");

		drinkProductList.add(product3);
		drinkProductList.add(product4);
		category2.setProductList(drinkProductList);
		
		
		// category 3
		Category category3 = new Category();
		category3.setName("Coffee");

		List<Product> coffeeProductList = new ArrayList<Product>();

		// category 3 product 5
		Product product5 = new Product();
		product5.setName("Frappe");
		product5.setPrice(BigDecimal.valueOf(1.5));
		product5.addDetail("Sweet");
		product5.addDetail("Semisweet");
		product5.addDetail("no sugar");
		product5.addDetail("Milk");
		
		// category 3 product 6
		Product product6 = new Product();
		product6.setName("Cappuccino");
		product6.setPrice(BigDecimal.valueOf(2.5));
		product6.addDetail("Sweet");
		product6.addDetail("semisweet");
		product6.addDetail("no sugar");

		coffeeProductList.add(product5);
		coffeeProductList.add(product6);
		category3.setProductList(drinkProductList);

		
		//finally add all categories to business
		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(category1);
		categoryList.add(category2);
		categoryList.add(category3);
		
		business.setCategoryList(categoryList);
		return business;
	}

}
