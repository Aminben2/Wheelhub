package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.constant.enums.Role;
import com.WheelHub.WheelHub.dto.userDtos.UserDto;
import com.WheelHub.WheelHub.dto.userDtos.UserResponseDto;
import com.WheelHub.WheelHub.dto.userDtos.UserResponseDtoForGetByUsername;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.exception.DuplicateResourceException;
import com.WheelHub.WheelHub.mapper.UserMapper;
import com.WheelHub.WheelHub.repository.UserRepository;
import com.WheelHub.WheelHub.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  @Transactional
  public UserResponseDto createUser(UserDto userDTO) {
    if (userRepository.existsByEmail(userDTO.getEmail())) {
      throw new DuplicateResourceException("Email is already in use.");
    }
    if (userRepository.existsByUsername(userDTO.getUsername())) {
      throw new DuplicateResourceException("Username is already in use.");
    }

    User user = userMapper.dtoToEntity(userDTO);
    user = userRepository.save(user);
    return userMapper.entityToResponseDTO(user);
  }

  @Override
  public UserResponseDto getUserById(Long id) {
    return userRepository
        .findById(id)
        .map(userMapper::entityToResponseDTO)
        .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + id));
  }

  @Override
  public UserResponseDtoForGetByUsername getUserByUsername(String username) {
    return userRepository
        .findByUsername(username)
        .map(userMapper::entityToResponseDtoForGetByUsername)
        .orElseThrow(() -> new EntityNotFoundException("User not found for username:" + username));
  }

  @Override
  public List<UserResponseDto> getUsers(Role role) {
    return userRepository.findByRole(role).stream()
        .map(userMapper::entityToResponseDTO)
        .collect(Collectors.toList());
  }

  @Override
  public List<UserResponseDto> getAllUsers() {
    return userRepository.findAll().stream()
        .map(userMapper::entityToResponseDTO)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public UserResponseDto updateUser(Long id, UserDto userDTO) {
    User existingUser =
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + id));

    existingUser.setName(userDTO.getName());
    existingUser.setUsername(userDTO.getUsername());
    existingUser.setEmail(userDTO.getEmail());

    User updatedUser = userRepository.save(existingUser);
    return userMapper.entityToResponseDTO(updatedUser);
  }

  @Override
  public void deleteUser(Long id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + id));
    userRepository.delete(user);
  }
}
