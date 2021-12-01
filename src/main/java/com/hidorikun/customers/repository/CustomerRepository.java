package com.hidorikun.customers.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hidorikun.customers.model.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
