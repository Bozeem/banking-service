package com.bozeemcoder.transactionservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

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
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<Posting> postings;
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<RbTranHistLog> rbTranHistLogs;
    BigDecimal amount;
    String type;
}
