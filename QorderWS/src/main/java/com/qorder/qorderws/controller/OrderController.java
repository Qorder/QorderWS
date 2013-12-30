package com.qorder.qorderws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.PendingOrdersDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.service.IOrderService;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);

	@Autowired
	private IOrderService orderService;

	@RequestMapping(value = "/business", params = "id", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> createOrder(@RequestParam Long id, @RequestBody OrderDTO orderDTO) throws BusinessDoesNotExistException {
		LOGGER.info("Request for order submit");
		orderService.submitOrder(id, orderDTO);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/business", params = "id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<PendingOrdersDTO> getOrdersByBusinessId(@RequestParam Long id) throws BusinessDoesNotExistException {
		PendingOrdersDTO pendingOrders = orderService.fetchOrdersFromBusinessID(id);
		LOGGER.info("Request for business orders.\nFetchedList size is ");
		return new ResponseEntity<PendingOrdersDTO>(pendingOrders, HttpStatus.OK);
	}
	
	@ExceptionHandler(BusinessDoesNotExistException.class)
	ResponseEntity<String> sendNotFoundException(BusinessDoesNotExistException ex) {
		return new ResponseEntity<String>(ex.getLocalizedMessage().toString(),HttpStatus.NOT_FOUND);
	}
}
