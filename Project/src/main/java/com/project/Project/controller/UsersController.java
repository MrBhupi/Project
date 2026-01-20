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
//@PreAuthorize("hasRole('admin')")
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
        user.setRole(request.getRole());
        user.setRequiresPasswordChange(false);
        user.setActive(true);
        return usersRepository.save(user);
    }
    // =========================
    // REGISTER USER (ADMIN)
    // =========================
    @PostMapping("/register")
    public Users registerUser(@RequestBody UserRequest request) {
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setRequiresPasswordChange(false);
        user.setActive(true);
        return usersRepository.save(user);
    }

    // =========================
    // GET USERS (ADMIN)
    // =========================
    @GetMapping
    public List<Users> getUsers(@RequestParam(required = false) Role role) {
        return role == null
                ? usersRepository.findAll()
                : usersRepository.findByRole(role);
    }

    // =========================
    // DELETE USER (ADMIN)
    // =========================
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        usersRepository.deleteById(id);
        return "User deleted with id: " + id;
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
