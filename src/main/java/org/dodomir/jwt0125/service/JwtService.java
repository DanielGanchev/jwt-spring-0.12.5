package org.dodomir.jwt0125.service;

import java.util.Map;
import org.dodomir.jwt0125.entities.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

 String generateJwtToken(UserDetails userDetails);
String getUsername(String token);
boolean isTokenExpired(String token);

 boolean validateToken(String token, UserDetails userDetails);


 String generateRefreshJwtToken(Map<String, Object> claims, UserDetails userDetails);
}
