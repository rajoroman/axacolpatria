package com.customers.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customers.models.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
