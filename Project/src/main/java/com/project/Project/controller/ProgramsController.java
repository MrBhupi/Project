package com.project.Project.controller;

import com.project.Project.model.Programs;
import com.project.Project.repository.ProgramsRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramsController {

    private final ProgramsRepository repo;

    public ProgramsController(ProgramsRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('admin','teacher')")
    public List<Programs> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Programs create(@RequestBody Programs program) {
        return repo.save(program);
    }
}
