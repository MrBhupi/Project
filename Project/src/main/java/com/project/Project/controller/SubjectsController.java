package com.project.Project.controller;

import com.project.Project.model.Subjects;
import com.project.Project.repository.SubjectsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectsController {

    private final SubjectsRepository repo;

    public SubjectsController(SubjectsRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Subjects> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Subjects create(@RequestBody Subjects subject) {
        return repo.save(subject);
    }
}
