package com.project.Project.config;

import com.project.Project.security.JwtAuthenticationFilter;
import com.project.Project.security.JwtAuthorizationFilter;
import com.project.Project.security.JwtUtils;
import com.project.Project.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
<<<<<<< HEAD
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
=======
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
<<<<<<< HEAD
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableMethodSecurity
=======

>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

<<<<<<< HEAD
    // ===== Password Encoder =====
=======
    // Password encoder bean
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

<<<<<<< HEAD
    // ===== Authentication Manager =====
=======
    // AuthenticationManager bean
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

<<<<<<< HEAD
    // ===== Security Filter Chain =====
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthenticationManager authManager) throws Exception {

        JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(authManager, jwtUtils);
        jwtAuthFilter.setFilterProcessesUrl("/login");

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // ===== PUBLIC ENDPOINTS =====
                        .requestMatchers(
                                "/login",
                                "/users/create",                 // public for testing
                                "/auth/forgot-password",
                                "/auth/verify-otp",
                                "/auth/reset-password"
                        ).permitAll()

                        // ===== ADMIN ONLY =====
                        .requestMatchers("/users/register").hasRole("admin")  // admin only

                        // ===== ADMIN OR TEACHER =====
                        .requestMatchers(
                                "/users/**",
                                "/teachers/**",
                                "/faculties/**",
                                "/subjects/**",
                                "/terms/**"
                        ).hasAnyRole("admin", "teacher")

                        // ===== STUDENT / ADMIN / TEACHER =====
                        .requestMatchers("/marks/**").hasAnyRole("student", "admin", "teacher")

                        // ===== ANY OTHER REQUEST =====
                        .anyRequest().authenticated()
                )
                .addFilter(jwtAuthFilter)
                .addFilterBefore(
                        new JwtAuthorizationFilter(jwtUtils, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    // ===== CORS CONFIG =====
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
=======
    // Security filter chain
    @Bean
    public SecurityFilterChain filterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http,
                                           AuthenticationManager authManager) throws Exception {

        // JWT Authentication filter
        JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(authManager, jwtUtils);
        jwtAuthFilter.setFilterProcessesUrl("/login"); // login endpoint

        // Configure HttpSecurity
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/users/create").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilter(jwtAuthFilter) // login filter
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtils, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
}
