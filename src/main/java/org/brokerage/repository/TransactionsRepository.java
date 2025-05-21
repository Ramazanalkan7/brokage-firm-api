package org.brokerage.repository;

import org.brokerage.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findByCustomerId(Long customerId);

}