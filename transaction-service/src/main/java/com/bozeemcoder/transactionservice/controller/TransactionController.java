package com.bozeemcoder.transactionservice.controller;

import com.bozeemcoder.transactionservice.dto.request.TransactionCreateRequest;
import com.bozeemcoder.transactionservice.dto.response.ApiResponse;
import com.bozeemcoder.transactionservice.dto.response.TransactionResponse;
import com.bozeemcoder.transactionservice.entity.Transaction;
import com.bozeemcoder.transactionservice.service.transaction.TransactionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TransactionController {
    TransactionService transactionService;

    @GetMapping("/transactions")
    ApiResponse<List<TransactionResponse>> getTransactions() {
        return ApiResponse.<List<TransactionResponse>>builder()
                .result(transactionService.getTransaction())
                .build();
    }
    @PostMapping("/transaction/create")
    ApiResponse<TransactionResponse> createTransaction(@RequestBody @Valid TransactionCreateRequest request) {
        return ApiResponse.<TransactionResponse>builder()
                .result(transactionService.createTransaction(request))
                .build();
    }
}
