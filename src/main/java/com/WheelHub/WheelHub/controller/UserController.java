package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.constant.enums.Role;
import com.WheelHub.WheelHub.dto.userDtos.UserDto;
import com.WheelHub.WheelHub.dto.userDtos.UserResponseDto;
import com.WheelHub.WheelHub.dto.userDtos.UserResponseDtoForGetByUsername;
import com.WheelHub.WheelHub.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/all/{role}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<List<UserResponseDto>> getUsers(@PathVariable String role) {
        List<UserResponseDto> users = userService.getUsers(Role.valueOf(role.toUpperCase()));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<UserResponseDto> getOneUserById(@PathVariable @Min(1) Long id) {
        UserResponseDto user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/username/{username}")
    @PreAuthorize("hasAuthority('users:read-by-username')")
    public ResponseEntity<UserResponseDtoForGetByUsername> getOneUserByUsername(@PathVariable String username) {
        UserResponseDtoForGetByUsername user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('users:create')")
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserDto user) {
        UserResponseDto createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('users:update')")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable @Min(1) Long id,@Valid @RequestBody UserDto user) {
        UserResponseDto updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:delete')")
    public ResponseEntity<Void> deleteUser(@PathVariable @Min(1) Long id) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
    }
}
