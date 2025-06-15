package org.brokerage.controller;

import lombok.RequiredArgsConstructor;
import org.brokerage.dto.TransactionsRequest;
import org.brokerage.model.Transaction;
import org.brokerage.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public void deposit(@RequestBody TransactionsRequest request) {
        transactionService.deposit(request);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody TransactionsRequest request) {
        transactionService.withdraw(request);
    }

    @GetMapping
    public List<Transaction> getTransaction(@RequestParam Long customerId) {
        return transactionService.listTransaction(customerId);
    }
}
