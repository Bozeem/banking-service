package com.bozeemcoder.transactionservice.dto.response;

import com.bozeemcoder.transactionservice.entity.Transaction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RbTranHistLogResponse {
    String logId;

    String status;
    String message;
    LocalDateTime timestamp;
}
