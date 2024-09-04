package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.config.JwtService;
import com.WheelHub.WheelHub.dto.loginDtos.LoginDto;
import com.WheelHub.WheelHub.dto.signupDtos.SignUpDto;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.exception.DuplicateResourceException;
import com.WheelHub.WheelHub.repository.RoleRepository;
import com.WheelHub.WheelHub.repository.UserRepository;
import com.WheelHub.WheelHub.util.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImp {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public JwtResponse register(SignUpDto signUpDto) {

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new DuplicateResourceException("Email is already in use.");
        }

        // Check for duplicate username
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new DuplicateResourceException("Username is already in use.");
        }

        Set<String> roleNames = signUpDto.getRoles();
        if (roleNames == null || roleNames.isEmpty()) {
            roleNames = Set.of("USER");
        }

        var user = User.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .roles(roleNames.stream()
                        .map(roleName -> roleRepository.findByName(roleName)
                                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                        .collect(Collectors.toSet()))
                .build();

        userRepository.save(user);
        var token = jwtService.generateToken(user);

        return JwtResponse.builder()
                .id(user.getId())
                .token(token)
                .build();
    }


    public JwtResponse login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        var user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow();
        var token = jwtService.generateToken(user);
        return JwtResponse.builder()
                .id(user.getId())
                .token(token)
                .build();
    }
}
