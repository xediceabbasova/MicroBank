package com.company.notificationservice.dto;

import java.math.BigDecimal;

public record AccountDto(
        String id,
        String userId,
        BigDecimal balance
) {
}
