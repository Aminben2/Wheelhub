package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.LoginDto;
import com.WheelHub.WheelHub.dto.SignUpDto;
import com.WheelHub.WheelHub.entity.Role;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.repository.RoleRepo;
import com.WheelHub.WheelHub.repository.UserRepo;
import com.WheelHub.WheelHub.service.impl.CustomUserDetailsService;
import com.WheelHub.WheelHub.util.JwtResponse;
import com.WheelHub.WheelHub.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT Token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsernameOrEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        User user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ loginDto.getUsernameOrEmail()));

        return ResponseEntity.ok(new JwtResponse(jwt, user.getId()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        // Check if username exists in the DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Check if email exists in the DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Create user object
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        // Retrieve and set roles
        Set<Role> roles = new HashSet<>();
        for (String roleName : signUpDto.getRoles()) {
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }
        user.setRoles(roles);

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}
