package com.bozeemcoder.transactionservice.service.transaction;

import com.bozeemcoder.transactionservice.dto.request.TransactionCreateRequest;
import com.bozeemcoder.transactionservice.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> getTransaction( );
    TransactionResponse getTransactionById(String transactionId );

    TransactionResponse createTransaction(TransactionCreateRequest request);
}
