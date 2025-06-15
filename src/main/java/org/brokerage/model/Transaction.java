package org.brokerage.model;

import jakarta.persistence.*;
import lombok.Data;
import org.brokerage.model.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // DEPOSIT / WITHDRAW

    private BigDecimal amount;

    private String iban;

    private LocalDateTime transactionDate;
}