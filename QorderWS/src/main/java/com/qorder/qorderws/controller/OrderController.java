package com.qorder.qorderws.controller;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderInfoDTO;
import com.qorder.qorderws.dto.product.ProductHolderDTO;
import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.service.IOrderService;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	private final IOrderService orderService;

	@Autowired
	public OrderController(IOrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(value = "/business/{businessID}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> postOrder(@PathVariable Long businessID, @RequestBody OrderDTO orderDTO) {
		LOGGER.info("Request for order submit");
		long orderID = orderService.submitOrder(businessID, orderDTO);
		
		URI location = URI.create(EDomainLinkProvider.INSTANCE.getLocationFor(EEntity.ORDER) + orderID);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		
		return new ResponseEntity<>(headers,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/business/{businessID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Collection<OrderInfoDTO>> getOrdersByBusinessId(@PathVariable Long businessID) {
		Collection<OrderInfoDTO> businessOrders = orderService.fetchOrdersByBusinessID(businessID);
		LOGGER.info("Request for business orders.\nFetchedList size is " + businessOrders.size());
		
		return new ResponseEntity<>(businessOrders, HttpStatus.OK);
	}

	@RequestMapping(value ="/{orderID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<OrderInfoDTO> getOrdersById(@PathVariable Long orderID) {
		LOGGER.info("Request for order to id " + orderID);
		
		OrderInfoDTO orderView = orderService.fetchOrderById(orderID);
		
		return new ResponseEntity<>(orderView, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/business/{businessID}/order", params = "status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Collection<OrderInfoDTO>> getOrdersByStatus(@PathVariable Long businessID, @RequestParam String status) {
		EOrderStatus orderStatus = EOrderStatus.valueOf(status);
		Collection<OrderInfoDTO> businessOrders = orderService.fetchOrdersByStatus(businessID, orderStatus);
		LOGGER.info("Request for business orders to status query.\nFetchedList size is " + businessOrders.size());
		
		return new ResponseEntity<>(businessOrders, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{orderId}/order", params = "status", method = RequestMethod.POST)
	ResponseEntity<Void> changeOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
		LOGGER.info("Request to change status if order to id " + orderId);
		
		EOrderStatus orderStatus = EOrderStatus.valueOf(status);
		orderService.changeOrderStatus(orderId, orderStatus);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "/{orderId}/products", method = RequestMethod.GET)
	ResponseEntity<Collection<ProductHolderDTO>> getOrderedProductsById(@PathVariable Long orderId) {
		LOGGER.info("Request to read products of an order to id {1}", orderId);
		Collection<ProductHolderDTO> orderedProducts = orderService.fetchOrderedProducts(orderId);

		return new ResponseEntity<>(orderedProducts, HttpStatus.OK);
	}


	@ExceptionHandler(IllegalArgumentException.class)
	ResponseEntity<String> sendBadRequestException(IllegalArgumentException ex) {
		LOGGER.warn("Exception was thrown, to cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
	}
}
