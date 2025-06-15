package org.brokerage.service.impl;

import lombok.RequiredArgsConstructor;
import org.brokerage.dto.OrderRequest;
import org.brokerage.model.Asset;
import org.brokerage.model.Customer;
import org.brokerage.model.CustomerAsset;
import org.brokerage.model.enums.OrderSide;
import org.brokerage.model.enums.OrderStatus;
import org.brokerage.model.Order;
import org.brokerage.repository.AssetRepository;
import org.brokerage.repository.CustomerAssetRepository;
import org.brokerage.repository.CustomerRepository;
import org.brokerage.repository.OrderRepository;
import org.brokerage.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerAssetRepository customerAssetRepository;
    private final AssetRepository assetRepository;
    private final CustomerRepository customerRepository;

    @Override
    public Order createOrder(OrderRequest request) {
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setAssetName(request.getAssetName());
        order.setOrderSide(request.getSide());
        order.setSize(request.getSize());
        order.setPrice(request.getPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        if (request.getSide() == OrderSide.SELL) {
            CustomerAsset customerAsset = customerAssetRepository
                    .findByCustomer_IdAndAsset_AssetName(request.getCustomerId(), request.getAssetName())
                    .orElseThrow(() -> new RuntimeException("Customer asset not found"));

            if (customerAsset.getUsableSize().compareTo(request.getSize()) < 0) {
                throw new IllegalStateException("Not enough usable asset to create sell order");
            }

            customerAsset.setUsableSize(customerAsset.getUsableSize().subtract(request.getSize()));
            customerAssetRepository.save(customerAsset);
        }

        return orderRepository.save(order);
    }

    @Override
    public List<Order> listOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending orders can be canceled");
        }

        if (order.getOrderSide() == OrderSide.SELL) {
            CustomerAsset customerAsset = customerAssetRepository
                    .findByCustomer_IdAndAsset_AssetName(order.getCustomerId(), order.getAssetName())
                    .orElseThrow(() -> new RuntimeException("Customer asset not found"));

            customerAsset.setUsableSize(customerAsset.getUsableSize().add(order.getSize()));
            customerAssetRepository.save(customerAsset);
        }

        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public void matchOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending orders can be matched");
        }

        BigDecimal orderSize = order.getSize();
        BigDecimal orderPrice = order.getPrice();
        BigDecimal totalAmount = orderSize.multiply(orderPrice);

        Customer customer = customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CustomerAsset customerAsset = customerAssetRepository
                .findByCustomer_IdAndAsset_AssetName(order.getCustomerId(), order.getAssetName())
                .orElse(null);

        if (order.getOrderSide() == OrderSide.SELL) {
            if (customerAsset == null || customerAsset.getTotalSize().compareTo(orderSize) < 0) {
                throw new IllegalStateException("Insufficient asset amount for sell order");
            }

            customerAsset.setTotalSize(customerAsset.getTotalSize().subtract(orderSize));
            customerAssetRepository.save(customerAsset);

            customer.setBalance(customer.getBalance().add(totalAmount));
        } else if (order.getOrderSide() == OrderSide.BUY) {
            if (customer.getBalance().compareTo(totalAmount) < 0) {
                throw new IllegalStateException("Insufficient balance for buy order");
            }

            customer.setBalance(customer.getBalance().subtract(totalAmount));

            if (customerAsset == null) {
                Asset asset = assetRepository.findByAssetName(order.getAssetName())
                        .orElseThrow(() -> new RuntimeException("Asset not found: " + order.getAssetName()));
                customerAsset = CustomerAsset.builder()
                        .customer(customer)
                        .asset(asset)
                        .totalSize(orderSize)
                        .usableSize(orderSize)
                        .build();
            } else {
                customerAsset.setTotalSize(customerAsset.getTotalSize().add(orderSize));
                customerAsset.setUsableSize(customerAsset.getUsableSize().add(orderSize));
            }

            customerAssetRepository.save(customerAsset);
        }

        customerRepository.save(customer);

        order.setStatus(OrderStatus.MATCHED);
        orderRepository.save(order);
    }
}
