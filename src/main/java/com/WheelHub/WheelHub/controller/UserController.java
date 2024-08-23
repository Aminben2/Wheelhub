package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.UserDTO;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getOneUser(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user)
    {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO user)
    {
        return userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id)
    {
        return userService.deleteUser(id);
    }
}
