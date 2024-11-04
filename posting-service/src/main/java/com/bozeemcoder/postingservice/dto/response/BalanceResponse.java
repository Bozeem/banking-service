package com.bozeemcoder.postingservice.dto.response;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BalanceResponse {
    String balanceId;
    BigDecimal amount;
}
