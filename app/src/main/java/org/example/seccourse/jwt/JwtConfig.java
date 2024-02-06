package org.example.seccourse.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.LocalDate;

@ConfigurationProperties(prefix = "application.jwt")
@Configuration
@Data
@NoArgsConstructor
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationInDays;

    public SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }

    public Date getTokenExpirationDate() {
        return Date.valueOf(LocalDate.now().plusDays(tokenExpirationInDays));
    }
}
