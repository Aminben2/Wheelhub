package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.loginDtos.LoginDto;
import com.WheelHub.WheelHub.dto.signupDtos.SignUpDto;
import com.WheelHub.WheelHub.service.impl.AuthServiceImp;
import com.WheelHub.WheelHub.util.JwtResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImp authServiceImp;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(
            @Valid @RequestBody LoginDto loginDto
    ){
        return ResponseEntity.ok(authServiceImp.login(loginDto));
    }


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody SignUpDto signUpDto,
            BindingResult bindingResult
    )
    {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(authServiceImp.register(signUpDto));
    }
}
