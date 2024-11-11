package com.bozeemcoder.transactionservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnore
    List<Posting> postings;
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    List<RbTranHistLog> rbTranHistLogs;
    BigDecimal amount;
    String type;
}
