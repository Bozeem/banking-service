package com.bozeemcoder.accountservice.entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String transactionId;

    @ManyToOne
    @JoinColumn(name = "from_account_id", nullable = false)
    Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id", nullable = false)
    Account toAccount;

    BigDecimal amount;
    String type;
}
