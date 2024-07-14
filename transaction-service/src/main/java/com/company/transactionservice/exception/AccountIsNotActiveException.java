package com.company.transactionservice.exception;

public class AccountIsNotActiveException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public AccountIsNotActiveException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}
