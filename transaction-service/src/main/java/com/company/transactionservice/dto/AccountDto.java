package com.company.transactionservice.dto;

import java.math.BigDecimal;

public record AccountDto(
        String id,
        String userId,
        BigDecimal balance
) {
}
