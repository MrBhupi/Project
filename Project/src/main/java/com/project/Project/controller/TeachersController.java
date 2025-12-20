package com.project.Project.controller;

import com.project.Project.model.Teachers;
import com.project.Project.repository.TeachersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeachersController {

    private final TeachersRepository repo;

    public TeachersController(TeachersRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Teachers> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Teachers create(@RequestBody Teachers teacher) {
        return repo.save(teacher);
    }
}
