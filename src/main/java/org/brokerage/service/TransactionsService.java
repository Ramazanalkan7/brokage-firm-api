package org.brokerage.service;

import org.brokerage.dto.TransactionsRequest;
import org.brokerage.model.Transactions;

import java.util.List;

public interface TransactionsService {
    void deposit(TransactionsRequest request);
    void withdraw(TransactionsRequest request);
    List<Transactions> listTransaction(Long customerId);

}