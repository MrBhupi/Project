package com.project.Project.controller;

<<<<<<< HEAD
import com.project.Project.dto.ProfileDto;
=======
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
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
<<<<<<< HEAD
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
=======
    public Users getProfile(Authentication authentication) {

        // username comes from JWT (SecurityContext)
        String username = authentication.getName();

        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
>>>>>>> 845ec6f833dea6f666d22aa3544cd98fa92d0d3c
    }
}
