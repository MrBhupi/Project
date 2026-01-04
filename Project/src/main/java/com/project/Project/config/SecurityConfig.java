package com.project.Project.config;

import com.project.Project.security.JwtAuthenticationFilter;
import com.project.Project.security.JwtAuthorizationFilter;
import com.project.Project.security.JwtUtils;
import com.project.Project.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public SecurityConfig(CustomUserDetailsService userDetailsService,
                          JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthenticationManager authManager) throws Exception {

        JwtAuthenticationFilter jwtAuthFilter =
                new JwtAuthenticationFilter(authManager, jwtUtils);

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        // Public endpoint
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/profile").authenticated()

                        // ADMIN → full access
                        .requestMatchers("/**").hasRole("admin")

                        // STUDENT permissions
                        .requestMatchers(
                                "/faculties/**",
                                "/programs/**",
                                "/marks/**",
                                "/subjects/**",
                                "/terms/**"
                        ).hasAnyRole("student", "admin")

                        // TEACHER permissions
                        .requestMatchers(
                                "/subjects/**",
                                "/programs/**",
                                "/faculties/**",
                                "/terms/**",
                                "/marks/**",
                                "/teachers/**"
                        ).hasAnyRole("teacher", "admin")

                        // Everything else
                        .anyRequest().authenticated()
                )
                .addFilter(jwtAuthFilter)
                .addFilterBefore(
                        new JwtAuthorizationFilter(jwtUtils, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
