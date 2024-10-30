package com.bozeemcoder.accountservice.repository;

import com.bozeemcoder.accountservice.entity.Account;
import com.bozeemcoder.accountservice.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, String> {

    Balance findByAccount(Account account);
}
