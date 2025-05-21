package org.brokerage.service.impl;

import lombok.RequiredArgsConstructor;
import org.brokerage.model.Customers;
import org.brokerage.repository.CustomerRepository;
import org.brokerage.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Customers> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }


    public boolean existsByUsername(String username) {
        return customerRepository.existsByUsername(username);
    }

    @Override
    public boolean checkPassword(String email, String rawPassword) {
        Optional<Customers> customerOpt = customerRepository.findByEmail(email);
        if (customerOpt.isEmpty()) {
            return false;
        }
        String encodedPassword = customerOpt.get().getPassword();
        System.out.println(passwordEncoder.matches(rawPassword, encodedPassword));
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public void saveCustomer(Customers customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }
}
