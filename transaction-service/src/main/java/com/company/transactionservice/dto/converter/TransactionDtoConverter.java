package com.company.transactionservice.dto.converter;

import com.company.transactionservice.dto.TransactionDto;
import com.company.transactionservice.dto.TransactionStatusDto;
import com.company.transactionservice.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {

    public TransactionDto convert(Transaction from) {
        return new TransactionDto(
                from.getId(),
                from.getFromAccountId(),
                from.getToAccountId(),
                from.getAmount(),
                from.getTransactionDate(),
                TransactionStatusDto.valueOf(from.getStatus().name())
        );
    }
}
