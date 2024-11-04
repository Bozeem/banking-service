package com.bozeemcoder.postingservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponse {
    String transactionId;
    AccountResponse fromAccount;
    AccountResponse toAccount;
    BigDecimal amount;
    String type;
    List<PostingResponse> postings;

}
