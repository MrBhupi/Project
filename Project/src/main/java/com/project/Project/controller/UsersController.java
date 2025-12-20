package com.project.Project.controller;

import com.project.Project.model.Users;
import com.project.Project.repository.UsersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersRepository repo;

    public UsersController(UsersRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Users> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Users getById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PostMapping
    public Users create(@RequestBody Users user) {
        return repo.save(user);
    }

    @PutMapping("/{id}")
    public Users update(@PathVariable Long id, @RequestBody Users data) {
        Users user = repo.findById(id).orElseThrow();
        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setPasswordHash(data.getPasswordHash());
        user.setRole(data.getRole());
        user.setRequiresPasswordChange(data.getRequiresPasswordChange());
        return repo.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
