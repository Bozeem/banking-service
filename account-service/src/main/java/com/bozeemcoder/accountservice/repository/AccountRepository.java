package com.bozeemcoder.accountservice.repository;

import com.bozeemcoder.accountservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
