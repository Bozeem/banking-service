package com.bozeemcoder.postingservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Balance {
    @Id
    String balanceId;
    @OneToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    Account account;
    BigDecimal amount;

    public Balance(String balanceId,Account account, BigDecimal amount) {
        this.balanceId = balanceId;
        this.account = account;
        this.amount = amount;
    }
}
