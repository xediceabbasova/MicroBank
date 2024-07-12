package com.company.accountservice.exception;

public class UserNotFoundException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public UserNotFoundException(ExceptionMessage message) {
        this.exceptionMessage = message;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}
