package com.project.Project.controller;

import com.project.Project.model.Users;
import com.project.Project.model.Role;
import com.project.Project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create a new user
    @PostMapping("/create")
    public Users createUser(@RequestBody UserRequest userRequest) {
        Users user = new Users();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        user.setRequiresPasswordChange(false);

        return usersRepository.save(user);
    }

    // List all users (excluding passwords)
    @GetMapping
    public Iterable<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Delete a user by id
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        usersRepository.deleteById(id);
        return "User deleted with id: " + id;
    }

    // DTO class for user creation request
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
