package com.project.Project.controller;

import com.project.Project.model.Users;
import com.project.Project.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping
    public Users create(@RequestBody Users user) {
        return usersRepository.save(user);
    }

    @GetMapping
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users getById(@PathVariable Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PutMapping("/{id}")
    public Users update(@PathVariable Long id, @RequestBody Users data) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        user.setRole(data.getRole());
        user.setPass_change(data.getPass_change());

        return usersRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        usersRepository.deleteById(id);
        return "User deleted";
    }
}
