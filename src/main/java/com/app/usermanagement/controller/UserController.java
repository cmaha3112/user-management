package com.app.usermanagement.controller;

import com.app.usermanagement.Configuration;
import com.app.usermanagement.model.User;
import com.app.usermanagement.requestdto.UserRequestDto;
import com.app.usermanagement.responsedto.UserResponseDto;
import com.app.usermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

        @Autowired
        private UserService userService;

        @Autowired
        private String sample;

        @PostMapping
        public String createUser(@Valid @RequestBody UserRequestDto user) {
            return userService.createUser(user);
        }

        @PostMapping("/bulk")
        public List<String> createUsers(@RequestBody List<@Valid UserRequestDto> users) {
            return userService.createUsers(users);
        }

        @GetMapping
        public List<UserResponseDto> getAll(@RequestParam(required = false) Boolean active) {
            return userService.getAllUsers(Optional.ofNullable(active));
        }

        @GetMapping("/{id}")
        public Optional<UserResponseDto> getById(@PathVariable String id) {
            return userService.getUserById(id);
        }

        @GetMapping(params = "name")
        public Optional<UserResponseDto> getByName(@RequestParam String name) {
            return userService.getUserByName(name);
        }

        @GetMapping(params = "email")
        public Optional<UserResponseDto> getByEmail(@RequestParam String email) {
            return userService.getUserByEmail(email);
        }

        @PutMapping("/{id}")
        public String update(@PathVariable String id, @RequestBody User user) {
            return userService.updateUser(id, user);
        }

        @DeleteMapping("/{id}")
        public String deleteById(@PathVariable String id) {
            return userService.deleteUserById(id);
        }

        @DeleteMapping(params = "name")
        public String deleteByName(@RequestParam String name) {
            return userService.deleteByName(name);
        }

        @DeleteMapping(params = "email")
        public String deleteByEmail(@RequestParam String email) {
            return userService.deleteByEmail(email);
        }

        @DeleteMapping
        public String deleteAll(@RequestParam(required = false) Boolean active) {
            return userService.deleteAll(Optional.ofNullable(active));
        }

        @GetMapping("/bean")
        public String test(){
            return sample;
        }



}
