package org.dodomir.jwt0125.service;

import org.dodomir.jwt0125.entities.dto.JwtAuthResponse;
import org.dodomir.jwt0125.entities.dto.RefreshTokenRequest;
import org.dodomir.jwt0125.entities.dto.SignInRequest;
import org.dodomir.jwt0125.entities.dto.SignUpRequest;
import org.dodomir.jwt0125.entities.models.User;

public interface AuthService {

  public User register(SignUpRequest signUpRequest);

  JwtAuthResponse login(SignInRequest signInRequest);

   JwtAuthResponse refreshToken(RefreshTokenRequest refreshToken);
}
