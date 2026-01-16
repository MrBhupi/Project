package com.project.Project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
<<<<<<< HEAD
=======
import java.util.HashMap;
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
<<<<<<< HEAD
        setFilterProcessesUrl("/login");
=======
        setFilterProcessesUrl("/login"); // login endpoint
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
<<<<<<< HEAD
                                                HttpServletResponse response)
            throws AuthenticationException {
        try {
            LoginRequest authRequest =
                    new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    );

            return authenticationManager.authenticate(authToken);

        } catch (IOException e) {
            throw new RuntimeException("Invalid login request", e);
=======
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            // Read username and password from request JSON
            var authRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());

            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
<<<<<<< HEAD
                                            Authentication authResult)
            throws IOException {

        boolean isStudent = authResult.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_student"));

        if (isStudent) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("""
            {
              "message": "Students must log in via the mobile application."
            }
        """);
            return;
        }
=======
                                            Authentication authResult) throws IOException, ServletException {
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c

        String token = jwtUtils.generateJwtToken(authResult);

        response.setContentType("application/json");
<<<<<<< HEAD
        response.getWriter().write("""
        {
          "token": "%s"
        }
    """.formatted(token));
    }


    // DTO
=======
        response.setCharacterEncoding("UTF-8");

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);

        new ObjectMapper().writeValue(response.getWriter(), tokenMap);
    }

    // DTO for login request
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
    private static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}
