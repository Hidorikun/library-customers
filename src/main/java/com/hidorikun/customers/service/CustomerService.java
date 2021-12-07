package com.hidorikun.customers.service;

import com.hidorikun.customers.model.dto.BookDTO;
import com.hidorikun.customers.model.dto.BorrowingDTO;
import com.hidorikun.customers.model.dto.CustomerDTO;
import com.hidorikun.customers.model.entity.Customer;
import com.hidorikun.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private BookService bookService;
    private BorrowingService borrowingService;

    private CustomerDTO customerToDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDTO dto = new CustomerDTO();

        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());

        return dto;
    }

    private Customer dtoToCustomer(CustomerDTO dto) {
        Customer customer = new Customer();

        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());

        return customer;

    }

    public CustomerService(
            final CustomerRepository customerRepository,
            final BookService bookService,
            final BorrowingService borrowingService
    ) {
        this.customerRepository = customerRepository;
        this.bookService = bookService;
        this.borrowingService = borrowingService;
    }

    public CustomerDTO getCustomer(long customerId) {
        return customerToDTO(customerRepository.findById(customerId).orElse(null));
    }

    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        return customerToDTO(customerRepository.save(dtoToCustomer(customerDTO)));
    }

    public CustomerDTO updateCustomer(long customerId, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer == null) {
            return null;
        }

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());

        return customerToDTO(customerRepository.save(customer));
    }

    public void removeCustomer(long customerId) {
        customerRepository.deleteById(customerId);
    }

    public List<BookDTO> getBorrowedBooks(long customerId) {
        List<BorrowingDTO> borrowings = borrowingService.getBorrowingsByCustomers(customerId);
        List<Long> bookIds = borrowings.stream().map(BorrowingDTO::getBookId).collect(Collectors.toList());
        return bookService.getBooks(bookIds);
    }

    public List<CustomerDTO> getCustomers() {
        List<CustomerDTO> customers = new ArrayList<>();

        customerRepository.findAll().forEach(customer -> customers.add(customerToDTO(customer)));

        return customers;
    }
}
