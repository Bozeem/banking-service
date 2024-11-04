package com.bozeemcoder.postingservice.service.balance;

import com.bozeemcoder.postingservice.dto.response.BalanceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

public class BalanceServiceImpl implements BalanceService {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    @Async
    public BalanceResponse updateBalance(String balanceId, BalanceResponse balanceResponse) {
        return null;
    }
}
