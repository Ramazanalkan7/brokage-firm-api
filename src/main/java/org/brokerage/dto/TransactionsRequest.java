package org.brokerage.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionsRequest {
    private Long customerId;
    private BigDecimal amount;
    private String iban;
}