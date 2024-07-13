package com.company.accountservice.service;

import com.company.accountservice.client.UserServiceClient;
import com.company.accountservice.dto.AccountDto;
import com.company.accountservice.dto.converter.AccountDtoConverter;
import com.company.accountservice.dto.request.AccountRequest;
import com.company.accountservice.exception.AccountIsNotActiveException;
import com.company.accountservice.exception.AccountNotFoundException;
import com.company.accountservice.exception.InsufficientFundsException;
import com.company.accountservice.model.Account;
import com.company.accountservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountDtoConverter converter;
    private final UserServiceClient client;

    public AccountService(AccountRepository accountRepository, AccountDtoConverter converter, UserServiceClient client) {
        this.accountRepository = accountRepository;
        this.converter = converter;
        this.client = client;
    }

    public AccountDto createAccount(final AccountRequest request) {
        client.getUserById(request.userId()).getBody();
        Account account = new Account(request.userId());
        return converter.convert(accountRepository.save(account));
    }

    public void deactivateAccount(final String accountId) {
        Account account = findAccountById(accountId);
        Account updatedAccount = new Account(
                account.getId(),
                account.getUserId(),
                account.getAccountNumber(),
                account.getBalance(),
                account.getCreatedAt(),
                false
        );
        accountRepository.save(updatedAccount);
    }

    public AccountDto getAccountById(final String id) {
        return converter.convert(findActiveAccountById(id));
    }

    public AccountDto addMoney(final String accountId, final BigDecimal amount) {
        Account account = findActiveAccountById(accountId);
        Account newAccount = new Account(
                account.getId(),
                account.getUserId(),
                account.getAccountNumber(),
                account.getBalance().add(amount),
                account.getCreatedAt(),
                account.isActive()
        );
        return converter.convert(accountRepository.save(newAccount));
    }

    public AccountDto withDrawMoney(final String accountId, final BigDecimal amount) {
        Account account = findActiveAccountById(accountId);
        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Account with id " + accountId + " has insufficient funds.");
        }
        Account newAccount = new Account(
                account.getId(),
                account.getUserId(),
                account.getAccountNumber(),
                account.getBalance().subtract(amount),
                account.getCreatedAt(),
                account.isActive()
        );
        return converter.convert(accountRepository.save(newAccount));
    }

    private Account findAccountById(final String id) {
        return accountRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);
    }

    private Account findActiveAccountById(final String id) {
        Account account = findAccountById(id);
        if (!account.isActive()) {
            throw new AccountIsNotActiveException();
        }
        return account;
    }
}
