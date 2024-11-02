package com.bozeemcoder.transactionservice.dto.response;
import com.bozeemcoder.transactionservice.entity.RbTranHistLog;
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
    List<RbTranHistLog> rbTranHistLog;

}
