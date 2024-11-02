package com.bozeemcoder.transactionservice.repository;

import com.bozeemcoder.transactionservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
