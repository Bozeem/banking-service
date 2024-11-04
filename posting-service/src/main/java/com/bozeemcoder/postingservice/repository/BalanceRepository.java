package com.bozeemcoder.postingservice.repository;


import com.bozeemcoder.postingservice.entity.Account;
import com.bozeemcoder.postingservice.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, String> {

    Balance findByAccount(Account account);
}
