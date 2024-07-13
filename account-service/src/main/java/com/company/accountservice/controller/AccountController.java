package com.company.accountservice.controller;

import com.company.accountservice.dto.AccountDto;
import com.company.accountservice.dto.request.AccountRequest;
import com.company.accountservice.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountRequest request) {
        return new ResponseEntity<>(accountService.createAccount(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> deactivateAccount(@PathVariable String id) {
        accountService.deactivateAccount(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PutMapping("/{id}/add")
    public ResponseEntity<AccountDto> addMoney(@PathVariable String id, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountService.addMoney(id, amount));
    }

    @PutMapping("/{id}/withDraw")
    public ResponseEntity<AccountDto> withDrawMoney(@PathVariable String id, @RequestParam BigDecimal amount) {
        return ResponseEntity.ok(accountService.withDrawMoney(id, amount));
    }
}
