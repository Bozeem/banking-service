package com.bozeemcoder.postingservice.mapper;


import com.bozeemcoder.postingservice.dto.request.TransactionCreateRequest;
import com.bozeemcoder.postingservice.dto.response.TransactionResponse;
import com.bozeemcoder.postingservice.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse toTransactionResponse(Transaction transaction);
    Transaction toTransaction(TransactionCreateRequest request);

}
