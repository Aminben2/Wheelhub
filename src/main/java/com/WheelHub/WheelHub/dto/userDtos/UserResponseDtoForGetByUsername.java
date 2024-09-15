package com.WheelHub.WheelHub.dto.userDtos;

import com.WheelHub.WheelHub.constant.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserResponseDtoForGetByUsername {
    private Long id;
    private String username;
    private Role role;
}
