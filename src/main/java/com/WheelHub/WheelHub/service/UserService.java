package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    ResponseEntity<UserDTO> createUser(UserDTO userDTO);
    ResponseEntity<Void> deleteUser(Long id);
    ResponseEntity<UserDTO> updateUser(Long id, UserDTO userDTO);
    ResponseEntity<List<UserDTO>> getAllUsers();
    ResponseEntity<UserDTO> getUserById(Long id);
}
