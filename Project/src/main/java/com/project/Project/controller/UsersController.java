package com.project.Project.controller;

import com.project.Project.model.Role;
import com.project.Project.model.Users;
import com.project.Project.repository.UsersRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersController(UsersRepository usersRepository,
                           PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // PUBLIC CREATE USER (TESTING)
    // =========================
    @PostMapping("/create")
    public Users createUser(@RequestBody UserRequest request) {
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole()); // keep lowercase
        user.setRequiresPasswordChange(false);
        user.setActive(true);
        return usersRepository.save(user);
    }

    // =========================
    // ADMIN ONLY REGISTER USER (PRODUCTION)
    // =========================
    @PostMapping("/register")
    @PreAuthorize("hasRole('admin')")
    public Users registerUser(@RequestBody UserRequest request) {
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole()); // lowercase role
        user.setRequiresPasswordChange(false);
        user.setActive(true);
        return usersRepository.save(user);
    }

    // =========================
    // GET USERS WITH ROLE FILTER
    // =========================
    @GetMapping
    @PreAuthorize("hasAnyRole('admin','teacher')")
    public List<Users> getUsers(
            @RequestParam(required = false) Role role,
            Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_admin"));

        boolean isTeacher = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_teacher"));

        if (isAdmin) {
            return role == null
                    ? usersRepository.findAll()
                    : usersRepository.findByRole(role);
        }

        if (isTeacher) {
            if (role == Role.admin) {
                throw new RuntimeException("Teachers cannot view admins");
            }

            if (role == null) {
                return usersRepository.findByRoleIn(
                        List.of(Role.teacher, Role.student)
                );
            }

            return usersRepository.findByRole(role);
        }

        throw new RuntimeException("Not authorized to view users");
    }

    // =========================
    // DTO
    // =========================
    public static class UserRequest {
        private String username;
        private String email;
        private String password;
        private Role role;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        public Role getRole() { return role; }
        public void setRole(Role role) { this.role = role; }
    }
}
