package com.bozeemcoder.transactionservice.dto.response;
import com.bozeemcoder.transactionservice.dto.response.BalanceResponse;
import com.bozeemcoder.transactionservice.dto.response.TransactionResponse;
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
