package com.project.Project.controller;

import com.project.Project.model.Program_subjects;
import com.project.Project.repository.ProgramSubjectsRepository;
import com.project.Project.repository.ProgramsRepository;
import com.project.Project.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program-subjects")
public class Program_subjectsController {

    @Autowired
    private ProgramSubjectsRepository programSubjectsRepository;

    @Autowired
    private ProgramsRepository programsRepository;

    @Autowired
    private SubjectsRepository subjectsRepository;

    @PostMapping
    public Program_subjects create(
            @RequestParam Integer programId,
            @RequestParam Integer subjectId,
            @RequestBody Program_subjects body) {

        programsRepository.findById(programId)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        subjectsRepository.findById(Long.valueOf(subjectId))
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        body.setProgram(programId);
        body.setSubject(subjectId);

        return programSubjectsRepository.save(body);
    }

    @GetMapping
    public List<Program_subjects> getAll() {
        return programSubjectsRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        programSubjectsRepository.deleteById(id);
        return "Program_subject deleted";
    }
}
