package com.WheelHub.WheelHub.dto.roleDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RoleDto {

    @NotBlank(message = "Role name cannot be blank")
    @Size(max = 50, message = "Role name cannot exceed 50 characters")
    @Pattern(regexp = "^(BUYER|SELLER|ADMIN)$", message = "Role name must be one of the following: BUYER, SELLER, ADMIN")
    private String name;
}
