package org.brokerage.service.impl;

import lombok.RequiredArgsConstructor;
import org.brokerage.dto.OrderRequest;

import org.brokerage.model.Orders;
import org.brokerage.model.enums.OrderStatus;
import org.brokerage.repository.OrderRepository;
import org.brokerage.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Orders createOrder(OrderRequest request) {
        Orders order = new Orders();
        order.setCustomerId(request.getCustomerId());
        order.setAssetName(request.getAssetName());
        order.setOrderSide(request.getSide());
        order.setSize(request.getSize());
        order.setPrice(request.getPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public List<Orders> listOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending orders can be canceled");
        }
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
    }
}