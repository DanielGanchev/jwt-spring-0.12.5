package org.dodomir.jwt0125.controller;

import lombok.RequiredArgsConstructor;
import org.dodomir.jwt0125.entities.dto.JwtAuthResponse;
import org.dodomir.jwt0125.entities.dto.RefreshTokenRequest;
import org.dodomir.jwt0125.entities.dto.SignInRequest;
import org.dodomir.jwt0125.entities.dto.SignUpRequest;
import org.dodomir.jwt0125.entities.models.User;
import org.dodomir.jwt0125.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody SignUpRequest signUpRequest) {
    return ResponseEntity.ok(authService.register(signUpRequest));
  }

  @PostMapping("/login")
  public ResponseEntity<JwtAuthResponse>  login(@RequestBody SignInRequest signInRequest) {
    return ResponseEntity.ok(authService.login(signInRequest));
  }

  @PostMapping("/refresh")
  public ResponseEntity<JwtAuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshToken) {
    return ResponseEntity.ok(authService.refreshToken(refreshToken));
  }

}
