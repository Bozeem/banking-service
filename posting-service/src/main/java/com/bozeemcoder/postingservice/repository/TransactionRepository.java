package com.bozeemcoder.postingservice.repository;


import com.bozeemcoder.postingservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
