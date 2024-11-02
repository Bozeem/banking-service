package com.bozeemcoder.transactionservice.repository;

import com.bozeemcoder.transactionservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
