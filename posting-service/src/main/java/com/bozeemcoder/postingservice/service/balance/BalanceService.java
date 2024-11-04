package com.bozeemcoder.postingservice.service.balance;

import com.bozeemcoder.postingservice.dto.response.BalanceResponse;

public interface BalanceService {
    BalanceResponse updateBalance(String balanceId ,BalanceResponse balanceResponse) ;
}
