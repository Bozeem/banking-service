package com.bozeemcoder.transactionservice.service.account;

import com.bozeemcoder.transactionservice.dto.response.AccountResponse;
import com.bozeemcoder.transactionservice.dto.response.ApiResponse;
import com.bozeemcoder.transactionservice.entity.Account;
import com.bozeemcoder.transactionservice.entity.Balance;
import com.bozeemcoder.transactionservice.exception.DomainException;
import com.bozeemcoder.transactionservice.exception.ErrorCode;
import com.bozeemcoder.transactionservice.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    RestTemplate restTemplate;
    AccountRepository accountRepository;

    String ACCOUNT_SERVICE_URL = "http://localhost:8080/account-service/account/read";

    @Override
    public AccountResponse getAccountById(String accountId) {
        return null;
    }

    @Override
    public void mapAccount(String accountId) {
        Account accountREST = retrieveAccount(accountId);
        existingAccount(accountREST, accountRepository);
    }

    public static void existingAccount(Account accountREST, AccountRepository accountRepository) {
        Account existingAccount = accountRepository.findByAccountId(accountREST.getAccountId());
        if (existingAccount != null) {
            // Nếu tài khoản đã tồn tại, cập nhật thông tin của nó
            existingAccount.setAccountNumber(accountREST.getAccountNumber());
            existingAccount.setAccount_type(accountREST.getAccount_type());
            existingAccount.setAccount_status(accountREST.getAccount_status());
            existingAccount.setIncomingTransactions(accountREST.getIncomingTransactions());
            existingAccount.setOutgoingTransactions(accountREST.getOutgoingTransactions());
            existingAccount.getBalance().setAmount(accountREST.getBalance().getAmount()); // Cập nhật số dư
            accountRepository.save(existingAccount);
        } else {
            // Nếu tài khoản chưa tồn tại, tạo mới và lưu
            Balance balance = new Balance(accountREST.getBalance().getBalanceId(), accountREST, accountREST.getBalance().getAmount());
            Account newAccount = new Account();
            newAccount.setAccountId(accountREST.getAccountId());
            newAccount.setBalance(balance);
            newAccount.setAccountNumber(accountREST.getAccountNumber());
            newAccount.setAccount_type(accountREST.getAccount_type());
            newAccount.setAccount_status(accountREST.getAccount_status());
            newAccount.setIncomingTransactions(accountREST.getIncomingTransactions());
            newAccount.setOutgoingTransactions(accountREST.getOutgoingTransactions());
            accountRepository.save(newAccount);

        }
    }

    @Override
    public List<AccountResponse> getAccounts() {
        return null;
    }
    private Account retrieveAccount(String accountId) {
        return getAccount(accountId, restTemplate, ACCOUNT_SERVICE_URL);
    }

    public static Account getAccount(String accountId, RestTemplate restTemplate, String accountServiceUrl) {
        ApiResponse<Account> response = restTemplate.exchange(
                accountServiceUrl + "/" + accountId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<Account>>() {}
        ).getBody();

        if (response == null || response.getCode() != 0) {
            throw new DomainException(ErrorCode.FAILED_TO_RETRIEVE);
        }
        return response.getResult();
    }

}
