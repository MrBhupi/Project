package com.project.Project.controller;

import com.project.Project.model.Subjects;
import com.project.Project.repository.SubjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectsController {

    @Autowired
    private SubjectsRepository subjectsRepository;

    @PostMapping
    public Subjects create(@RequestBody Subjects subject) {
        return subjectsRepository.save(subject);
    }

    @GetMapping
    public List<Subjects> getAll() {
        return subjectsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Subjects getById(@PathVariable Long id) {
        return subjectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    @PutMapping("/{id}")
    public Subjects update(@PathVariable Long id, @RequestBody Subjects data) {
        Subjects subject = subjectsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        subject.setCode(data.getCode());
        subject.setName(data.getName());
        subject.setFullMark(data.getFullMark());
        subject.setPassMarks(data.getPassMarks());
        subject.setCreatedAt(data.getCreatedAt());

        return subjectsRepository.save(subject);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        subjectsRepository.deleteById(id);
        return "Subject deleted";
    }
}
