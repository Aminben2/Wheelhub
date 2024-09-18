package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.constant.enums.Role;
import com.WheelHub.WheelHub.dto.userDtos.UserDto;
import com.WheelHub.WheelHub.dto.userDtos.UserResponseDto;
import com.WheelHub.WheelHub.dto.userDtos.UserResponseDtoForGetByUsername;
import java.util.List;

public interface UserService {

  UserResponseDto createUser(UserDto userDTO);

  UserResponseDto getUserById(Long id);

  UserResponseDtoForGetByUsername getUserByUsername(String username);

  List<UserResponseDto> getUsers(Role role);

  UserResponseDto updateUser(Long id, UserDto userDTO);

  void deleteUser(Long id);
}
