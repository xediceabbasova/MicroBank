package com.company.accountservice.dto.converter;

import com.company.accountservice.dto.AccountDto;
import com.company.accountservice.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {

    public AccountDto convert(Account from) {
        return new AccountDto(
                from.getId(),
                from.getBalance(),
                from.isActive()
        );
    }
}
