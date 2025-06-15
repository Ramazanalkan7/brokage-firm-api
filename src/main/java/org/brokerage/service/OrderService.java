package org.brokerage.service;

import org.brokerage.dto.OrderRequest;
import org.brokerage.model.Order;


import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest request);
    List<Order> listOrders(Long customerId);
    void cancelOrder(Long orderId);
    void matchOrder(Long orderId);
}