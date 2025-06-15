package org.brokerage.controller;

import lombok.RequiredArgsConstructor;
import org.brokerage.dto.OrderRequest;
import org.brokerage.model.Order;
import org.brokerage.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping
    public List<Order> listOrders(@RequestParam Long customerId) {
        return orderService.listOrders(customerId);
    }

    @DeleteMapping("/{orderId}")
    public void cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
    }

    @PostMapping("/{orderId}/match")
    public ResponseEntity<String> matchOrder(@PathVariable Long orderId) {
        orderService.matchOrder(orderId);
        return ResponseEntity.ok("Order matched successfully.");
    }
}
