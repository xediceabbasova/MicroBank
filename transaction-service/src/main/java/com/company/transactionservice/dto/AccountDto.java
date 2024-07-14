package com.company.transactionservice.dto;

import java.math.BigDecimal;

public record AccountDto (
        String id,
        BigDecimal balance
){
}
