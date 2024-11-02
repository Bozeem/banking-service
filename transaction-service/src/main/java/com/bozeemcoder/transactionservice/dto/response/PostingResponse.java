package com.bozeemcoder.transactionservice.dto.response;

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
public class PostingResponse {
    String postingId;
    String type;
    BigDecimal amount;
    LocalDateTime postingDate;
}
