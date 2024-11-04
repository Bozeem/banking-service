package com.bozeemcoder.postingservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    ACCOUNT_EXISTED(1, "Account existed", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_EXISTED(2, "Account not existed", HttpStatus.BAD_REQUEST),
    FAILED_TO_RETRIEVE(3, "Failed to retrieve account information", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_EXISTED(4, "Customer not  existed", HttpStatus.BAD_REQUEST),
    BALANCE_NOT_EXISTED(5, "Balance not  existed", HttpStatus.BAD_REQUEST),
    BALANCE_IS_NULL(6, "Insufficient funds in sender's account", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
