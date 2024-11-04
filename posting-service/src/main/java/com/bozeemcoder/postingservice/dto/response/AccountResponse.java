package com.bozeemcoder.postingservice.dto.response;

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
