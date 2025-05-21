package org.brokerage.service.impl;


import lombok.RequiredArgsConstructor;
import org.brokerage.dto.TransactionsRequest;

import org.brokerage.model.Customers;
import org.brokerage.model.Transactions;
import org.brokerage.model.enums.TransactionType;
import org.brokerage.repository.CustomerRepository;
import org.brokerage.repository.TransactionsRepository;
import org.brokerage.service.TransactionsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {

    private final CustomerRepository customerRepository;
    private final TransactionsRepository transactionRepository;

    @Override
    public void deposit(TransactionsRequest request) {
        Customers customer = getCustomer(request.getCustomerId());
        customer.setBalance(customer.getBalance().add(request.getAmount()));
        customerRepository.save(customer);

        Transactions tx = new Transactions();
        tx.setCustomerId(customer.getId());
        tx.setAmount(request.getAmount());
        tx.setType(TransactionType.DEPOSIT);
        tx.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(tx);
    }

    @Override
    public void withdraw(TransactionsRequest request) {
        Customers customer = getCustomer(request.getCustomerId());
        if (customer.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalStateException("Insufficient balance");
        }
        customer.setBalance(customer.getBalance().subtract(request.getAmount()));
        customerRepository.save(customer);

        Transactions tx = new Transactions();
        tx.setCustomerId(customer.getId());
        tx.setAmount(request.getAmount());
        tx.setType(TransactionType.WITHDRAW);
        tx.setIban(request.getIban());
        tx.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(tx);
    }

    private Customers getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public List<Transactions> listTransaction(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }
}
