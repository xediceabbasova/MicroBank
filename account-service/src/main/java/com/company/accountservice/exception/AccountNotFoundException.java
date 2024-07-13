package com.company.accountservice.exception;

public class AccountNotFoundException extends RuntimeException {

    private static final String message = "Account couldn't be found!";

    public AccountNotFoundException() {
        super(message);
    }
}
