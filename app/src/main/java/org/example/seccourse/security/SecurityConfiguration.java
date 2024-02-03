package org.example.seccourse.security;

import org.example.seccourse.repository.UserRepository;

import static org.example.seccourse.model.enums.Permissions.*;
import static org.example.seccourse.model.enums.Role.*;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(request -> request.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/api/**").hasRole(STUDENT.name())
                        .requestMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.name())
                        .requestMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.name())
                        .requestMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.name())
                        .requestMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                        .anyRequest().authenticated())
                .csrf(CsrfConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        UserDetails student = User.builder()
                .username("Kalin")
                .password(passwordEncoder().encode("123"))
                .roles(STUDENT.name())
                .build();

        UserDetails admin = User.builder()
                .username("Admin")
                .password(passwordEncoder().encode("123"))
                .roles(ADMIN.name())
                .build();

        UserDetails adminT = User.builder()
                .username("AdminT")
                .password(passwordEncoder().encode("123"))
                .roles(ADMINTRAINEE.name())
                .build();

        return new InMemoryUserDetailsManager(student, admin, adminT);

//        return new AuthenticationUserService(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
