package com.bozeemcoder.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Account {
    private String requestId;
    private String accountNumber;
    private String userId;
    private Balance balance;
    private Transaction transaction;
}
