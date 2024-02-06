package org.example.seccourse.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtTokenVerifier extends OncePerRequestFilter {
    private static final String SECRET_KEY = System.getenv("jwt_key");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);

            return;
        }

        final String token = authorizationHeader.substring(7);

        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .parseClaimsJws(token);
    }
}
