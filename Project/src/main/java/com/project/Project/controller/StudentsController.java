package com.project.Project.controller;

import com.project.Project.model.Students;
import com.project.Project.repository.StudentsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentsRepository repo;

    public StudentsController(StudentsRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Students> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Students create(@RequestBody Students student) {
        return repo.save(student);
    }
}
