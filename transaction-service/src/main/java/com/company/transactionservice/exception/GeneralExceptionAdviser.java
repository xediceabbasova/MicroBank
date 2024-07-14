package com.company.transactionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionAdviser {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handle(AccountNotFoundException exception) {
        return new ResponseEntity<>(exception.getExceptionMessage(), HttpStatus.resolve(exception.getExceptionMessage().status()));
    }

    @ExceptionHandler(AccountIsNotActiveException.class)
    public ResponseEntity<ExceptionMessage> handle(AccountIsNotActiveException exception) {
        return new ResponseEntity<>(exception.getExceptionMessage(), HttpStatus.resolve(exception.getExceptionMessage().status()));
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ExceptionMessage> handle(InsufficientFundsException exception) {
        return new ResponseEntity<>(exception.getExceptionMessage(), HttpStatus.resolve(exception.getExceptionMessage().status()));
    }
}
