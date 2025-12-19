package com.project.Project.controller;

import com.project.Project.model.Students;
import com.project.Project.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentsRepository studentsRepository;

    @PostMapping
    public Students create(@RequestBody Students student) {
        return studentsRepository.save(student);
    }

    @GetMapping
    public List<Students> getAll() {
        return studentsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Students getById(@PathVariable Integer id) {
        return studentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @PutMapping("/{id}")
    public Students update(@PathVariable Integer id, @RequestBody Students data) {
        Students student = studentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setUser(data.getUser());
        student.setName(data.getName());
        student.setGender(data.getGender());
        student.setDob(data.getDob());
        student.setPermanent_address(data.getPermanent_address());
        student.setTemporary_address(data.getTemporary_address());
        student.setRoll_no(data.getRoll_no());
        student.setFaculty(data.getFaculty());
        student.setSemester(data.getSemester());
        student.setBatch(data.getBatch());
        student.setProgram(data.getProgram());

        return studentsRepository.save(student);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        studentsRepository.deleteById(id);
        return "Student deleted";
    }
}
