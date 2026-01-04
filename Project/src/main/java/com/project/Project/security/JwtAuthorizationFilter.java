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
import java.util.List;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService userDetailsService;

    // List of public endpoints (no JWT required)
    private static final List<String> PUBLIC_URLS = List.of(
            "/login",
            "/users",
            "/users/",
            "/users/", // covers /users/**
            "/teachers",
            "/teachers/",
            "/terms",
            "/terms/",
            "/teacher-subjects",
            "/teacher-subjects/",
            "/subjects",
            "/subjects/",
            "/students",
            "/students/",
            "/programs",
            "/programs/",
            "/program-subjects",
            "/program-subjects/",
            "/marks",
            "/marks/",
            "/faculties",
            "/faculties/"
    );

    public JwtAuthorizationFilter(JwtUtils jwtUtils, CustomUserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String path = request.getRequestURI();

        // Skip JWT validation for public endpoints
        for (String url : PUBLIC_URLS) {
            if (path.startsWith(url)) {
                chain.doFilter(request, response);
                return;
            }
        }

        // JWT validation for secured endpoints
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtUtils.validateJwtToken(token)) {
                String username = jwtUtils.getUsernameFromJwt(token);
                var userDetails = userDetailsService.loadUserByUsername(username);

                var auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }
}
