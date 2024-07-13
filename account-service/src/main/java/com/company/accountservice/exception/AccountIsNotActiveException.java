package com.company.accountservice.exception;

public class AccountIsNotActiveException extends RuntimeException {

    private static final String message = "Account is not active!";

    public AccountIsNotActiveException() {
        super(message);
    }
}
