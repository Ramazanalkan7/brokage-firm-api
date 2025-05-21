package org.brokerage.service;

import org.brokerage.dto.OrderRequest;
import org.brokerage.model.Orders;


import java.util.List;

public interface OrderService {
    Orders createOrder(OrderRequest request);
    List<Orders> listOrders(Long customerId);
    void cancelOrder(Long orderId);
}