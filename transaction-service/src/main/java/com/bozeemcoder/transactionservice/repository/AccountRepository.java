package com.bozeemcoder.transactionservice.repository;

import com.bozeemcoder.transactionservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByAccountId(String accountId);
}
