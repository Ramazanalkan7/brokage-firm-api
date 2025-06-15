package org.brokerage.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.brokerage.model.enums.OrderSide;

import java.math.BigDecimal;

@Data
public class OrderRequest {

    @NotNull(message = "Customer ID must not be null")
    private Long customerId;

    @NotBlank(message = "Asset name must not be blank")
    private String assetName;

    @NotNull(message = "Order side must not be null")
    private OrderSide side;

    @NotNull(message = "Size must not be null")
    @DecimalMin(value = "0.0000001", inclusive = false, message = "Size must be greater than zero")
    private BigDecimal size;

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.0000001", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;
}
