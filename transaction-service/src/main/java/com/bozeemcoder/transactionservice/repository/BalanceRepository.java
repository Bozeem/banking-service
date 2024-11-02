package com.bozeemcoder.transactionservice.repository;

import com.bozeemcoder.transactionservice.entity.Account;
import com.bozeemcoder.transactionservice.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, String> {

    Balance findByAccount(Account account);
}
