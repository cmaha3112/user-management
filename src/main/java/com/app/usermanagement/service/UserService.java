package com.app.usermanagement.service;

import com.app.usermanagement.model.User;
import com.app.usermanagement.requestdto.UserRequestDto;
import com.app.usermanagement.responsedto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    String createUser(UserRequestDto user);

    List<String> createUsers(List<UserRequestDto> users);

    List<UserResponseDto> getAllUsers(Optional<Boolean> active);

    Optional<UserResponseDto> getUserById(String id);

    Optional<UserResponseDto> getUserByName(String name);

    Optional<UserResponseDto> getUserByEmail(String email);

    String updateUser(String id, User user);

    String deleteUserById(String id);

    String deleteByName(String name);

    String deleteByEmail(String email);

    String deleteAll(Optional<Boolean> active);
}
