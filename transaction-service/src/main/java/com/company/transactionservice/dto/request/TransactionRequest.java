package com.company.transactionservice.dto.request;

import com.company.transactionservice.model.Currency;

import java.math.BigDecimal;

public record TransactionRequest(
        String fromAccountId,
        String toAccountId,
        BigDecimal amount,
        Currency currency
) {
}
