package com.company.notificationservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDto(
        String id,
        String fromAccountId,
        String toAccountId,
        BigDecimal amount,
        LocalDateTime transactionDate,
        TransactionStatusDto status
){
}

