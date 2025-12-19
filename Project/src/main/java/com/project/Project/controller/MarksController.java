package com.project.Project.controller;

import com.project.Project.model.Marks;
import com.project.Project.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marks")
public class MarksController {

    @Autowired
    private MarksRepository marksRepository;

    /* ================= CREATE ================= */
    @PostMapping
    public Marks create(@RequestBody Marks marks) {
        return marksRepository.save(marks);
    }

    /* ================= READ ALL ================= */
    @GetMapping
    public List<Marks> getAll() {
        return marksRepository.findAll();
    }

    /* ================= READ BY ID ================= */
    @GetMapping("/{id}")
    public Marks getById(@PathVariable Integer id) {
        return marksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marks not found"));
    }

    /* ================= UPDATE ================= */
    @PutMapping("/{id}")
    public Marks update(@PathVariable Integer id, @RequestBody Marks data) {

        Marks marks = marksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marks not found"));

        marks.setStudent(data.getStudent());
        marks.setSubject(data.getSubject());
        marks.setTerm(data.getTerm());
        marks.setObtained_marks(data.getObtained_marks());
      //  marks.setTotal_marks(data.getTotal_marks());
        //marks.setGrade(data.getGrade());

        return marksRepository.save(marks);
    }

    /* ================= DELETE ================= */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        marksRepository.deleteById(id);
        return "Marks deleted successfully";
    }
}
