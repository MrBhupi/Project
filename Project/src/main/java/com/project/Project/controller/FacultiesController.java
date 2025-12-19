package com.project.Project.controller;

import com.project.Project.model.Faculties;
import com.project.Project.repository.FacultiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultiesController {

    @Autowired
    private FacultiesRepository facultiesRepository;

    @PostMapping
    public Faculties create(@RequestBody Faculties faculty) {
        return facultiesRepository.save(faculty);
    }

    @GetMapping
    public List<Faculties> getAll() {
        return facultiesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Faculties getById(@PathVariable Integer id) {
        return facultiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
    }

    @PutMapping("/{id}")
    public Faculties update(@PathVariable Integer id, @RequestBody Faculties data) {
        Faculties faculty = facultiesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        faculty.setName(data.getName());
        faculty.setDescription(data.getDescription());
        faculty.setCreatedAt(data.getCreatedAt());

        return facultiesRepository.save(faculty);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        facultiesRepository.deleteById(id);
        return "Faculty deleted";
    }
}
