package com.bozeemcoder.accountservice.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String balanceId;
    @OneToOne
    @JoinColumn(name = "accountId")
    @JsonBackReference
    Account account;
    BigDecimal amount;

    public Balance(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }
}
