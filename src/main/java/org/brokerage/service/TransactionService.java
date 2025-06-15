package org.brokerage.service;

import org.brokerage.dto.TransactionsRequest;
import org.brokerage.model.Transaction;

import java.util.List;

public interface TransactionService {
    void deposit(TransactionsRequest request);
    void withdraw(TransactionsRequest request);
    List<Transaction> listTransaction(Long customerId);

}