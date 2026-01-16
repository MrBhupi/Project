package com.project.Project.controller;

import com.project.Project.model.Subjects;
import com.project.Project.repository.SubjectsRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectsController {

    private final SubjectsRepository repo;

    public SubjectsController(SubjectsRepository repo) {
        this.repo = repo;
    }

    // ADMIN + TEACHER can view
    @GetMapping
    @PreAuthorize("hasAnyRole('admin','teacher')")
    public List<Subjects> getAll() {
        return repo.findAll();
    }

    // ADMIN ONLY can create
    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Subjects create(@RequestBody Subjects subject) {
        return repo.save(subject);
    }
}
