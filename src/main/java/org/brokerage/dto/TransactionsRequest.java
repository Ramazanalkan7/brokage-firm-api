package org.brokerage.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionsRequest {

    @NotNull(message = "Customer ID must not be null")
    private Long customerId;

    @NotNull(message = "Amount must not be null")
    @DecimalMin(value = "0.0000001", inclusive = false, message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "IBAN must not be blank")
    @Pattern(
            regexp = "[A-Z]{2}[0-9]{2}[A-Z0-9]{1,30}",
            message = "Invalid IBAN format"
    )
    private String iban;
}
