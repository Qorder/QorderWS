package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderInfoDTO;
import com.qorder.qorderws.dto.product.ProductHolderDTO;
import com.qorder.qorderws.mapper.IMapper;
import com.qorder.qorderws.mapper.resolver.IMapResolver;
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

    private final IMapper<?,?> mapper;

    @Autowired
    public OrderService(IOrderRepository orderRepository, IBusinessRepository businessRepository, IMapper mapper) {
        this.orderRepository = orderRepository;
        this.businessRepository = businessRepository;
        this.mapper = mapper;
    }

    //TODO: remove when object mapper is finished.
    @Deprecated
    private IMapResolver<OrderDTO, Order> orderDtoToOrderResolver() {
        return ( source, target) -> {
            target.setTableNumber(source.getTableNumber());
            source.getOrders()
                   .forEach((basketProductDTO) -> {
                       ProductHolder productHolder = new ProductHolder();
                       productHolder.getProduct().setId(basketProductDTO.getProductId());
                       productHolder.getProduct().setName(basketProductDTO.getName());
                       productHolder.getProduct().setPrice(basketProductDTO.getPrice());

                       productHolder.setQuantity(basketProductDTO.getQuantity());
                       productHolder.setSelectedAttributes(basketProductDTO.getAttributes());
                       productHolder.setNotes(basketProductDTO.getNotes());
                       target.add(productHolder);
                   });
            return target;
        };
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @Override
    public long submitOrder(long businessId, @NotNull OrderDTO orderDTO) {
        Order order = mapper.map(orderDTO)
                .explicit()
                .to(new Order())
                .get();
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
            OrderInfoDTO orderView = mapper.map(order)
                    .explicit()
                    .to(new OrderInfoDTO())
                    .get();
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
            OrderInfoDTO orderView = mapper.map(order)
                    .explicit()
                    .to(new OrderInfoDTO())
                    .get();
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
        return Objects.nonNull(order)
                ? mapper.map(order)
                        .explicit()
                        .to(new OrderInfoDTO())
                        .get()
                : new OrderInfoDTO();

    }

    @Override
    public Collection<ProductHolderDTO> fetchOrderedProducts(long orderId) {
        final List<ProductHolderDTO> orderedProducts = new ArrayList<>();
        final Order order = orderRepository.findOne(orderId);

        final List<ProductHolder> productList = Objects.nonNull(order)
                ? order.getOrderList() : Collections.emptyList();
        productList.forEach((productHolder) -> {
            ProductHolderDTO productHolderDTO = mapper.map(productHolder)
                    .to(new ProductHolderDTO())
                    .get();
            orderedProducts.add(productHolderDTO);
        });
        return orderedProducts;
    }

}
