package com.project.Project.controller;

import com.project.Project.model.Marks;
import com.project.Project.repository.MarksRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks")
public class MarksController {

    private final MarksRepository repo;

    public MarksController(MarksRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Marks> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Marks create(@RequestBody Marks marks) {
        return repo.save(marks);
    }
}
