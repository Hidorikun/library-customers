package com.hidorikun.customers.service;

import com.hidorikun.customers.model.dto.BookDTO;
import com.hidorikun.customers.model.dto.CustomerDTO;
import com.hidorikun.customers.model.entity.Customer;
import com.hidorikun.customers.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private BookService bookService;

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
            final BookService bookService
    ) {
        this.customerRepository = customerRepository;
        this.bookService = bookService;
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
        BookDTO book = bookService.getBook(1);

        return null;
    }
}
