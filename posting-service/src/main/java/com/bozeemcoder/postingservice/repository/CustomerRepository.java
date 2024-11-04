package com.bozeemcoder.postingservice.repository;


import com.bozeemcoder.postingservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
