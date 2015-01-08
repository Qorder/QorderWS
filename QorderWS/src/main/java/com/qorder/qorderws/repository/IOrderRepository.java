package com.qorder.qorderws.repository;

import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Grigorios
 */
public interface IOrderRepository extends JpaRepository<Order, Long> {

    @Query("select ord from Order ord where ord.business = ?1")
    List<Order> findOrdersByBusiness(Business business);

    @Query("select ord from Order ord where ord.business = ?1 and ord.status = ?2")
    List<Order> findOrdersByStatus(Business business, EOrderStatus orderStatus);
}
