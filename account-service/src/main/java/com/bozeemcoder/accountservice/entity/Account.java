package com.bozeemcoder.accountservice.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String accountId;

    Long accountNumber;
    String account_type;
    String account_status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @OneToOne(mappedBy = "account",fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    Balance balance;

    @OneToMany(mappedBy = "fromAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Transaction> outgoingTransactions;
    @OneToMany(mappedBy = "toAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Transaction> incomingTransactions;

    public Account(String accountId) {
        this.accountId = accountId;
    }

    public Account initZeroBalance() {
        var balance = new Balance(this, BigDecimal.ZERO);
        this.setBalance(balance);
        return this;
    }
}
