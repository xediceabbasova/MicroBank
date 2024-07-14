package com.company.transactionservice.service;

import com.company.transactionservice.client.AccountServiceClient;
import com.company.transactionservice.dto.AccountDto;
import com.company.transactionservice.dto.TransactionDto;
import com.company.transactionservice.dto.converter.TransactionDtoConverter;
import com.company.transactionservice.dto.request.TransactionRequest;
import com.company.transactionservice.kafka.KafkaProducer;
import com.company.transactionservice.model.Transaction;
import com.company.transactionservice.model.TransactionStatus;
import com.company.transactionservice.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionDtoConverter converter;
    private final AccountServiceClient client;
    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


    public TransactionService(TransactionRepository repository, TransactionDtoConverter converter, AccountServiceClient client, KafkaProducer kafkaProducer) {
        this.repository = repository;
        this.converter = converter;
        this.client = client;
        this.kafkaProducer = kafkaProducer;
    }

    public TransactionDto createTransaction(TransactionRequest request) {

        AccountDto fromAccount = client.getAccountById(request.fromAccountId()).getBody();
        AccountDto toAccount = client.getAccountById(request.toAccountId()).getBody();

        Transaction transaction = new Transaction(
                request.fromAccountId(),
                request.toAccountId(),
                request.amount(),
                request.currency()
        );
        repository.save(transaction);

        try {
            client.withDrawMoney(Objects.requireNonNull(toAccount).id(), request.amount());
            client.addMoney(Objects.requireNonNull(fromAccount).id(), request.amount());
            transaction.updateStatus(TransactionStatus.CONFIRMED);
        } catch (FeignException e) {
            transaction.updateStatus(TransactionStatus.CANCELLED);
        } finally {
            repository.save(transaction);
            send(transaction);
        }
        return converter.convert(transaction);
    }

    private void send(Transaction transaction) {
        String message;
        try {
            message = objectMapper.writeValueAsString(converter.convert(transaction));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting TransactionDto to JSON", e);
        }
        kafkaProducer.sendMessage(message);
    }

}
