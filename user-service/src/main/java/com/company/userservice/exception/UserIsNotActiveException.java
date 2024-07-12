package com.company.userservice.exception;

public class UserIsNotActiveException extends RuntimeException {

    private static final String message = "The user wanted update is not active!";

    public UserIsNotActiveException() {
        super(message);
    }
}
