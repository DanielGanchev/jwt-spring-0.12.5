package org.dodomir.jwt0125.service.impl;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.dodomir.jwt0125.entities.dto.JwtAuthResponse;
import org.dodomir.jwt0125.entities.dto.RefreshTokenRequest;
import org.dodomir.jwt0125.entities.dto.SignInRequest;
import org.dodomir.jwt0125.entities.dto.SignUpRequest;
import org.dodomir.jwt0125.entities.models.Role;
import org.dodomir.jwt0125.entities.models.User;
import org.dodomir.jwt0125.repository.UserRepository;
import org.dodomir.jwt0125.service.AuthService;
import org.dodomir.jwt0125.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private  final PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;


  public User register(SignUpRequest signUpRequest) {
    User user = new User();
    user.setEmail(signUpRequest.email());
    user.setPassword(passwordEncoder.encode(signUpRequest.password()));
    user.setRole(Role.USER);
    return userRepository.save(user);
  }


  public JwtAuthResponse login(SignInRequest signInRequest) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.email(), signInRequest.password()));
     var user = userRepository.findByEmail(signInRequest.email()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
     var jwt = jwtService.generateJwtToken(user);
     var refreshToken = jwtService.generateRefreshJwtToken(new HashMap<>(), user);

      return new JwtAuthResponse(jwt, refreshToken);

  }

  public JwtAuthResponse refreshToken(RefreshTokenRequest refreshToken) {
    String email = jwtService.getUsername(refreshToken.getRefreshToken());
    User user = userRepository.findByEmail(email).orElseThrow();
    if (jwtService.validateToken(refreshToken.getRefreshToken(), user)) {
      var jwt = jwtService.generateJwtToken(user);
      var newRefreshToken = jwtService.generateRefreshJwtToken(new HashMap<>(), user);
      return new JwtAuthResponse(jwt, newRefreshToken);
    } else {
      throw new IllegalArgumentException("Invalid refresh token");
    }
  }
}
