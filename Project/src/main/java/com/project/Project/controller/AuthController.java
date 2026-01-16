package com.project.Project.controller;

import com.project.Project.model.Users;
import com.project.Project.repository.UsersRepository;
import com.project.Project.service.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsersRepository usersRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UsersRepository usersRepository,
            EmailService emailService,
            PasswordEncoder passwordEncoder
    ) {
        this.usersRepository = usersRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    // ===============================
    // SEND OTP
    // ===============================
    @PostMapping("/forgot-password")
    public Map<String, String> forgotPassword(@RequestBody Map<String, String> req) {

        String email = req.get("email");

        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not registered"));

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        user.setResetOtp(otp);
        user.setResetOtpExpiry(LocalDateTime.now().plusMinutes(10));
        user.setOtpVerified(false);

        usersRepository.save(user);
        emailService.sendOtp(email, otp);

        return Map.of("message", "OTP sent to email");
    }

    // ===============================
    // VERIFY OTP
    // ===============================
    @PostMapping("/verify-otp")
    public Map<String, String> verifyOtp(@RequestBody Map<String, String> req) {

        Users user = usersRepository.findByEmail(req.get("email"))
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (user.getResetOtp() == null ||
                !user.getResetOtp().equals(req.get("otp")) ||
                user.getResetOtpExpiry().isBefore(LocalDateTime.now())) {

            throw new RuntimeException("Invalid or expired OTP");
        }

        user.setOtpVerified(true);
        usersRepository.save(user);

        return Map.of("message", "OTP verified");
    }

    // ===============================
    // RESET PASSWORD
    // ===============================
    @PostMapping("/reset-password")
    public Map<String, String> resetPassword(@RequestBody Map<String, String> req) {

        Users user = usersRepository.findByEmail(req.get("email"))
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!Boolean.TRUE.equals(user.getOtpVerified())) {
            throw new RuntimeException("OTP not verified");
        }

        user.setPasswordHash(passwordEncoder.encode(req.get("password")));
        user.setResetOtp(null);
        user.setResetOtpExpiry(null);
        user.setOtpVerified(false);

        usersRepository.save(user);

        return Map.of("message", "Password reset successful");
    }
}
