package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.userDtos.UserDto;
import com.WheelHub.WheelHub.dto.userDtos.UserResponseDto;
import com.WheelHub.WheelHub.dto.userDtos.UserResponseDtoForGetByUsername;
import com.WheelHub.WheelHub.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDto entityToDTO(User user) {
        return UserDto.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public UserResponseDto entityToResponseDTO(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .profilePicture(user.getProfilePicture())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public UserResponseDtoForGetByUsername entityToResponseDtoForGetByUsername(User user) {
        return UserResponseDtoForGetByUsername.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public User dtoToEntity(UserDto userDTO) {
        return  User.builder()
                .name(userDTO.getName())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .profilePicture(userDTO.getProfilePicture())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .build();
    }
}
