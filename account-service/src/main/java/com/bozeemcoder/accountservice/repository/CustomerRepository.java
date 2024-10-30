package com.bozeemcoder.accountservice.repository;

import com.bozeemcoder.accountservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
