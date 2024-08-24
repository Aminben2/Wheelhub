package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.UserDTO;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.mapper.*;
import com.WheelHub.WheelHub.repository.RoleRepo;
import com.WheelHub.WheelHub.repository.UserRepo;
import com.WheelHub.WheelHub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;


    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userRepo.findAll().stream()
                .map(UserMapper::entityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(Long id) {
        return userRepo.findById(id)
                .map(user -> ResponseEntity.ok(UserMapper.entityToDTO(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        User user = UserMapper.dtoToEntity(userDTO);

        User savedUser = userRepo.save(user);

        UserDTO savedUserDTO = UserMapper.entityToDTO(savedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @Transactional
    public ResponseEntity<UserDTO> updateUser(Long id, UserDTO userDTO) {
        if (!userRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userDTO.getUsername() != null) {
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getCreatedAt() != null) {
            existingUser.setCreatedAt(userDTO.getCreatedAt());
        }
        if (userDTO.getUpdatedAt() != null) {
            existingUser.setUpdatedAt(userDTO.getUpdatedAt());
        }

        User updatedUser = userRepo.save(existingUser);

        UserDTO updatedUserDTO = UserMapper.entityToDTO(updatedUser);

        return ResponseEntity.ok(updatedUserDTO);
    }
}
