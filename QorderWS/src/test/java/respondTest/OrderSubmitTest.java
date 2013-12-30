package respondTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.dto.order.BasketProductDTO;
import com.qorder.qorderws.dto.order.OrderDTO;

public class OrderSubmitTest {

	private AppClient client;
	@Before
	public void setUp() throws Exception {
		client = new AppClient();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void SuccessfulOrderSubmittest() {
		System.out.println("Put Request for order submit to registered business");
		long businessId = 1;
		OrderDTO order = new OrderDTO();
		order.setTableNumber("B12");
		order.setOrders(createOrderList());
		
		Exception ex1 = null;
		try
		{
			client.putNewOrder("http://localhost:8080/qorderws/orders/business?id=", businessId, order);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			ex1 = ex;
		}
		assertNull(ex1);
	}
	
	@Test
	public void FailedOrderSubmittest() {
		System.out.println("Put Request for order submit to not registered business");
		long businessId = 50;
		OrderDTO order = new OrderDTO();
		order.setTableNumber("A12");
		order.setOrders(createOrderList());
		
		Exception ex1 = null;
		try
		{
			client.putNewOrder("http://localhost:8080/qorderws/orders/business?id=", businessId, order);
		}
		catch(Exception ex)
		{
			System.out.println("Web service error message: " + ex.getMessage());
			ex1 = ex;
		}
		assertNotNull(ex1);
	}
	
	
	private List<BasketProductDTO> createOrderList() {
		List<BasketProductDTO> productHolderList = new ArrayList<BasketProductDTO>();
		
		BasketProductDTO productHolder1 = new BasketProductDTO();
		productHolder1.setNotes("My product 1 notes");
		productHolder1.setProductId(1);
		
		BasketProductDTO productHolder2 = new BasketProductDTO();
		productHolder2.setNotes("My product 2 notes");
		productHolder2.setProductId(3);
		
		productHolderList.add(productHolder1);
		productHolderList.add(productHolder2);
		return productHolderList;
	}

}
