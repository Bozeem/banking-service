package com.bozeemcoder.transactionservice.controller;



import com.bozeemcoder.transactionservice.dto.response.AccountResponse;
import com.bozeemcoder.transactionservice.dto.response.ApiResponse;
import com.bozeemcoder.transactionservice.service.account.AccountService;
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
public class AccountController {
    AccountService accountService;

    @GetMapping("/accounts")
    ApiResponse<List<AccountResponse>> getAccounts() {
        return ApiResponse.<List<AccountResponse>>builder()
                .result(accountService.getAccounts())
                .build();
    }
    @PostMapping("/account/map/{accountId}")
    ApiResponse<String> mapAccount(@PathVariable("accountId") String accountId) {
        accountService.mapAccount(accountId);
        return ApiResponse.<String>builder()
                .result("Map successful!!!")
                .build();
    }
    @GetMapping("/account/read/{accountId}")
    ApiResponse<AccountResponse> getAccount(@PathVariable("accountId") String accountId) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.getAccountById(accountId))
                .build();
    }

//    @PutMapping("/account/update/{accountId}")
//    ApiResponse<AccountResponse> updateAccount(@PathVariable("accountId") String accountId, @RequestBody AccountUpdateRequest request) {
//        return ApiResponse.<AccountResponse>builder()
//                .result(accountService.updateAccount(accountId, request))
//                .build();
//    }
//    @DeleteMapping("/account/delete/{accountId}")
//    ApiResponse<String> deleteAccount(@PathVariable String accountId) {
//        accountService.deleteAccount(accountId);
//        return ApiResponse.<String>builder().result("Account has been deleted").build();
//    }

}
