package com.company.transactionservice.exception;

public class InsufficientFundsException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public InsufficientFundsException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}
