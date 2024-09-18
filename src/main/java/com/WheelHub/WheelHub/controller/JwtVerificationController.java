package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.config.JwtService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JwtVerificationController {

  private final JwtService jwtService;

  private final UserDetailsService userDetailsService;

  @GetMapping("/verify-token")
  public ResponseEntity<Map<String, String>> verifyToken(
      @RequestHeader("Authorization") String authorizationHeader) {
    Map<String, String> response = new HashMap<>();
    String token = authorizationHeader.substring(7);

    String username = jwtService.extractUsername(token);

    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    if (userDetails == null) {
      response.put("error", "User details not found");
      return ResponseEntity.status(404).body(response);
    }

    if (jwtService.isTokenExpired(token)) {
      response.put("error", "Token is expired");
      return ResponseEntity.status(401).body(response);
    }

    if (!jwtService.isTokenValid(token, userDetails)) {
      response.put("error", "Invalid token");
      return ResponseEntity.status(401).body(response);
    }

    response.put("message", "Token is valid");
    return ResponseEntity.ok(response);
  }
}
