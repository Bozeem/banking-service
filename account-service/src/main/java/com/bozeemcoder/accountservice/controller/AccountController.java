package com.bozeemcoder.accountservice.controller;
import java.util.List;
import com.bozeemcoder.accountservice.dto.request.AccountCreateRequest;
import com.bozeemcoder.accountservice.dto.request.AccountUpdateRequest;
import com.bozeemcoder.accountservice.dto.response.AccountResponse;
import com.bozeemcoder.accountservice.dto.response.ApiResponse;
import com.bozeemcoder.accountservice.entity.Account;
import com.bozeemcoder.accountservice.service.account.AccountService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


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
    @PostMapping("/account/create")
    ApiResponse<AccountResponse> createAccount(@RequestBody @Valid AccountCreateRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.createAccount(request))
                .build();
    }
    @GetMapping("/account/read/{accountId}")
    ApiResponse<AccountResponse> getAccount(@PathVariable("accountId") String accountId) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.getAccountById(accountId))
                .build();
    }

    @PutMapping("/account/update/{accountId}")
    ApiResponse<AccountResponse> updateAccount(@PathVariable("accountId") String accountId, @RequestBody AccountUpdateRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.updateAccount(accountId, request))
                .build();
    }
    @DeleteMapping("/account/delete/{accountId}")
    ApiResponse<String> deleteAccount(@PathVariable String accountId) {
        accountService.deleteAccount(accountId);
        return ApiResponse.<String>builder().result("User has been deleted").build();
    }

}
