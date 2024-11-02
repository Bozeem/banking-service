package com.bozeemcoder.transactionservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String postingId;

    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    Transaction transaction;

    String type;
    BigDecimal amount;
    LocalDateTime postingDate;

}

