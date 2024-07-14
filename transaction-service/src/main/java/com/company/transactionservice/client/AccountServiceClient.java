package com.company.transactionservice.client;

import com.company.transactionservice.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(name = "account-service", path = "/v1/accounts")
public interface AccountServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<AccountDto> getAccountById(@PathVariable String id);

    @PutMapping("/{id}/add")
    ResponseEntity<AccountDto> addMoney(@PathVariable String id, @RequestParam BigDecimal amount);

    @PutMapping("/{id}/withDraw")
    ResponseEntity<AccountDto> withDrawMoney(@PathVariable String id, @RequestParam BigDecimal amount);


}
