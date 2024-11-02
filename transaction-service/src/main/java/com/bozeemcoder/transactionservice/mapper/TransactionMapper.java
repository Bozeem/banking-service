package com.bozeemcoder.transactionservice.mapper;

import com.bozeemcoder.transactionservice.dto.request.TransactionCreateRequest;
import com.bozeemcoder.transactionservice.dto.response.TransactionResponse;
import com.bozeemcoder.transactionservice.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transaction transaction);
    Transaction toTransaction(TransactionCreateRequest request);

}
