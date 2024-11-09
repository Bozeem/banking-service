package com.bozeemcoder.accountservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    ACCOUNT_EXISTED(1, "Account existed", HttpStatus.BAD_REQUEST),
    ACCOUNT_NOT_EXISTED(2, "Account not existed", HttpStatus.BAD_REQUEST),
    CUSTOMER_EXISTED(3, "Customer existed", HttpStatus.BAD_REQUEST),
    CUSTOMER_NOT_EXISTED(4, "Customer not  existed", HttpStatus.BAD_REQUEST),
    BALANCE_NOT_EXISTED(5, "Balance not  existed", HttpStatus.BAD_REQUEST),
    CREATE_ACCOUNT_FAIL(6, "Create account fail", HttpStatus.INTERNAL_SERVER_ERROR),
    UPDATE_ACCOUNT_FAIL(6, "Update account fail", HttpStatus.INTERNAL_SERVER_ERROR),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
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
