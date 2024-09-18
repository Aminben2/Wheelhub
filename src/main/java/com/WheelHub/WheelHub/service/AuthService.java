package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.loginDtos.LoginDto;
import com.WheelHub.WheelHub.dto.signupDtos.SignUpDto;
import com.WheelHub.WheelHub.util.JwtResponse;

public interface AuthService {

  JwtResponse register(SignUpDto signUpDto);

  JwtResponse login(LoginDto loginDto);
}
