package org.brokerage.dto;

import lombok.Data;
import org.brokerage.model.enums.OrderSide;

import java.math.BigDecimal;

@Data
public class OrderRequest {
    private Long customerId;
    private String assetName;
    private OrderSide side;
    private BigDecimal size;
    private BigDecimal price;
}