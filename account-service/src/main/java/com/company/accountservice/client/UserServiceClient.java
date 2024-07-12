package com.company.accountservice.client;

import com.company.accountservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", path = "/v1/users")
public interface UserServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable("id") String id);


}
