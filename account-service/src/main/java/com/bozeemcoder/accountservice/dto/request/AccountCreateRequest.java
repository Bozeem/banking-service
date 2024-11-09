package com.bozeemcoder.accountservice.dto.request;


import com.bozeemcoder.accountservice.dto.request.validator.DateTimeString;
import jakarta.validation.constraints.NotBlank;

public record AccountCreateRequest(
        @NotBlank(message = "001")
        Long accountNumber,
        @NotBlank(message = "002")
        String account_type,
        @NotBlank(message = "003")
        String account_status,

        //Todo: Remove after test the validation annotation
        @DateTimeString(message = "004")
        String openingDatetime
) {}
