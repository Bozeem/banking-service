package com.bozeemcoder.accountservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceEvent {
    private String balanceId;
    private BigDecimal amount;
    private String eventType; // INCOME or OUTCOME
    private LocalDateTime timestamp;
}