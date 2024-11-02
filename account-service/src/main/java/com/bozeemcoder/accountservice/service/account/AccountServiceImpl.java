package com.bozeemcoder.accountservice.service.account;

import com.bozeemcoder.accountservice.dto.request.AccountCreateRequest;
import com.bozeemcoder.accountservice.dto.request.AccountUpdateRequest;
import com.bozeemcoder.accountservice.dto.response.AccountResponse;
import com.bozeemcoder.accountservice.entity.Account;
import com.bozeemcoder.accountservice.entity.Balance;
import com.bozeemcoder.accountservice.exception.DomainException;
import com.bozeemcoder.accountservice.exception.ErrorCode;
import com.bozeemcoder.accountservice.mapper.AccountMapper;
import com.bozeemcoder.accountservice.repository.AccountRepository;
import com.bozeemcoder.accountservice.repository.BalanceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    BalanceRepository balanceRepository;

    @Override
    public List<AccountResponse> getAccounts() {
        log.info("In method get Accounts");
        return accountRepository.findAll().stream()
                .map(accountMapper::toAccountResponse)
                .toList();
    }

    @Override
    public AccountResponse getAccountById(String accountId) {
        Account account = accountRepository.findById(accountId).
                orElseThrow(() -> new DomainException(ErrorCode.ACCOUNT_NOT_EXISTED));
        return accountMapper.toAccountResponse(account);
    }
    @Override
    public AccountResponse createAccount(AccountCreateRequest request) {
        Account account = accountMapper.toAccount(request);
        Balance balance = new Balance(account, new BigDecimal("0"));
        account.setBalance(balance);
        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    @Override
    public AccountResponse updateAccount(String accountId,AccountUpdateRequest request) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new DomainException(ErrorCode.ACCOUNT_NOT_EXISTED));
        accountMapper.updateAccount(account, request);
        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    @Override
    public void deleteAccount(String accountId) {
        accountRepository.findById(accountId)
                .ifPresentOrElse(account -> {

                    Balance balance = balanceRepository.findByAccount(account);
                    if (balance != null) {
                        balanceRepository.delete(balance);
                    }
                    accountRepository.delete(account);
                }, () -> {
                    throw new DomainException(ErrorCode.ACCOUNT_NOT_EXISTED);
                });
    }
}
