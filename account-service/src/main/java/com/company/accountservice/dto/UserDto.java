package com.company.accountservice.dto;

public record UserDto(
        String id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {
}
