package com.company.userservice.dto;

public record UserDto(
        String id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {
}
