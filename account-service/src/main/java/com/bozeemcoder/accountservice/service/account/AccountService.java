package com.bozeemcoder.accountservice.service.account;
import com.bozeemcoder.accountservice.dto.request.AccountCreateRequest;
import com.bozeemcoder.accountservice.dto.request.AccountUpdateRequest;
import com.bozeemcoder.accountservice.dto.response.AccountResponse;


import java.util.List;

public interface AccountService {
    List<AccountResponse> getAccounts();
    AccountResponse getAccountById(String accountId);
    AccountResponse createAccount(AccountCreateRequest request);
    AccountResponse updateAccount(String accountId,AccountUpdateRequest request);
    void deleteAccount(String accountId);

}
