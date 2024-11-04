package com.bozeemcoder.accountservice.controller;
import com.bozeemcoder.accountservice.dto.response.ApiResponse;
import com.bozeemcoder.accountservice.service.balance.BalanceService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("${api.prefix}")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BalanceController {
    BalanceService balanceService;
    @PutMapping("/balance/income/{balanceId}/{amount}")
    ApiResponse<String> incomeBalance(@PathVariable("balanceId") String accountId, @PathVariable("amount") BigDecimal amount) {
        balanceService.incomeBalance(accountId, amount);
        return ApiResponse.<String>builder()
                .result("Update balance success !")
                .build();
    }
    @PutMapping("/balance/outcome/{balanceId}/{amount}")
    ApiResponse<String> outcomeBalance(@PathVariable("balanceId") String accountId, @PathVariable("amount") BigDecimal amount) {
        balanceService.outcomeBalance(accountId, amount);
        return ApiResponse.<String>builder()
                .result("Update balance success !")
                .build();
    }

}
