package org.dodomir.jwt0125.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.dodomir.jwt0125.service.JwtService;
import org.dodomir.jwt0125.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final UserService userService;

  private final JwtService jwtService;

  public JwtAuthFilter(UserService userService, JwtService jwtService) {
    this.userService = userService;
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
      final  String jwt;
      final String userEmail;
      final String authorizationHeader = request.getHeader("Authorization");

      if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
        filterChain.doFilter(request, response);
        return;
      }
      jwt = authorizationHeader.substring(7);
      userEmail = jwtService.getUsername(jwt);

      if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
         UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
          if(jwtService.validateToken(jwt, userDetails)){
            SecurityContext security = SecurityContextHolder.createEmptyContext();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            security.setAuthentication(token);
            SecurityContextHolder.setContext(security);


        }
      }
      filterChain.doFilter(request, response);

  }
}
