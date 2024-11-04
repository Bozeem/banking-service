package com.bozeemcoder.accountservice.service.balance;

import com.bozeemcoder.accountservice.entity.Account;
import com.bozeemcoder.accountservice.entity.Balance;

import java.math.BigDecimal;

public interface BalanceService {
    void incomeBalance(String balanceId, BigDecimal amount);
    void outcomeBalance(String balanceId, BigDecimal amount);
}
