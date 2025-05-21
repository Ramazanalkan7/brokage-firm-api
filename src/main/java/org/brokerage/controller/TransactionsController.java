package org.brokerage.controller;

import lombok.RequiredArgsConstructor;
import org.brokerage.dto.TransactionsRequest;
import org.brokerage.model.Transactions;
import org.brokerage.service.TransactionsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionService;

    @PostMapping("/deposit")
    public void deposit(@RequestBody TransactionsRequest request) {
        transactionService.deposit(request);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody TransactionsRequest request) {
        transactionService.withdraw(request);
    }

    @GetMapping
    public List<Transactions> getTransaction(@RequestParam Long customerId) {
        return transactionService.listTransaction(customerId);
    }
}
