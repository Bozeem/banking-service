package com.bozeemcoder.postingservice.dto.response;

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
