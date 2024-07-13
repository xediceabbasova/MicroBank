package com.company.accountservice.exception;

public class UserIsNotActiveException extends RuntimeException {

    private final ExceptionMessage exceptionMessage;

    public UserIsNotActiveException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }
}
