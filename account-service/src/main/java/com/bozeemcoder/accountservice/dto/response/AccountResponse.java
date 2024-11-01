package com.bozeemcoder.accountservice.dto.response;

import com.bozeemcoder.accountservice.entity.Balance;
import com.bozeemcoder.accountservice.entity.Customer;
import com.bozeemcoder.accountservice.entity.Transaction;
import com.bozeemcoder.accountservice.repository.BalanceRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    String accountId;
    Long accountNumber;
    String account_type;
    String account_status;
    CustomerResponse customer;
    BalanceResponse balance;
    List<TransactionResponse> transactions;
}
