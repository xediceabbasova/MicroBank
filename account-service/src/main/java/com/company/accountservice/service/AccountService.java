package com.company.accountservice.service;

import com.company.accountservice.dto.AccountDto;
import com.company.accountservice.dto.converter.AccountDtoConverter;
import com.company.accountservice.dto.request.AccountRequest;
import com.company.accountservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository, AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.converter = converter;
    }

    public AccountDto createAccount(AccountRequest request){

    }
}
