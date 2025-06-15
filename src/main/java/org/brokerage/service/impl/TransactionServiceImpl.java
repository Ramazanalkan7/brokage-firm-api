package org.brokerage.service.impl;

import lombok.RequiredArgsConstructor;
import org.brokerage.dto.TransactionsRequest;
import org.brokerage.model.Customer;
import org.brokerage.model.Transaction;
import org.brokerage.model.enums.TransactionType;
import org.brokerage.repository.CustomerRepository;
import org.brokerage.repository.TransactionsRepository;
import org.brokerage.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final CustomerRepository customerRepository;
    private final TransactionsRepository transactionRepository;


    @Override
    public void deposit(TransactionsRequest request) {
        validateAmount(request.getAmount());
        Customer customer = getCustomer(request.getCustomerId());

        customer.setBalance(customer.getBalance().add(request.getAmount()));
        customerRepository.save(customer);

        Transaction tx = createTransaction(customer.getId(), request.getAmount(), TransactionType.DEPOSIT, null);
        transactionRepository.save(tx);
    }

    @Override
    public void withdraw(TransactionsRequest request) {
        validateAmount(request.getAmount());
        Customer customer = getCustomer(request.getCustomerId());

        if (customer.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalStateException("Insufficient balance");
        }

        customer.setBalance(customer.getBalance().subtract(request.getAmount()));
        customerRepository.save(customer);

        Transaction tx = createTransaction(customer.getId(), request.getAmount(), TransactionType.WITHDRAW, request.getIban());
        transactionRepository.save(tx);
    }

    @Override
    public List<Transaction> listTransaction(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    private Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found: id=" + customerId));
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid transaction amount");
        }
    }

    private Transaction createTransaction(Long customerId, BigDecimal amount, TransactionType type, String iban) {
        Transaction tx = new Transaction();
        tx.setCustomerId(customerId);
        tx.setAmount(amount);
        tx.setType(type);
        tx.setIban(iban);
        tx.setTransactionDate(LocalDateTime.now());
        return tx;
    }
}
