package com.qorder.qorderws.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderViewDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.service.IOrderService;
import com.qorder.qorderws.utils.providers.ReferenceProvider;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private IOrderService orderService;

	@RequestMapping(value = "/business/{businessID}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createOrder(@PathVariable Long businessID, @RequestBody OrderDTO orderDTO) throws ResourceNotFoundException {
		LOGGER.info("Request for order submit");
		long orderID = orderService.submitOrder(businessID, orderDTO);
		
		URI location = URI.create(ReferenceProvider.INSTANCE.getLocationFor(EEntity.ORDER) + orderID);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		
		return new ResponseEntity<>(headers,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/business/{businessID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<OrderViewDTO[]> getOrdersByBusinessId(@PathVariable Long businessID) throws ResourceNotFoundException {
		OrderViewDTO[] businessOrders = orderService.fetchOrdersByBusinessID(businessID);
		LOGGER.info("Request for business orders.\nFetchedList size is " + businessOrders.length);
		
		return new ResponseEntity<>(businessOrders, HttpStatus.OK);
	}

	@RequestMapping(value ="/{orderID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<OrderViewDTO> getOrdersById(@PathVariable Long orderID) throws ResourceNotFoundException {
		LOGGER.info("Request for order with id " + orderID);
		
		OrderViewDTO orderView = orderService.fetchOrderById(orderID);
		
		return new ResponseEntity<>(orderView, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/business/{businessID}/order", params = "status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<OrderViewDTO[]> getOrdersByStatus(@PathVariable Long businessID, @RequestParam String status) throws ResourceNotFoundException, IllegalArgumentException {
		EOrderStatus orderStatus = EOrderStatus.valueOf(status);
		OrderViewDTO[] businessOrders = orderService.fetchOrdersByStatus(businessID, orderStatus);
		LOGGER.info("Request for business orders with status query.\nFetchedList size is " + businessOrders.length);
		
		return new ResponseEntity<>(businessOrders, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/{orderID}/order", params = "status", method = RequestMethod.POST)
	ResponseEntity<Void> changeOrderStatus(@PathVariable Long orderId, @RequestParam String status) throws ResourceNotFoundException, IllegalArgumentException {
		LOGGER.info("Request to change status if order with id " + orderId);
		
		EOrderStatus orderStatus = EOrderStatus.valueOf(status);
		orderService.changeOrderStatus(orderId, orderStatus);
		
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	ResponseEntity<String> sendBadRequestException(IllegalArgumentException ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
	}
}
