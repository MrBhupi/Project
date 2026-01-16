package com.project.Project.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSecret = "YourSuperSecretKeyForJWTGeneration12345"; // must be long
    private final long jwtExpirationMs = 86400000; // 1 day

    private final Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

    // Generate JWT token from Authentication object
    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
<<<<<<< HEAD
              //  .claim("role", userPrincipal.getAuthorities().iterator().next().getAuthority())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
=======
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

<<<<<<< HEAD

=======
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
    // Extract username from JWT token
    public String getUsernameFromJwt(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

<<<<<<< HEAD

=======
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
    // Validate JWT token
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
