package org.example.seccourse.security;

import lombok.RequiredArgsConstructor;
import org.example.seccourse.jwt.JwtConfig;
import org.example.seccourse.jwt.JwtTokenVerifier;
import org.example.seccourse.jwt.JwtUsernamePasswordAuthenticationFilter;
import org.example.seccourse.repository.UserRepository;
import org.example.seccourse.service.AuthenticationUserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.example.seccourse.model.enums.Role.STUDENT;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final UserRepository userRepository;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtConfig jwtConfig) throws Exception {
        httpSecurity.addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationProvider(), jwtConfig))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig), JwtUsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/login").permitAll()
                        .requestMatchers("/api/**").hasRole(STUDENT.name())
//                        .requestMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                        .requestMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                        .requestMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                        .requestMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                        .anyRequest().authenticated())
//                .formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/courses", true))
//                .rememberMe(r -> r.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                        .key("topsecretkey"))
//                .logout(logout -> logout.logoutUrl("/logout")
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .deleteCookies("JSESSIONID", "remember-me")
//                        .logoutSuccessUrl("/"))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(CsrfConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
//        UserDetails student = User.builder()
//                .username("Kalin")
//                .password(passwordEncoder().encode("123"))
//                //.roles(STUDENT.name())
//                .authorities(STUDENT.getGrantedAuthorities())
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("Admin")
//                .password(passwordEncoder().encode("123"))
//                //.roles(ADMIN.name())
//                .authorities(ADMIN.getGrantedAuthorities())
//                .build();
//
//        UserDetails adminT = User.builder()
//                .username("AdminT")
//                .password(passwordEncoder().encode("123"))
//                //.roles(ADMINTRAINEE.name())
//                .authorities(ADMINTRAINEE.getGrantedAuthorities())
//                .build();
//
//        //return new InMemoryUserDetailsManager(student, admin, adminT);

        return new AuthenticationUserService(userRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

}
