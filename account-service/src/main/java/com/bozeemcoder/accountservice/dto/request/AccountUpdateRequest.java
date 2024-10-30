package com.bozeemcoder.accountservice.dto.request;

import com.bozeemcoder.accountservice.dto.response.BalanceResponse;
import com.bozeemcoder.accountservice.dto.response.CustomerResponse;
import com.bozeemcoder.accountservice.entity.Balance;
import com.bozeemcoder.accountservice.entity.Customer;
import com.bozeemcoder.accountservice.entity.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountUpdateRequest {
    Long accountNumber;
    String account_type;
    String account_status;
}
