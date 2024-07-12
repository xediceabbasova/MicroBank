package com.company.userservice.dto.request;

public record UserRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String password
) {
}
