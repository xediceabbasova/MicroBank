package com.company.accountservice.dto;

import java.math.BigDecimal;

public record AccountDto(
        String id,
        BigDecimal balance
) {
}
