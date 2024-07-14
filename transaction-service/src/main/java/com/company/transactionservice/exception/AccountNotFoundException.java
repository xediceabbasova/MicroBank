package com.company.transactionservice.exception;

public class AccountNotFoundException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public AccountNotFoundException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

}
