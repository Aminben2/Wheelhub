package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.userDtos.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDTO);

    UserDto getUserById(Long id);

    UserDto getUserByUsername(String username);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserDto userDTO);

    void deleteUser(Long id);
}
