package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.config.JwtService;
import com.WheelHub.WheelHub.constant.enums.Role;
import com.WheelHub.WheelHub.dto.loginDtos.LoginDto;
import com.WheelHub.WheelHub.dto.signupDtos.SignUpDto;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.exception.DuplicateResourceException;
import com.WheelHub.WheelHub.repository.UserRepository;
import com.WheelHub.WheelHub.util.JwtResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImp {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Transactional
    public JwtResponse register(SignUpDto signUpDto) {

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new DuplicateResourceException("Email is already in use.");
        }

        // Check for duplicate username
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new DuplicateResourceException("Username is already in use.");
        }


        var user = User.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .role(signUpDto.getRole() != null ? Role.valueOf(signUpDto.getRole()) : Role.BUYER)
                .build();


        userRepository.save(user);
        var token = jwtService.generateToken(user);

        return JwtResponse.builder()
                .token(token)
                .build();
    }


    public JwtResponse login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        var user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow();
        var token = jwtService.generateToken(user);
        return JwtResponse.builder()
                .token(token)
                .build();
    }
}
