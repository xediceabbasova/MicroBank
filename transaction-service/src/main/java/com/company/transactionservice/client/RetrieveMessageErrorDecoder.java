package com.company.transactionservice.client;

import com.company.transactionservice.exception.AccountIsNotActiveException;
import com.company.transactionservice.exception.AccountNotFoundException;
import com.company.transactionservice.exception.ExceptionMessage;
import com.company.transactionservice.exception.InsufficientFundsException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message;
        try (InputStream body = response.body().asInputStream()) {
            message = new ExceptionMessage(
                    (String) response.headers().get("date").toArray()[0],
                    response.status(),
                    HttpStatus.resolve(response.status()).getReasonPhrase(),
                    IOUtils.toString(body, StandardCharsets.UTF_8),
                    response.request().url()
            );

        } catch (IOException exception) {
            return new Exception(exception.getMessage());
        }
        return switch (response.status()) {
            case 404 -> throw new AccountNotFoundException(message);
            case 400 -> {
                if (message.message().contains("Account is not active")) {
                    throw new AccountIsNotActiveException(message);
                } else {
                    throw new InsufficientFundsException(message);
                }
            }
            default -> errorDecoder.decode(methodKey, response);
        };
    }
}
