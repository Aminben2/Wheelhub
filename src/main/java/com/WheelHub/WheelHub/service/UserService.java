package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.userDtos.UserDto;
import com.WheelHub.WheelHub.dto.userDtos.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserDto userDTO);

    UserResponseDto getUserById(Long id);

    UserResponseDto getUserByUsername(String username);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(Long id, UserDto userDTO);

    void deleteUser(Long id);
}
