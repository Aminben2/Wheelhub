package com.WheelHub.WheelHub.dto.loginDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "Username is mandatory")
    @Size(max = 255, message = "Username or email must not exceed 255 characters")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
    private String password;
}