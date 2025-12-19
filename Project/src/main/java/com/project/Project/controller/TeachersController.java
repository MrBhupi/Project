package com.project.Project.controller;

import com.project.Project.model.Teachers;
import com.project.Project.repository.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeachersController {

    @Autowired
    private TeachersRepository teachersRepository;

    @PostMapping
    public Teachers create(@RequestBody Teachers teacher) {
        return teachersRepository.save(teacher);
    }

    @GetMapping
    public List<Teachers> getAll() {
        return teachersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Teachers getById(@PathVariable Long id) {
        return teachersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    @PutMapping("/{id}")
    public Teachers update(@PathVariable Long id, @RequestBody Teachers data) {
        Teachers teacher = teachersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.setName(data.getName());
        teacher.setEmployee_id(data.getEmployee_id());
        teacher.setEmail(data.getEmail());
        teacher.setQualifications(data.getQualifications());
        teacher.setPermanent_address(data.getPermanent_address());
        teacher.setTemporary_address(data.getTemporary_address());
        teacher.setDob(data.getDob());
        teacher.setContact_no(data.getContact_no());
        teacher.setStatus(data.getStatus());
        teacher.setGenders(data.getGenders());

        return teachersRepository.save(teacher);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        teachersRepository.deleteById(id);
        return "Teacher deleted";
    }
}
