package com.bozeemcoder.postingservice.dto.request;


import com.bozeemcoder.postingservice.entity.Transaction;
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
