package com.bozeemcoder.transactionservice.service.account;

import com.bozeemcoder.transactionservice.dto.response.AccountResponse;
import com.bozeemcoder.transactionservice.entity.Account;

import java.util.List;

public interface AccountService {


    AccountResponse getAccountById(String accountId);
    public void mapAccount(String accountId);
    List<AccountResponse> getAccounts();

}
