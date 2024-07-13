package com.company.userservice.service;

import com.company.userservice.dto.UserDto;
import com.company.userservice.dto.converter.UserDtoConverter;
import com.company.userservice.dto.request.UpdateUserRequest;
import com.company.userservice.dto.request.UserRequest;
import com.company.userservice.exception.UserIsNotActiveException;
import com.company.userservice.exception.UserNotFoundException;
import com.company.userservice.model.User;
import com.company.userservice.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter converter;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, UserDtoConverter converter, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.encoder = encoder;
    }

    public UserDto createUser(final UserRequest request) {
        User user = new User(
                request.firstName(),
                request.lastName(),
                request.phoneNumber(),
                request.email(),
                encoder.encode(request.password()),
                false
        );
        return converter.convert(userRepository.save(user));
    }

    public UserDto updateUser(final String userId, final UpdateUserRequest request) {
        User user = findActiveUserById(userId);

        User updatedUser = new User(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                request.phoneNumber() != null ? request.phoneNumber() : user.getPhoneNumber(),
                request.email() != null ? request.email() : user.getEmail(),
                request.password() != null ? encoder.encode(request.password()) : user.getPassword(),
                user.isActive());
        return converter.convert(userRepository.save(updatedUser));
    }

    public List<UserDto> getAllUsers() {
        return converter.convert(userRepository.findAll());
    }

    public UserDto getUserById(final String id) {
        return converter.convert(findActiveUserById(id));
    }

    public void activateUser(final String id) {
        changeActivateUser(id, true);
    }

    public void deactivateUser(final String id) {
        changeActivateUser(id, false);
    }

    private void changeActivateUser(final String id, final boolean isActive) {
        User user = findUserById(id);
        User updatedUser = new User(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getPassword(),
                isActive
        );
        userRepository.save(updatedUser);
    }

    private User findActiveUserById(final String id) {
        User user = findUserById(id);
        if (!user.isActive()) {
            throw new UserIsNotActiveException();
        }
        return user;
    }

    private User findUserById(final String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User couldn't be found by following id: " + id));
    }
}
