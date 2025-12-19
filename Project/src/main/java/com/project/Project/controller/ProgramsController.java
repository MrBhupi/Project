package com.project.Project.controller;

import com.project.Project.model.Programs;
import com.project.Project.repository.ProgramsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramsController {

    @Autowired
    private ProgramsRepository programsRepository;

    @PostMapping
    public Programs create(@RequestBody Programs program) {
        return programsRepository.save(program);
    }

    @GetMapping
    public List<Programs> getAll() {
        return programsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Programs getById(@PathVariable Integer id) {
        return programsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));
    }

    @PutMapping("/{id}")
    public Programs update(@PathVariable Integer id, @RequestBody Programs data) {
        Programs program = programsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        program.setFaculty(data.getFaculty());
        program.setName(data.getName());
        program.setDescription(data.getDescription());
        program.setCreatedAt(data.getCreatedAt());

        return programsRepository.save(program);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        programsRepository.deleteById(id);
        return "Program deleted";
    }
}
