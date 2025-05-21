package org.brokerage.service;

import org.brokerage.model.Customers;
import java.util.Optional;

public interface CustomerService {
    Optional<Customers> findByEmail(String email);
    boolean checkPassword(String email, String rawPassword);
    boolean existsByUsername(String username);
    void saveCustomer(Customers customer);
}
