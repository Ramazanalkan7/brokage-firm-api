package org.brokerage.controller;

import lombok.RequiredArgsConstructor;
import org.brokerage.model.Customer;
import org.brokerage.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        if (customerService.existsByUsername(customer.getUsername())) {
            return ResponseEntity.badRequest().body("This users already exists in the system.");
        }
        customerService.saveCustomer(customer);
        return ResponseEntity.ok("Successfully");
    }

    @GetMapping("/info")
    public ResponseEntity<Customer> getCustomerInfo(@RequestParam String email) {
        return customerService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
