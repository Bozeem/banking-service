package com.bozeemcoder.transactionservice.dto.request;

import com.bozeemcoder.transactionservice.dto.response.TransactionResponse;
import com.bozeemcoder.transactionservice.entity.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostingCreateRequest {
    Transaction transaction;
    String type;
    BigDecimal amount;
    LocalDateTime postingDate;
}
