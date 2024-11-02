package com.bozeemcoder.transactionservice.dto.request;

import com.bozeemcoder.transactionservice.entity.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionCreateRequest {
    Account fromAccount;
    Account toAccount;
    BigDecimal amount;
    String type;

}
