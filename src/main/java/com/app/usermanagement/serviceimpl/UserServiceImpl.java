package com.app.usermanagement.serviceimpl;

import com.app.usermanagement.Exception.UserNotFoundException;
import com.app.usermanagement.model.User;
import com.app.usermanagement.repository.UserRepository;
import com.app.usermanagement.requestdto.UserRequestDto;
import com.app.usermanagement.responsedto.UserResponseDto;
import com.app.usermanagement.service.UserService;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto) {
        if (userRepository.findByNameIgnoreCase(userRequestDto.getName()).isPresent() ||
                userRepository.findByEmailIgnoreCase(userRequestDto.getEmail()).isPresent()) {
            return "User with same name or email already exists.";
        }
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(Base64Util.encode(user.getPassword()));
        user.setCreateDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        user.setActive(true);
        userRepository.save(user);
        return "User created successfully.";
    }

    public List<String> createUsers(List<UserRequestDto> users) {
        List<String> results = new ArrayList<>();
        for (UserRequestDto user : users) {
            results.add(createUser(user));
        }
        return results;
    }

    public List<UserResponseDto> getAllUsers(Optional<Boolean> active) {
        List<User> users = active
                .map(userRepository::findByActive)
                .orElseGet(userRepository::findAll);

        return users.stream()
                .map(UserResponseDto::new)
                .toList();
    }

    public Optional<UserResponseDto> getUserById(String userId) {
        return userRepository.findById(userId)
                .map(UserResponseDto::new);
    }

    public Optional<UserResponseDto> getUserByName(String name) {
        return userRepository.findByNameIgnoreCase(name)
                .map(UserResponseDto::new);
    }

    public Optional<UserResponseDto> getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .map(UserResponseDto::new);
    }


    public String updateUser(String userId, User updated) {
        Optional<User> existing = userRepository.findById(userId);
        if (existing.isEmpty()) return "User not found";

        User user = existing.get();
        if (updated.getName() != null) user.setName(updated.getName());
        if (updated.getEmail() != null) user.setEmail(updated.getEmail());
        if (updated.getPassword() != null) user.setPassword(Base64Util.encode(updated.getPassword()));
        user.setUpdatedDate(LocalDateTime.now());
        userRepository.save(user);
        return "User updated successfully";
    }

    public String deleteUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setActive(false);
        user.setUpdatedDate(LocalDateTime.now());
        userRepository.save(user);
        return "User deleted";
    }

    public String deleteByName(String name) {
        Optional<User> optionalUser = userRepository.findByNameIgnoreCase(name);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
            user.setUpdatedDate(LocalDateTime.now());
            userRepository.save(user);
            return "User deleted by name: " + name;
        } else {
            throw new UserNotFoundException("User with name '" + name + "' not found");
        }
    }



    public String deleteByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmailIgnoreCase(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
            user.setUpdatedDate(LocalDateTime.now());
            userRepository.save(user);
            return "User deleted by email: " + email;
        } else {
            throw new UserNotFoundException("User with email '" + email + "' not found");
        }
    }


    public String deleteAll(Optional<Boolean> active) {
        List<User> usersToUpdate = active
                .map(userRepository::findByActive)
                .orElseGet(userRepository::findAll);

        usersToUpdate.forEach(user -> {
            user.setActive(false);
            user.setUpdatedDate(LocalDateTime.now());
        });

        userRepository.saveAll(usersToUpdate);
        return "Users Deleted";
    }

}
