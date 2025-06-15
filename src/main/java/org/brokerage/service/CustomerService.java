package org.brokerage.service;

import org.brokerage.model.Customer;
import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findByEmail(String email);
    boolean checkPassword(String email, String rawPassword);
    boolean existsByUsername(String username);
    void saveCustomer(Customer customer);
}
