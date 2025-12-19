package com.project.Project.controller;

import com.project.Project.model.Terms;
import com.project.Project.repository.TermsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/terms")
public class TermsController {

    @Autowired
    private TermsRepository termsRepository;

    @PostMapping
    public Terms create(@RequestBody Terms term) {
        return termsRepository.save(term);
    }

    @GetMapping
    public List<Terms> getAll() {
        return termsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Terms getById(@PathVariable Long id) {
        return termsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Term not found"));
    }

    @PutMapping("/{id}")
    public Terms update(@PathVariable Long id, @RequestBody Terms data) {
        Terms term = termsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Term not found"));

        term.setName(data.getName());
        term.setStart_date(data.getStart_date());
        term.setEnd_date(data.getEnd_date());
        term.setCreatedAt(data.getCreatedAt());

        return termsRepository.save(term);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        termsRepository.deleteById(id);
        return "Term deleted";
    }
}
