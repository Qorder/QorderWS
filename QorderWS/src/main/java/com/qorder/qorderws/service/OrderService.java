
package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderInfoDTO;
import com.qorder.qorderws.dto.product.BasketProductDTO;
import com.qorder.qorderws.dto.product.ProductHolderDTO;
import com.qorder.qorderws.mapper.IMapResolver;
import com.qorder.qorderws.mapper.IMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.model.order.Order;
import com.qorder.qorderws.model.product.ProductHolder;
import com.qorder.qorderws.repository.IBusinessRepository;
import com.qorder.qorderws.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service
@Transactional
public class OrderService implements IOrderService {

	private final IOrderRepository orderRepository;
	private final IBusinessRepository businessRepository;

	private final IMapper mapper;

	private final IMapResolver<OrderDTO, Order> mapResolver;

	@Autowired
	public OrderService(IOrderRepository orderRepository, IBusinessRepository businessRepository, IMapper mapper) {
		this.orderRepository = orderRepository;
		this.businessRepository = businessRepository;
		this.mapper = mapper;
		this.mapResolver = orderDtoToOrderResolver();
	}

	private IMapResolver<OrderDTO,Order> orderDtoToOrderResolver() {
		return (source, target) -> {
			target.setTableNumber(source.getTableNumber());
			for (BasketProductDTO basketProductDTO : source.getOrders()) {
				ProductHolder productHolder = new ProductHolder();

				productHolder.getProduct().setId(basketProductDTO.getProductId());
				productHolder.getProduct().setName(basketProductDTO.getName());
				productHolder.getProduct().setPrice(basketProductDTO.getPrice());

				productHolder.setQuantity(basketProductDTO.getQuantity());
				productHolder.setSelectedAttributes(basketProductDTO.getAttributes());
				productHolder.setNotes(basketProductDTO.getNotes());

				target.add(productHolder);
			}
			return target;
		};
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public long submitOrder(long businessId, @NotNull OrderDTO orderDTO) {
		Order order = mapper.mapWithResolver(orderDTO, new Order(), mapResolver);
		order.setBusiness(businessRepository.findOne(businessId));
		orderRepository.save(order);
		return order.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<OrderInfoDTO> fetchOrdersByBusinessID(long businessId) {
		Business business = businessRepository.findOne(businessId);
		List<Order> orders = orderRepository.findOrdersByBusiness(business);
		List<OrderInfoDTO> businessOrders = new ArrayList<>();

		orders.forEach((order) -> {
			OrderInfoDTO orderView = mapper.map(order, new OrderInfoDTO());
			businessOrders.add(orderView);
		});
		return businessOrders;
		
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<OrderInfoDTO> fetchOrdersByStatus(long businessId, @NotNull EOrderStatus orderStatus) {
		Business business = businessRepository.findOne(businessId);
		List<Order> orders = orderRepository.findOrdersByStatus(business, orderStatus);
		List<OrderInfoDTO> businessOrders = new ArrayList<>();

		orders.forEach((order) -> {
			OrderInfoDTO orderView = mapper.map(order, new OrderInfoDTO());
			businessOrders.add(orderView);
		});
		return businessOrders;
	}

	@Transactional(readOnly = false)
	@Override
	public void changeOrderStatus(long orderId, @NotNull EOrderStatus orderStatus) {
		Order order = orderRepository.findOne(orderId);
		order.setStatus(orderStatus);
		orderRepository.save(order);
	}
	
	@Transactional(readOnly = true)
	@Override
	public OrderInfoDTO fetchOrderById(long orderId) {
		Order order = orderRepository.findOne(orderId);
		return Objects.nonNull(order) ? mapper.map(order, new OrderInfoDTO()) : new OrderInfoDTO();
	}

	@Override
	public Collection<ProductHolderDTO> fetchOrderedProducts(long orderId) {
		final List<ProductHolderDTO> orderedProducts = new ArrayList<>();
		final Order order = orderRepository.findOne(orderId);

		final List<ProductHolder> productList = Objects.nonNull(order) ?
				order.getOrderList() : Collections.emptyList();
		productList.forEach((productHolder) -> {
			orderedProducts.add(mapper.map(productHolder, new ProductHolderDTO()));
		});
		return orderedProducts;
	}

}
