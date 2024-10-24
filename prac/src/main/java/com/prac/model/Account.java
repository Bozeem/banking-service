package com.prac.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {
    private String requestId;
    private String accountNumber;
    private String userId;
    private Balance balance;
    private Transaction transaction;
}
