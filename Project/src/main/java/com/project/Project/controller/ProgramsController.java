package com.project.Project.controller;

import com.project.Project.model.Programs;
import com.project.Project.repository.ProgramsRepository;
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
    public List<Programs> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Programs create(@RequestBody Programs program) {
        return repo.save(program);
    }
}
