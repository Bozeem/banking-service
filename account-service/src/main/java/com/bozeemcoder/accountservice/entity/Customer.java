package com.bozeemcoder.accountservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long customerId;

    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String address;

    @OneToMany
    Set<Account> accounts;
}
