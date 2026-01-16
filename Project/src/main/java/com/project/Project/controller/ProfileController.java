package com.project.Project.controller;

import com.project.Project.dto.ProfileDto;
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
    public ProfileDto getProfile(Authentication authentication) {

        String username = authentication.getName();

        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Return ID, name, username, and role
        return new ProfileDto(
                user.getId(),
                user.getUsername(), // or actual full name if you have a separate field
                user.getUsername(),
                user.getRole().name()
        );
    }
}
