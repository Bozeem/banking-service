package com.bozeemcoder.accountservice.dto.response;

import com.bozeemcoder.accountservice.entity.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponse {
    AccountResponse fromAccount;
    AccountResponse toAccount;
    BigDecimal amount;
    String type;
}
