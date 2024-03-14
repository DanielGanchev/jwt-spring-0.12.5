package org.dodomir.jwt0125.service.impl;

import lombok.RequiredArgsConstructor;
import org.dodomir.jwt0125.repository.UserRepository;
import org.dodomir.jwt0125.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;


  @Override
  public UserDetailsService userDetailsService() {
    return new UserDetailsService() {
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("No user found with email: " + username));
      }
    };
    }


}
