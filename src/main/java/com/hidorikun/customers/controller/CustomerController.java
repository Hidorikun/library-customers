package com.hidorikun.customers.controller;

import com.hidorikun.customers.model.dto.BookDTO;
import com.hidorikun.customers.model.dto.CustomerDTO;
import com.hidorikun.customers.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customer/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable() long customerId) {
        CustomerDTO customerDTO = customerService.getCustomer(customerId);

        if (customerDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping("customer/{customerId}/books")
    public ResponseEntity<List<BookDTO>> getCustomerBorrowedBooks(@PathVariable() long customerId) {
        List<BookDTO> borrowedBooks = customerService.getBorrowedBooks(customerId);

        if (borrowedBooks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping("customer")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.addCustomer(customerDTO));
    }

    @PutMapping("customer/{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable long customerId, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(customerId, customerDTO);

        if (updatedCustomer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("customer/{customerId}")
    public ResponseEntity<Void> removeCustomer(@PathVariable long customerId) {
        customerService.removeCustomer(customerId);
        return ResponseEntity.ok().build();
    }
}
