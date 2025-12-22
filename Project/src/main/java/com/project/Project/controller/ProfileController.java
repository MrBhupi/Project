package com.project.Project.controller;

import com.project.Project.model.Users;
import com.project.Project.repository.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final UsersRepository usersRepository;

    public ProfileController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping
    public Users getProfile(Authentication authentication) {

        // username comes from JWT (SecurityContext)
        String username = authentication.getName();

        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
