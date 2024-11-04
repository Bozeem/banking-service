package com.bozeemcoder.postingservice.dto.request;


import com.bozeemcoder.postingservice.entity.Account;
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
