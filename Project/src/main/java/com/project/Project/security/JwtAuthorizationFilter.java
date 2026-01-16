package com.project.Project.security;

import com.project.Project.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
<<<<<<< HEAD
import java.util.List;
=======
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService userDetailsService;

<<<<<<< HEAD
    // ===== PUBLIC URLS (NO JWT REQUIRED) =====
    private static final List<String> PUBLIC_URLS = List.of(
            "/login",
            "/users/create",

            // Forgot Password
            "/auth/forgot-password",
            "/auth/verify-otp",
            "/auth/reset-password"
    );

    public JwtAuthorizationFilter(JwtUtils jwtUtils,
                                  CustomUserDetailsService userDetailsService) {
=======
    public JwtAuthorizationFilter(JwtUtils jwtUtils, CustomUserDetailsService userDetailsService) {
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
<<<<<<< HEAD
                                    FilterChain chain)
            throws IOException, ServletException {

        String path = request.getRequestURI();

        // ===== SKIP JWT FOR PUBLIC URLS =====
        for (String url : PUBLIC_URLS) {
            if (path.startsWith(url)) {
                chain.doFilter(request, response);
                return;
            }
        }

        // ===== CHECK JWT =====
=======
                                    FilterChain chain) throws IOException, ServletException {

>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtUtils.validateJwtToken(token)) {
                String username = jwtUtils.getUsernameFromJwt(token);
<<<<<<< HEAD

                var userDetails =
                        userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
=======
                var userDetails = userDetailsService.loadUserByUsername(username);

                var auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
            }
        }

        chain.doFilter(request, response);
    }
}
