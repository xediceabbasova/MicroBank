package com.company.userservice.dto.request;

public record UpdateUserRequest(
        String phoneNumber,
        String email,
        String password
) {
}
