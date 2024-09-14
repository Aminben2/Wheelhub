package com.WheelHub.WheelHub.dto.userDtos;

import com.WheelHub.WheelHub.constant.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserResponseDto {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String phoneNumber;

    private String profilePicture;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Role role;
}
