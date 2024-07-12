package com.company.userservice.dto.converter;

import com.company.userservice.dto.UserDto;
import com.company.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convert(User from) {
        return new UserDto(
                from.getId(),
                from.getFirstName(),
                from.getLastName(),
                from.getPhoneNumber(),
                from.getEmail()
        );
    }

    public List<UserDto> convert(List<User> from) {
        return from.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
