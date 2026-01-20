package com.project.Project.config;

import com.project.Project.security.JwtAuthenticationFilter;
import com.project.Project.security.JwtAuthorizationFilter;
import com.project.Project.security.JwtUtils;
import com.project.Project.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableMethodSecurity
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
        jwtAuthFilter.setFilterProcessesUrl("/login");

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth

                        // ========================
                        // PUBLIC
                        // ========================
                        .requestMatchers(
                                "/login",
                                "/users/create",
                                "/auth/forgot-password",
                                "/auth/verify-otp",
                                "/auth/reset-password"
                        ).permitAll()

                        // ========================
                        // ADMIN ONLY (WRITE / MANAGEMENT)
                        // ========================
                        .requestMatchers(
                                "/users/**",
                                "/teachers/**",
                                "/faculties/**",
                                "/student-results/**"
                        ).hasRole("admin")

                        // ========================
                        // READ-ONLY DATA (ADMIN + TEACHER)
                        // ========================
                        .requestMatchers(
                                "/students/**",
                                "/subjects/**",
                                "/programs/**",
                                "/terms/**"
                        ).hasAnyRole("admin", "teacher")

                        // ========================
                        // MARKS (ADMIN + TEACHER)
                        // ========================
                        .requestMatchers("/users/**").hasRole("admin")

                        .requestMatchers("/marks/**")
                        .hasAnyRole("admin", "teacher")

                        // ========================
                        // ANY AUTHENTICATED USER
                        // ========================
                        .anyRequest().authenticated()
                )
                .addFilter(jwtAuthFilter)
                .addFilterBefore(
                        new JwtAuthorizationFilter(jwtUtils, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }


    // ========================
    // CORS CONFIGURATION
    // ========================
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
