package com.bozeemcoder.transactionservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class RbTranHistLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String logId;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    Transaction transaction;

    String status;
    String message;
    LocalDateTime timestamp;
}
