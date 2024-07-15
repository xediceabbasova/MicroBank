package com.company.notificationservice.client;

import com.company.notificationservice.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", path = "/v1/accounts")
public interface AccountServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<AccountDto> getAccountById(@PathVariable String id);

}
