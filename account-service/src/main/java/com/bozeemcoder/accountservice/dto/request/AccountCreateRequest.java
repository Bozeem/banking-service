package com.bozeemcoder.accountservice.dto.request;

import com.bozeemcoder.accountservice.entity.Balance;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreateRequest {
    Long accountNumber;
    String account_type;
    String account_status;
}
