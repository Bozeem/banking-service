package com.bozeemcoder.postingservice.repository;


import com.bozeemcoder.postingservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByAccountId(String accountId);
}
