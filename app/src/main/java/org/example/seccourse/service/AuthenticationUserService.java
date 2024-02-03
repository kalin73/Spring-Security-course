package org.example.seccourse.service;

import lombok.RequiredArgsConstructor;
import org.example.seccourse.model.entity.UserEntity;
import org.example.seccourse.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class AuthenticationUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " does not exist!"));

        return user;
    }
}
